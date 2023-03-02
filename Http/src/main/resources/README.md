## HTTP & Socket

---

#### 问：一个port能否被多个socket监听 [多个进程用同一个port]

- 一般情况下一个port只能被一个socket监听，但是通过反射可以配置成多个socket监听同一个port。
    - 通过设置 socket option: SO_REUSEPORT
    - port被多个进程监听时，accept事件时随机分配给监听进程的。
- UDP 不需要保持连接

#### 探索
> refer: https://blog.flipkart.tech/linux-tcp-so-reuseport-usage-and-implementation-6bfbf642885a
 <pre>

 一个 TCP连接是一个五元组 [protocol, server ip, server port, client ip, client port]

 在客户端中：
   Protocol          ：当基于应用程序提供的参数创建套接字时，该字段被初始化。出于本文的目的，协议始终是 TCP。例如，
                       socket(AF_INET, SOCK_STREAM, 0); /* 创建一个 TCP 套接字
   源 IP 地址和端口    ：这些通常由内核在应用程序调用 connect()而不事先调用 bind()时设置。
                       内核选择一个合适的 IP 地址来与目标服务器通信，并从临时端口范围(sysctl net.ipv4.ip_local_port_range)中选择一个源端口。
   目标 IP 地址和端口  ：这些由应用程序通过调用 connect()设置。


 在服务端中：
   协议              ：以与客户端应用程序相同的方式初始化。

   源 IP 地址和端口   ：由应用程序在调用 bind() 时设置

   目标 IP 地址和端口 ：客户端通过完成 TCP 3 次握手连接到服务器。
                     服务器的 TCP/IP 堆栈创建一个新的套接字来跟踪客户端连接，
                     并根据传入的客户端连接参数设置它的源 IP:端口和目标 IP:端口。
                     新套接字转换为 ESTABLISHED 状态，而服务器的 LISTEN 套接字保持不变。
                     此时，服务器应用程序对 LISTEN 套接字上的accept()的调用返回了对新 ESTABLISHED 套接字的引用。

   TIME-WAIT 套接字 : TIME-WAIT套接字在应用程序首先关闭它的 TCP 连接末端时创建。
                     这导致 TCP 4 次握手的启动，在此期间，在套接字关闭之前，
                     套接字状态从 ESTABLISHED 变为 FIN-WAIT1 到 FIN-WAIT2 到 TIME-WAIT。
                     由于协议原因，TIME-WAIT 状态是一个挥之不去的状态。
                     应用程序可以通过发送 TCP RST 数据包指示 TCP/IP 堆栈不要延迟连接。
                     这样做时，连接会立即终止，而无需经过 TCP 4 次握手.

 服务器通常在启动时执行以下系统调用：

   1. Create a socket:
       server_fd = socket(...);
   2. Bind to a well known IP address and port#:
       ret = bind(server_fd, ...);
   3. Mark the socket as passive by changing it's state to LISTEN:
       ret = listen(server_fd, ...);
   4. Wait for a client to connect, and get a reference file descriptor:
       client_fd = accept(server_fd, ...);

</pre>

#### 问： Socket在连接过程是什么样的呢
socket:

- client： client active fd -[connected]> server listen fd -> socket io
- server: active fd -[bind port/ip]> listen fd -[accept & 三次握手]> 生成 新的connect fd -> socket io

linux内核中，socket函数不管在客户端还是服务端，创建的套接字都是主动socket，
但是在服务端经过listen()，后把其转变为listen_socket_fd(被动监听socket)，
经过accept()后转变为connect_socket_fd(已连接socket)。

在转变为connect_socket_fd之前，都是同一个socket，只不过socket的状态改变了，
但是服务端经过accept()后返回的socket是新的socket，用于连接后的read()/write()。

listen_socket_fd专门负责响应客户端的请求，每个新的connect_socket_fd专门负责该次连接的数据交互，分层协作，提高服务端的性能。
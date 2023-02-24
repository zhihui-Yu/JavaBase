import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

import static java.nio.channels.SelectionKey.OP_ACCEPT;

/**
 * @author simple
 */
public class Server {
    public static void main(String[] args) throws Exception {
        new Server(8888);
    }

    // 执行select()前 必须要先把key注册进去

    private final Selector RW_SELECTOR;
    private final Selector ACCEPT_SELECTOR;
    private final ServerSocketChannel socketChannel;

    private AtomicBoolean rwRunning = new AtomicBoolean(false);


    Server(int port) throws Exception {
        socketChannel = ServerSocketChannel.open();
        socketChannel.configureBlocking(false); // nio
        socketChannel.bind(new InetSocketAddress(port));

        ACCEPT_SELECTOR = Selector.open();
        RW_SELECTOR = Selector.open();

        socketChannel.register(ACCEPT_SELECTOR, OP_ACCEPT);

        new Thread(() -> {
            System.out.println(threadName() + ": running");
            while (true) {
                try {
                    ACCEPT_SELECTOR.select();
                    watchKeys(ACCEPT_SELECTOR, key -> {
                        if (key.isAcceptable()) {
                            accept(key);
                        } else if (key.isConnectable()) {
                            connect(key);
                        } else {
                            System.out.println("unknown---");
                        }
                    });
                } catch (Exception e) {
                    System.out.println(threadName() + "-ERROR：");
                    e.printStackTrace();
                }
            }
        }, "accept-selector-handler").start();
    }

    private void runRWSelector() {
        if (!rwRunning.get()) {
            rwRunning.set(true);
        }
        new Thread(() -> {
            System.out.println(threadName() + ": running");
            while (true) {
                try {
                    RW_SELECTOR.select(); // 下次accept 后要才能select， close后不能？
                    watchKeys(RW_SELECTOR, key -> {
                        if (key.isReadable()) {
                            read(key);
                        } else if (key.isWritable()) {
                            write(key);
                        } else {
                            System.out.println("unknown key" + key);
                        }
                    });
                } catch (Exception e) {
                    System.out.println(threadName() + "-ERROR：");
                    e.printStackTrace();
                    return;
                }
            }
        }, "rw-selector-handler").start();
    }

    private void watchKeys(Selector selector, Consumer<SelectionKey> consumer) {
        Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
        while (iterator.hasNext()) {
            SelectionKey key = iterator.next();
            consumer.accept(key);
            iterator.remove();
        }
    }

    private void read(SelectionKey key) {
        try {
            SocketChannel channel = (SocketChannel) key.channel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            StringBuilder sb = new StringBuilder(1024);
            do {
                buffer.clear();
                int len = channel.read(buffer);
                if (len == -1) {
                    channel.close();
                    System.out.println("close socket");
                    return;
                }
                sb.append(new String(buffer.array(), 0, len, Charset.defaultCharset()));
            } while (buffer.remaining() == 0);
            System.out.println("received msg from " + getClientAddress(channel.socket()) + ": " + sb.toString());
            channel.register(RW_SELECTOR, SelectionKey.OP_WRITE);
        } catch (Exception e) {
            System.out.println(threadName() + "-ERROR：");
            e.printStackTrace();
        }
    }

    private void write(SelectionKey key) {
        try {
            SocketChannel channel = (SocketChannel) key.channel();
            channel.write(ByteBuffer.wrap("Server received".getBytes()));
            channel.register(RW_SELECTOR, SelectionKey.OP_READ);
            System.out.println("write msg to client [" + getClientAddress(channel.socket()) + "] ：Server received");
        } catch (Exception e) {
            System.out.println(threadName() + "-ERROR：");
            e.printStackTrace();
        }
    }

    private void connect(SelectionKey key) {
        System.out.println("connect with a client: " + ((ServerSocketChannel) key.channel()).socket().getInetAddress().getHostAddress());
    }

    private void accept(SelectionKey key) {
        try {
            ServerSocketChannel channel = (ServerSocketChannel) key.channel();
            SocketChannel sc = channel.accept();
            sc.configureBlocking(false);
            socketChannel.register(ACCEPT_SELECTOR, OP_ACCEPT);
            sc.register(RW_SELECTOR, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
            runRWSelector();
            System.out.println("accept a client: " + channel.socket().getInetAddress().getCanonicalHostName());
        } catch (Exception e) {
            System.out.println(threadName() + "-ERROR：");
            e.printStackTrace();
        }
    }

    public static String getClientAddress(Socket socket) {
        return socket.getInetAddress().getHostAddress();
    }

    private String threadName() {
        return Thread.currentThread().getName();
    }
}

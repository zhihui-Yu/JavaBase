package http;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author simple
 */
public class Client {
    public static void main(String[] args) throws IOException, InterruptedException {
        AtomicInteger in = new AtomicInteger(0);
        for (int i = 0; i < 100000; i++) {
            new Thread(() -> {
                try {
                    Socket socket = new Socket();
                    socket.connect(new InetSocketAddress("localhost", 8888));
                    socket.getOutputStream().write("I am client".getBytes(StandardCharsets.UTF_8));
                    socket.getOutputStream().flush();

                    InputStream inputStream = socket.getInputStream();
                    byte[] bytes = new byte[1024];
                    int len = inputStream.read(bytes);
                    System.out.println(new String(bytes, 0, len, Charset.defaultCharset()) + "-----" + in.getAndIncrement());
                    Thread.sleep(5000);
                    socket.close();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }).start();
            if (i % 10000 == 0)
                TimeUnit.SECONDS.sleep(1);
        }
    }
}

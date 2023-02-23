import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * @author simple
 */
public class SocketUtils {
    public static void printReceive(SocketChannel channel) throws Exception {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        StringBuilder sb = new StringBuilder(1024);
        do {
            buffer.clear();
            int len = channel.read(buffer);
            sb.append(new String(buffer.array(), 0, len, Charset.defaultCharset()));
        } while (buffer.remaining() == 0);
        System.out.println("received msg from " + getClientAddress(channel.socket()) + ": " + sb.toString());
    }

    public static void writeMsg(SocketChannel channel, String writeMsg) throws Exception {

    }

    public static String getClientAddress(Socket socket) {
        return socket.getInetAddress().getHostAddress();
    }
}

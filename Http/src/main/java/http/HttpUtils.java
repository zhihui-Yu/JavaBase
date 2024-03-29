package http;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Map;

/**
 * @author simple
 */
public class HttpUtils {

    // ||----------------------------------------------||
    // ||       Http -> socket -> connect              ||
    // ||----------------------------------------------||


    public static HttpURLConnection getConn(String url) {
        try {
            URL httpUrl = new URL(url);
            return (HttpURLConnection) httpUrl.openConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static InputStream getInputStream(String url, Map<String, String> reqProperties) throws Exception {
        HttpURLConnection conn = getConn(url);
        reqProperties.forEach(conn::setRequestProperty);
//        System.out.println("rec len: " + conn.getContentLength()+"-- header:"+reqProperties.get("RANGE"));
        return conn.getInputStream();
    }

    public static void logResHeaders(HttpURLConnection conn) throws IOException {
        String name = Thread.currentThread().getName();
        System.out.println("Thread-" + name + ": print response headers");
        conn.getHeaderFields().forEach((k, v) -> System.out.printf("Thread-%s: %s: %s%n", name, k, v));
//        conn.setFixedLengthStreamingMode();
    }

    public static void main(String[] args) throws Exception {

        byte[] bytes = new byte[10240];
        HttpURLConnection conn = getConn("https://www.baidu.com");
        conn.setChunkedStreamingMode(100);
        conn.setDoOutput(true);
        try (InputStream inputStream = conn.getInputStream(); BufferedInputStream bis = new BufferedInputStream(inputStream)) {
            int len = bis.read(bytes);
            System.out.println(new String(bytes, 0, len, Charset.defaultCharset()));
            logResHeaders(conn);
        }
    }
}

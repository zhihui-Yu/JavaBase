import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author simple
 */
public class FileUtils {
    public static String read(String path) throws IOException {
        File file = new File(path);
        StringBuilder content = new StringBuilder(512);
        if (file.exists() && file.canRead()) {

            BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String cont = null;
            while ((cont = bf.readLine()) != null) {
                content.append(cont).append("\n");
            }

        } else {
            System.out.println("not");
        }
        return content.toString();
    }

    public static byte[] binRead(String path) throws Exception {
        FileInputStream fileInputStream = new FileInputStream(new File(path));
        byte[] content = new byte[fileInputStream.available()];
        fileInputStream.read(content);
        fileInputStream.close();
        return content;
    }
}

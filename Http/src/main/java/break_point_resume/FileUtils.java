package break_point_resume;

import java.io.File;

/**
 * @author simple
 */
public class FileUtils {
    public static String fileName(String link) {
        return link.substring(link.lastIndexOf('/') + 1);
    }

    public static boolean localExists(String fileName) {
        File file = new File(fileName);
        return file.exists() && file.isFile();
    }

    public static int len(String filePath) {
        return (int) new File(filePath).length();
//            try (var is = new FileInputStream(localFileName)) {
//                return (int) is.getChannel().size();
//            } catch (Exception exception) {
//                exception.printStackTrace();
//            }
//            return 0;
    }
}

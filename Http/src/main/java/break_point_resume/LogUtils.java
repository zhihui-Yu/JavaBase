package break_point_resume;

/**
 * @author simple
 */
public class LogUtils {
    public static void print(String content, Object... param) {
        String name = Thread.currentThread().getName();
        System.out.printf("[" + name + "] " + content + "\n", param);
    }
}

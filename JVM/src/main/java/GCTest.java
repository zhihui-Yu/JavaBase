import java.util.ArrayList;

/**
 * @author simple
 */
public class GCTest {
    public static void main(String[] args) throws InterruptedException {
        for (int j = 0; j < 100000; j++) {
            int i = 100;
            ArrayList<Object> list = new ArrayList<>();

            Thread.sleep(5);
            if (j % 2000 == 0) {
//                System.gc();
                j = 0;
            }
            i = 0;
            list = null;
        }
    }
}

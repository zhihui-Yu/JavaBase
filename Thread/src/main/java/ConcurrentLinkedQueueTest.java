import java.util.Arrays;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author listener
 */
public class ConcurrentLinkedQueueTest {
    public static void main(String[] args) throws InterruptedException {

        // 1. 线程安全的集合类型
        ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<>();
        queue.addAll(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20));

        // 3. 多线程清空数组
        for (int i = 0; i < 10; i++) {
            new Thread() {
                @Override
                public void run() {
                    while (!queue.isEmpty()) {
                        Integer remove = queue.poll();
                        System.out.println(Thread.currentThread().getName() + " delete: " + remove);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }.start();
        }

        Thread.sleep(1000);
        System.out.println("剩余数量:" + queue.size());
    }

}

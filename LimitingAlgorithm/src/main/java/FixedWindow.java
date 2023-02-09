import java.time.LocalDateTime;

/**
 * @author simple
 */
public class FixedWindow {
    // 固定窗口： 规定时间内限制最大访问数量

    private static final int MAX_PERMIT = 2;
    private static final int WINDOW = 1_000_000_000; // ns
    private long startTime = System.nanoTime();
    private int permitted = 0; // 考虑是否加 volatile 来 满足线程可见

    public synchronized boolean tryAcquire(long timeInNano) {
        if (startTime + WINDOW > timeInNano) {
            return ++permitted <= MAX_PERMIT;
        }
        startTime += WINDOW;
        permitted = 0;
        return ++permitted <= MAX_PERMIT;
    }

    public static void main(String[] args) throws InterruptedException {
        FixedWindow fixedWindow = new FixedWindow();
        for (int i = 0; i < 10; i++) {
            if (fixedWindow.tryAcquire(System.nanoTime())) {
                System.out.println("通过了 => " + LocalDateTime.now());
            } else {
                System.out.println("被限制了 => " + LocalDateTime.now());
            }

            Thread.sleep(250);
//            sleep 400 时候， 在11:15:42时候通过了三次， 这就是固定窗口的缺陷
//            通过了 => 2023-02-09T11:15:40.941161100
//            通过了 => 2023-02-09T11:15:41.349396600
//            被限制了 => 2023-02-09T11:15:41.761801600
//            通过了 => 2023-02-09T11:15:42.167612600
//            通过了 => 2023-02-09T11:15:42.575463600
//            通过了 => 2023-02-09T11:15:42.981296300
//            通过了 => 2023-02-09T11:15:43.389944
//            被限制了 => 2023-02-09T11:15:43.797748300
//            通过了 => 2023-02-09T11:15:44.201928
//            通过了 => 2023-02-09T11:15:44.605208800
        }
    }
}

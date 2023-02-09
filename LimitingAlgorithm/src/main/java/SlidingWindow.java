import java.time.LocalDateTime;

/**
 * @author simple
 */
public class SlidingWindow {
    public static void main(String[] args) throws InterruptedException {
        SlidingWindow limit = new SlidingWindow();
        for (int i = 0; i < 10; i++) {
            if (limit.tryAcquire(System.nanoTime())) {
                System.out.println("通过了 => " + LocalDateTime.now());
            } else {
                System.out.println("被限制了 => " + LocalDateTime.now());
            }
            Thread.sleep(250);
        }
    }


    // 将1s分成x分，每份内记录了在这段时间内的请求数量， 当时间过来1/x 时，将舍弃最久的，并且创建最新的。
    // 这种算法只能说时由于时间间隔缩小而精确了一些

    private final int maxCount = 2;
    private final int slotCount = 10; // 1s分成10份
    private final long window = 1_000_000_000L; // 窗口大小， 1s
    private final long slot = window / slotCount; // 窗口内每个单元的长度

    private int[] windows = new int[slotCount]; // 窗口
    private long startTime = System.nanoTime();
    private long lastUpdated = System.nanoTime();

    public synchronized boolean tryAcquire(long time) {
        boolean acquire = false;
        // 重置windows 如果时间超过单元
        reset(time);
        // 计算是否超出请求限制
        if (maxCount > sum()) {
            // 没有则更新对应单元的请求次数
            windows[(int) ((time - lastUpdated) / slot) % 10] += 1;
            acquire = true;
        }
        lastUpdated = time;
        return acquire;
    }

    public int sum() {
        int sum = 0;
        for (int j : windows) {
            sum += j;
        }
        return sum;
    }

    public void reset(long time) {
        if (startTime + window >= time) {
            return;
        }
        startTime = time; // 由于要重置， start 也要变化
        long diff = time - lastUpdated;
        if (diff >= window) {
            windows = new int[slotCount];
        }
        int unitCount = (int) (diff / slot) % 10 + 1; // 如果正好超 1/x 以内的值，windows[0] 要调整，整个数组左移1位
        for (int i = 0; i < windows.length; i++) {
            if (i + unitCount >= windows.length) {
                windows[i] = 0;
            } else {
                windows[i] = windows[i + unitCount];
            }
        }
    }
}

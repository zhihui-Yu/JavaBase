import java.time.LocalDateTime;

/**
 * @author simple
 */
public class LeakyBucket {
    // 一个桶，按一定速率往桶里加水（请求）, 同时桶从一个口中按一定速率漏水（请求通过）。
    // 如果桶加水的速率超过漏水的速率，那会先缓存水直到桶不能装。 (现实使用：不愿请求丢失，即使处理速度慢一点)

    private final int buck = 2;
    private final double rate = 1; // 漏水速率， x 个每秒

    private int water = 0; // 桶内的水，既已请求数
    private long latestUpdatedTime = System.currentTimeMillis() / 1_000L; // 秒级别好测试

    public synchronized boolean tryAcquire(long time) {
        // 计算桶剩余的水
        int newWater = Math.max(0, (int) (water - ((time - latestUpdatedTime) * rate))); // 当前已请求数 - 这段时间内会被处理的请求数, 如果每次减少的是小数，取整后相当于一直没减，所以用整数
        if (newWater != water) {
            water = newWater;
            latestUpdatedTime = time;
        }
        System.out.println("桶中新的水位是 ====》》 " + water);

        if (water < buck) { // 水没超过总量
            water++; // 缓存请求
            return true;
        }
        return false;
    }


    public static void main(String[] args) throws InterruptedException {
        LeakyBucket limit = new LeakyBucket();
        for (int i = 0; i < 10; i++) {
            if (limit.tryAcquire(System.currentTimeMillis() / 1_000L)) {
                System.out.println("通过了 => " + LocalDateTime.now());
            } else {
                System.out.println("被限制了 => " + LocalDateTime.now());
            }
            System.out.println("--------------------");
            Thread.sleep(250);
        }
    }
}

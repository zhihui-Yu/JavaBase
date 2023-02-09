import java.time.LocalDateTime;

/**
 * @author simple
 */
public class TokenBucket {
    // 令牌桶算法： 按固定速率往桶里加令牌，如果有请求则消耗令牌。
    // 使用场景： 瞬时流量多，且快速失败保证下游安全。 令牌桶算法可以保证在大并发时段生产令牌以供消耗。与漏桶不同的时，没有令牌快速失败，令牌桶只有超过桶大小才会失败。
    private final int max = 2; // 最大可请求数
    private final double fillRate = 1; // 填充速率 / s

    private int current = max; // 当前可请求数
    private long latestUpdatedTime = System.currentTimeMillis() / 1_000L;

    public synchronized boolean tryAcquire(long time) {
        current = Math.min(max, (int) (current + (time - latestUpdatedTime) * fillRate)); // max(最大的可请求数， 当前可请求数+令牌填充数） 保证 (time - latestUpdatedTime) 是个整数
        latestUpdatedTime = time;
        if (current > 0) {
            current--;
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws InterruptedException {
        TokenBucket limit = new TokenBucket();
        for (int i = 0; i < 10; i++) {
            if (limit.tryAcquire(System.currentTimeMillis() / 1_000L)) {
                System.out.println("通过了 => " + LocalDateTime.now());
            } else {
                System.out.println("被限制了 => " + LocalDateTime.now());
            }
            Thread.sleep(250);
        }
    }
}

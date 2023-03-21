package break_point_resume;

import java.util.concurrent.CountDownLatch;

import static break_point_resume.LogUtils.print;

/**
 * @author simple
 */
public class LogTask implements Runnable {
    long pre;
    int fileLen;
    CountDownLatch countDownLatch;

    public LogTask(int fileLen, CountDownLatch countDownLatch) {
        this.fileLen = fileLen;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        while (true) {
            var cur = BreakPointResume.DOWNLOAD_SIZE.get();
            long speed = (cur - pre) / 1024; // KB/s
            String completion = String.format("%.2f", (float) cur * 100 / fileLen);
            print("Log-Thread download: all=%s, current=%s, speed: %s K/S, completion: %s%% ", fileLen, cur, speed, completion);
            pre = cur;

            if (completion.equals("100.00")) {
                countDownLatch.countDown();
                return;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
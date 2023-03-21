package break_point_resume;

import static break_point_resume.LogUtils.print;

/**
 * @author simple
 */
public class LogTask implements Runnable {
    long pre;
    int fileLen;

    public LogTask(int fileLen) {
        this.fileLen = fileLen;
    }

    @Override
    public void run() {
        while (true) {
            var cur = BreakPointResume.DOWNLOAD_SIZE.get();
            long speed = (cur - pre) / 1024; // KB/s
            String completion = String.format("%.2f", (float) cur * 100 / fileLen);
            print("download: all=%s, current=%s, speed: %s K/S, completion: %s%% ", fileLen, cur, speed, completion);
            pre = cur;

            if (completion.equals("100.00%")) return;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
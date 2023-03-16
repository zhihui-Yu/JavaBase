import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author simple
 */
public class BreakPointResume {
    public static void main(String[] args) throws InterruptedException {
        new BreakPointResume().download();
    }

    // test https first
    String link = "http://mirror.aarnet.edu.au/pub/TED-talks/911Mothers_2010W-480p.mp4";  // len: 68936214
//    String link = "http://vjs.zencdn.net/v/oceans.mp4";
    String fileName;

    int downloadThread = 10;
    int segment = 1024 * 1024; // 1M

    public static final AtomicLong atomic = new AtomicLong(0);

    CountDownLatch countDownLatch; // 全部下完的判断
    ExecutorService executor = Executors.newFixedThreadPool(downloadThread);

    public BreakPointResume() {
    }

    public BreakPointResume(String url) {
        this.link = url;
    }

    public void download() throws InterruptedException {
        this.fileName = fileName();
        // 打开一个连接，并没有连接现在
        HttpURLConnection conn = HttpUtils.getConn(link);
        int fileLen = conn.getContentLength();

        // 打印前置信息
        print("下载连接：" + link);
        print("remote file name: %s \nremote file length: %s", fileName, fileLen);

        // 判断本地是否已经存在完整文件
        if (localExists(fileName)) {
            // 完整则返回
            print("本地已下载完成");
            return;
        }

        // 不完整开始分段下载
        List<Task> tasks = split(fileName, fileLen);
        print("split task size:" + tasks.size());
        tasks.forEach(executor::submit); // todo 失败重试？
        // todo 下载完成度？
        // 总完成度
        new Thread(new LogTask(fileLen)).start();

        // 下载完成后合并文件
        countDownLatch.await();

        mergeFiles(tasks);
        System.exit(0);
    }

    private void mergeFiles(List<Task> tasks) {
        print("start to merge part files");
        // 创建一个新文件
        File file = new File(fileName);
        var all = tasks.stream().sorted(Comparator.comparing(x -> x.startLen)).toList();
        try (var fos = new FileOutputStream(file)) {
            for (var task : all) {
                // 读取task创建的文件 并写入file
                var fis = new FileInputStream(task.localFileName);
                byte[] bytes = new byte[1024];
                int len;
                while ((len = fis.read(bytes)) != -1) {
                    fos.write(bytes, 0, len);
                }
                fis.close();
            }
        } catch (Exception exception) {
            // 删除文件
            if (file.exists()) file.delete();
            exception.printStackTrace();
        }
        print("file merged: %s", fileName);
        print("deleting part files");
        // 删除part所有文件
        tasks.forEach(t -> new File(t.localFileName).delete());
        print("all done");
    }

    private List<Task> split(String fileName, int fileLen) {
        List<Task> tasks = new LinkedList<>();

        int taskSize = fileLen / segment;
        int exceed = fileLen % segment;
        taskSize += exceed > 0 ? 1 : 0;

        countDownLatch = new CountDownLatch(taskSize);

        for (int i = 0; i < taskSize; i++) {
            if (taskSize - 1 == i) {
                tasks.add(new Task((taskSize - 1) * segment, fileLen, fileName, link, countDownLatch));
            } else {
                tasks.add(new Task(i * segment, (i + 1) * segment, fileName, link, countDownLatch));
            }
        }
        return tasks;
    }

    private String fileName() {
        return link.substring(link.lastIndexOf('/') + 1);
    }

    public static boolean localExists(String fileName) {
        File file = new File(fileName);
        return file.exists() && file.isFile();
    }

    private static void print(String content, Object... param) {
        System.out.printf(content + "\n", param);
    }

    public static class Task implements Callable<Boolean> {
        private final int startLen;
        private final int endLen;

        private final String fileName;
        private final String link;

        private final CountDownLatch countDownLatch;

        private String localFileName;

        public Task(int start, int end, String fileName, String link, CountDownLatch countDownLatch) {
            this.startLen = start;
            this.endLen = end;
            this.fileName = fileName;
            this.link = link;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public Boolean call() { // 是否下完
            // 判断是否缺少该段文件，是则下载, 否则标记完成
            localFileName = String.format("%s-%s-%s", fileName, startLen, endLen);
            if (localExists(localFileName)) {
                atomic.getAndAdd(endLen - startLen);
                countDownLatch.countDown();
                return true;
            }

            File file = new File(localFileName);
            try (var ins = HttpUtils.getInputStream(link, Map.of("RANGE", "bytes=" + startLen + "-" + endLen))) {
                // 获取输入流 保存到本地
                var buffered = new BufferedInputStream(ins);
                if (file.createNewFile()) {
                    var fos = new FileOutputStream(file);
                    byte[] bytes = new byte[1024];
                    int len;
                    while ((len = buffered.read(bytes)) != -1) {
                        fos.write(bytes, 0, len);
                        atomic.getAndAdd(len);
                    }
                    fos.close();
                }
                buffered.close();
            } catch (Exception e) {
                if (file.exists()) file.delete();
                e.printStackTrace();
                return false;
            }
            countDownLatch.countDown();
            return true;
        }

        public String getLocalFileName() {
            return localFileName;
        }

        public int getStartLen() {
            return startLen;
        }
    }

    public static class LogTask implements Runnable {
        long pre;
        int fileLen;

        public LogTask(int fileLen) {
            this.fileLen = fileLen;
        }

        @Override
        public void run() {
            while (true) {
                var cur = atomic.get();
                long speed = (cur - pre) / 1024; // KB/s
                String completion = String.format("%.2f", (float) cur * 100 / fileLen);
                print("download: all=%s, current=%s, speed: %s K/S, completion: %s%% ", fileLen, cur, speed, completion);
                pre = cur;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}

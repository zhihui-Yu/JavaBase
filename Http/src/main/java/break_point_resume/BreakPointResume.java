package break_point_resume;

import http.HttpUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

import static break_point_resume.FileUtils.fileName;
import static break_point_resume.FileUtils.localExists;
import static break_point_resume.LogUtils.print;

/**
 * @author simple
 */
public class BreakPointResume {
    //    String link = "http://mirror.aarnet.edu.au/pub/TED-talks/911Mothers_2010W-480p.mp4";  // len: 68936214
    String link = "http://vjs.zencdn.net/v/oceans.mp4";
    String fileName;

    public static final int SEGMENT = 1024 * 1024; // 1M
    public static final AtomicLong DOWNLOAD_SIZE = new AtomicLong(0); // store the actual download size

    int downloadThread = 10;
    CountDownLatch countDownLatch; // 全部下完的判断
    ExecutorService executor = Executors.newFixedThreadPool(downloadThread);

    public BreakPointResume() {
    }

    public BreakPointResume(String url) {
        this.link = url;
    }

    public void download() throws InterruptedException {
        this.fileName = fileName(link);
        // 打开一个连接，并没有连接现在
        HttpURLConnection conn = HttpUtils.getConn(link);
        int fileLen = conn.getContentLength();

        // 打印前置信息
        print("下载连接：" + link);
        print("remote file name: %s \nremote file length: %s", fileName, fileLen);

        // 判断本地是否已经存在完整文件
        if (localExists(fileName) && FileUtils.len(fileName) == fileLen) {
            // 完整则返回
            print("本地已下载完成");
            return;
        }

        // 不完整开始分段下载
        List<DownloadTask> tasks = split(fileName, fileLen);
        print("split task size:" + tasks.size());
        tasks.forEach(executor::submit); // todo 失败重试？

        // 总完成度
        new Thread(new LogTask(fileLen, countDownLatch)).start();

        // 下载完成后合并文件
        countDownLatch.await();

        mergeFiles(tasks);
        System.exit(0);
    }

    private void mergeFiles(List<DownloadTask> tasks) {
        print("start to merge part files");
        // 创建一个新文件
        File file = new File(fileName);
        var all = tasks.stream().sorted(Comparator.comparing(DownloadTask::getStartLen)).toList();
        try (var fos = new FileOutputStream(file)) {
            for (var task : all) {
                // 读取task创建的文件 并写入file
                var fis = new FileInputStream(task.getFileName());
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
        tasks.forEach(t -> new File(t.getFileName()).delete());
        print("all done");
    }

    private List<DownloadTask> split(String fileName, int fileLen) {
        List<DownloadTask> tasks = new LinkedList<>();

        int taskSize = fileLen / SEGMENT;
        int exceed = fileLen % SEGMENT;
        taskSize += exceed > 0 ? 1 : 0;

        countDownLatch = new CountDownLatch(taskSize + 1); // more one for the log thread stop

        for (int i = 0; i < taskSize; i++) {
            if (taskSize - 1 == i) {
                tasks.add(new DownloadTask((taskSize - 1) * SEGMENT, fileLen, fileName, link, countDownLatch));
            } else {
                tasks.add(new DownloadTask(i * SEGMENT, (i + 1) * SEGMENT - 1, fileName, link, countDownLatch));
            }
        }
        return tasks;
    }
}

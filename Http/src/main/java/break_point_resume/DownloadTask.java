package break_point_resume;

import http.HttpUtils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

/**
 * @author simple
 */
public class DownloadTask implements Callable<Boolean> {
    private int startLen;
    private final int endLen;

    private final String fileName;
    private final String link;

    private final CountDownLatch countDownLatch;

    private String localFileName;

    public DownloadTask(int start, int end, String fileName, String link, CountDownLatch countDownLatch) {
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
        if (FileUtils.localExists(localFileName)) {
            int len = FileUtils.len(localFileName);
            var dif = endLen - startLen;
//                print("file: %s, len: %s, lost: %s", localFileName, len, dif - len);
            if (len >= dif) { // 获取的文件长度gen
                BreakPointResume.DOWNLOAD_SIZE.getAndAdd(len);
                countDownLatch.countDown();
                return true;
            } else {
                BreakPointResume.DOWNLOAD_SIZE.getAndAdd(len);
                startLen += len; // 不能+1， 假设从100-150，下载了30，那么100-129是下完的， 130-150还没下载
            }
        }

        File file = new File(localFileName);
        try (var ins = HttpUtils.getInputStream(link, Map.of("RANGE", "bytes=" + startLen + "-" + endLen))) {
            // 获取输入流 保存到本地
            var buffered = new BufferedInputStream(ins);
            if (!file.exists()) file.createNewFile();
            var fos = new FileOutputStream(file, true); // 存在则append
            byte[] bytes = new byte[1024];
            int len;
            while ((len = buffered.read(bytes)) != -1) {
                fos.write(bytes, 0, len);
                BreakPointResume.DOWNLOAD_SIZE.getAndAdd(len);
            }
            fos.close();
            buffered.close();
        } catch (Exception e) {
            if (file.exists()) file.delete();
            e.printStackTrace();
            return false;
        }
        countDownLatch.countDown();
        return true;
    }

    //  有下载过，但是没下完，直接删掉，重新下载。
//        @Override
    public Boolean call2() { // 是否下完
        // 判断是否缺少该段文件，是则下载, 否则标记完成
        localFileName = String.format("%s-%s-%s", fileName, startLen, endLen);
        if (FileUtils.localExists(localFileName)) {
            var file = new File(localFileName);
            if (file.length() < BreakPointResume.SEGMENT) {
                file.delete();
            } else {
                BreakPointResume.DOWNLOAD_SIZE.getAndAdd(endLen - startLen);
                countDownLatch.countDown();
                return true;
            }
        }

        File file = new File(localFileName);
        try (var ins = HttpUtils.getInputStream(link, Map.of("RANGE", "bytes=" + startLen + "-" + endLen))) { // 0-10, 意味着 11个字节
            // 获取输入流 保存到本地
            var buffered = new BufferedInputStream(ins);
            if (file.createNewFile()) {
                var fos = new FileOutputStream(file);
                byte[] bytes = new byte[1024];
                int len;
                while ((len = buffered.read(bytes)) != -1) {
                    fos.write(bytes, 0, len);
                    BreakPointResume.DOWNLOAD_SIZE.getAndAdd(len);
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

    public String getFileName() {
        return localFileName;
    }

    public int getStartLen() {
        return startLen;
    }

    public String taskName() {
        return "Thread-" + localFileName;
    }
}

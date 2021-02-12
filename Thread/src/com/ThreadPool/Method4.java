package com.ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Method4 {

    //单线程的线程池，线程异常结束，会创建一个新的线程，能确保任务按提交顺序执行
    static ExecutorService singleExecutor = Executors.newSingleThreadExecutor();


    public static void main(String[] args) {
        //测试单线程的线程池
        for (int i = 0; i < 3; i++) {
            final int index = i;
            singleExecutor.execute(new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " index:" + index);
                }
            });
        }

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("4秒后...");

        singleExecutor.shutdown();
    }

}
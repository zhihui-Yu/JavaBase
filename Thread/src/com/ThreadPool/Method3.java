package com.ThreadPool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Method3 {

    //定长线程池，可执行周期性的任务
    static ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(3);


    public static void main(String[] args) {
        //测试定长、可周期执行的线程池
        for (int i = 0; i < 3; i++) {
            final int index = i;
            //scheduleWithFixedDelay 固定的延迟时间执行任务； scheduleAtFixedRate 固定的频率执行任务
            scheduledExecutor.scheduleWithFixedDelay(new Runnable() {
                public void run() {
                    System.out.println(Thread.currentThread().getName() + " index:" + index);
                }
            }, 0, 5, TimeUnit.SECONDS);
            System.out.println("==========================");
        }

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("4秒后...");

        //scheduledExecutor.shutdown();
    }
}
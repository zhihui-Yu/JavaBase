package com.createThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
//线程池实现
public class Thread4 {
 
    public static void main(String[] args) {
        //创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        ThreadPool threadPool = new ThreadPool();
        for(int i =0;i<5;i++){
            //为线程池分配任务
            executorService.submit(threadPool);
        }
        //关闭线程池
        executorService.shutdown();
        System.out.println('a'+1);
    }
}
 
class ThreadPool implements Runnable {
 
    @Override
    public void run() {
        for(int i = 0 ;i<2;i++){
            System.out.println(Thread.currentThread().getName() + ":" + i);
        }
    }

}
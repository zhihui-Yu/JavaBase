package com.ThreadPool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Method5 {
	//单线程可执行周期性任务的线程池
	static ScheduledExecutorService singleScheduledExecutor = Executors.newSingleThreadScheduledExecutor();
	
	
	public static void main(String[] args) {
	//测试单线程可周期执行的线程池
		for (int i = 0; i < 3; i++) {
			final int index = i;
			//scheduleWithFixedDelay 固定的延迟时间执行任务； scheduleAtFixedRate 固定的频率执行任务
			singleScheduledExecutor.scheduleAtFixedRate(new Runnable() {
				public void run() {
					System.out.println(Thread.currentThread().getName() + " index:" + index);
				}
			}, 0, 3, TimeUnit.SECONDS);
		}
		
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("4秒后...");
		
		singleScheduledExecutor.shutdown();
	}
	
}
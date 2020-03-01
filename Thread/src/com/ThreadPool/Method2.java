package com.ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Method2 {

	//可缓存的线程池，如果线程池的容量超过了任务数，自动回收空闲线程，任务增加时可以自动添加新线程，线程池的容量不限制
	static ExecutorService cachedExecutor = Executors.newCachedThreadPool();
	
	
	public static void main(String[] args) {
	//测试可缓存线程池
		for (int i = 0; i < 6; i++) {
			final int index = i;
			cachedExecutor.execute(new Runnable() {
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
		System.out.println(Thread.currentThread().getName() + "4秒后...");
		
		cachedExecutor.shutdown();
	}
	
}
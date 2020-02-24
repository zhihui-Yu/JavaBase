package com.ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Method6 {
	 
	//任务窃取线程池
	//当主线程结束 就结束了
	static ExecutorService workStealingExecutor = Executors.newWorkStealingPool();
	
	public static void main(String[] args) {
	
	//测试任务窃取线程池
		for (int i = 0; i < 10; i++) {//本机 CPU 8核，这里创建10个任务进行测试
			final int index = i;
			workStealingExecutor.execute(new Runnable() {
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
			Thread.sleep(4000);//这里主线程不休眠，不会有打印输出
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("4秒后...");
		
//		workStealingExecutor.shutdown();
	}
	
}
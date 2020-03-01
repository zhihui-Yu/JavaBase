package com.ThreadPoolExecutor;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
/**
 * ThreadPoolExecutor 特性测试
 * 
 * submit 会返回一个结过，execute不会，后者效率高一点
 * 
 * @author listener
 */
public class Test1 {
	private static Test1 test = new Test1();

	volatile static int finishState = 0;

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		/**
		 * 第一个参数：核心线程数
		 * 第二个参数：最大线程数
		 * 第三个参数：空闲线程存活时间
		 * 第四个参数：回收空闲线程的时间单位
		 * 第五个参数：任务之前前保存在的队列 size为10
		 */
		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 7, 10, TimeUnit.SECONDS,
				new LinkedBlockingDeque<>(10));
		//设置核心线程 结束
		threadPoolExecutor.allowCoreThreadTimeOut(true);
		ExecutorCompletionService<String> executorCompletionService = new ExecutorCompletionService<String>(threadPoolExecutor);

		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 30; i++) {
					String name = "name_" + i;
					TestCallable testCallable = new TestCallable(name);
					try {
						executorCompletionService.submit(testCallable);

						synchronized (test) {
							System.out.print("+++添加任务 name: " + name);
							System.out.print(" ActiveCount: " + threadPoolExecutor.getActiveCount());
							System.out.print(" poolSize: " + threadPoolExecutor.getPoolSize());
							System.out.print(" queueSize: " + threadPoolExecutor.getQueue().size());
							System.out.println(" taskCount: " + threadPoolExecutor.getTaskCount());
						}
					} catch (RejectedExecutionException e) {
						synchronized (test) {
							System.out.println("拒绝：" + name);
						}
					}
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				finishState = 1;
			}
		};

		Thread addThread = new Thread(runnable);
		addThread.start();

		// 添加的任务有被抛弃的。taskCount不一定等于添加的任务。
		int completeCount = 0;
		while (!(completeCount == threadPoolExecutor.getTaskCount() && finishState == 1)) {
			Future<String> take = executorCompletionService.take();
			String taskName = take.get();
			synchronized (test) {
				System.out.print("---完成任务 name: " + taskName);
				System.out.print(" ActiveCount: " + threadPoolExecutor.getActiveCount());
				System.out.print(" poolSize: " + threadPoolExecutor.getPoolSize());
				System.out.print(" queueSize: " + threadPoolExecutor.getQueue().size());
				System.out.print(" taskCount: " + threadPoolExecutor.getTaskCount());
				System.out.println(" finishTask：" + (++completeCount));

			}
		}

		addThread.join();

		while (threadPoolExecutor.getPoolSize() > 0) {
			Thread.sleep(1000);
			synchronized (test) {
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
				System.out.print(simpleDateFormat.format(new Date()));
				System.out.print(" ActiveCount: " + threadPoolExecutor.getActiveCount());
				System.out.print(" poolSize: " + threadPoolExecutor.getPoolSize());
				System.out.print(" queueSize: " + threadPoolExecutor.getQueue().size());
				System.out.println(" taskCount: " + threadPoolExecutor.getTaskCount());
			}
		}

		// Tell threads to finish off.
		threadPoolExecutor.shutdown();
		// Wait for everything to finish. pool shutdown return true
		while (!threadPoolExecutor.awaitTermination(10, TimeUnit.SECONDS)) {
			//没有完成
		}
		System.out.println("complete");
	}
}
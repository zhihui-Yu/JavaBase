package com.sync;

import java.util.concurrent.TimeUnit;

/**
 * 线程中断 在获取锁资源的线程是不起作用的(等待态线程不起作用)
 * @author listener
 *
 */
public class Test5 implements Runnable {

	public synchronized void method() {
		System.out.println(Thread.currentThread().getName()+"method run ....");
	}

	/**
     * 在构造器中创建新线程并启动获取对象锁
     */
    public Test5() {
    	System.out.println("Test5 init....");
        //初始化时，创建线程 并执行
        new Thread() {
            public void run() {
            	System.out.println(Thread.currentThread().getName()+": run ...");
            	method();
            	//放弃锁
            	try {
					wait();
				} catch (InterruptedException e) {
					System.out.println(Thread.currentThread().getName()+": interrupt ...");
				}
            }
        }.start();
    }
    
    @Override
	public void run() {
		System.out.println(Thread.currentThread().getName()+" run ....");
		// 中断判断
		while (true) {
			if (Thread.interrupted()) {
				System.out.println("中断线程!!");
				break;
			} else {
				method();
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Test5 sync = new Test5();
		Thread t = new Thread(sync);
		// 再创建的对象在运行时，获取不到锁资源
		t.start();
		//睡1秒
		TimeUnit.SECONDS.sleep(1);
		// 中断线程,无法生效
		t.interrupt();
		System.out.println("end");
	}

}

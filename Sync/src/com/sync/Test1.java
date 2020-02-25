package com.sync;

/**
 * 修饰方法
 * @author listener
 *
 */
public class Test1 implements Runnable {
	// 共享资源(临界资源)
	static int i = 0;

	/**
	 * synchronized 修饰实例方法
	 * 锁住的是实例，所以同一个实例才有同步效果
	 */
	public synchronized void increase() {
		i++;
	}
	/**
	 * 修饰静态方法
	 * 锁住的是类对象(class 文件) 不同实例都是用了同样的static
	 * 所以可以同步
	 */
	public static synchronized void increase2() {
		i++;
	}
	@Override
	public void run() {
		for (int j = 0; j < 1000000; j++) {
			increase2();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(new Test1());
		Thread t2 = new Thread(new Test1());
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println(i);
	}
	/**
	 * 输出结果: 2000000
	 */
}

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
			increase();
			//increase2();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Test1 test = new Test1();
		Test1 test2 = new Test1();
		Thread t1 = new Thread(test);
		Thread t2 = new Thread(test);
		//不同对象调用 同步方法不可以，静态同步方法就可以，因为静态同步方法 锁住的class
		//Thread t3 = new Thread(test2);
		t1.start();
		t2.start();
		//等待执行
		t1.join();
		t2.join();
		System.out.println(i);
	}
	/**
	 * 输出结果: 2000000
	 */
}

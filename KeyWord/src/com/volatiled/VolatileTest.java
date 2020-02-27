package com.volatiled;

/**
 * volatile 可见性，有序性，没有原子性
 * 
 * 当一个线程修改该关键字修饰的变量时，其他线程会立马知道新的值：可见性(存储在内存中)
 * 禁止重排序，让虚拟机禁止把该变量重排序，让比该变量之前执行的一定会执行，之后的也一样在该变量之后执行:有序性
 * 没有原子性: 假设线程1读取存值，准备加1时，线程2也读取值加1，但是线程2提交的比较早，所以内存的值是1，线程1也提交了，这时内存的值还是1。
 * @author listener
 *
 */
public class VolatileTest {
	volatile int i;

	public void addI() {
		i++;
	}

	public static void main(String[] args) throws InterruptedException {
		final VolatileTest test = new VolatileTest();
		for (int n = 0; n < 1000; n++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					test.addI();
				}
			}).start();
		}
		Thread.sleep(10000);// 等待10秒，保证上面程序执行完成
		System.out.println(test.i);
	}
}

package com.sync;

/**
 * 重入性：线程已经用户锁了，再次请求锁对象，则算重入，但是请求还是会成功。
 *
 * @author listener
 */
public class Test4 implements Runnable {
    static Test4 instance = new Test4();
    static int i = 0;
    static int j = 0;

    @Override
    public void run() {
        for (int j = 0; j < 1000000; j++) {

            // this,当前实例对象锁
            synchronized (this) {
                i++;
                increase();// synchronized的可重入性
            }
        }
    }

    /**
     * 静态 则所有对象都会同步
     */
    public static synchronized void increase() {
        j++;
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }
}

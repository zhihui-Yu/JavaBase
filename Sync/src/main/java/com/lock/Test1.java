package com.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * lock 的用法
 *
 * @author listener
 */
public class Test1 {
    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                shareResource.print5();
            }
        }, "Thread-0").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                shareResource.print10();
            }
        }, "Thread-1").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                shareResource.print15();
            }
        }, "Thread-2").start();

    }
}

//condition.singal 唤醒一个等待线程。
class ShareResource {
    private int number = 1; // A:1, B:2, C:3
    private Lock lock = new ReentrantLock();
    private Condition conditionA = lock.newCondition();
    private Condition conditionB = lock.newCondition();
    private Condition conditionC = lock.newCondition();

    public void print5() {
        try {
            //上锁
            lock.lock();
            //如果不等  则等待
            while (number != 1) {
                conditionA.await();
            }

            System.out.println(Thread.currentThread().getName());

            number++;
            //唤醒一个等待线程
            conditionB.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //解锁
            lock.unlock();
        }
    }

    public void print10() {
        try {
            lock.lock();
            while (number != 2) {
                conditionB.await();
            }

            System.out.println(Thread.currentThread().getName());

            number++;
            conditionC.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print15() {
        try {
            lock.lock();
            while (number != 3) {
                conditionC.await();
            }

            System.out.println(Thread.currentThread().getName());

            number = 1;
            conditionA.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

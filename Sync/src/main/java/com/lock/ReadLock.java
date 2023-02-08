package com.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author simple
 */
public class ReadLock {
    static int num = 0;
    public static void main(String[] args) throws InterruptedException {
        ReadWriteLock lock = new ReentrantReadWriteLock(true);
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                lock.readLock();
                System.out.println("before add -- " + Thread.currentThread().getName() + ": " + num);
                Lock writeLock = lock.writeLock();
                writeLock.lock();
                num ++;
                System.out.println("after add -- " + Thread.currentThread().getName() + ": " + num);
                writeLock.unlock();
            }).start();
        }
        System.out.println(Thread.currentThread().getPriority());
    }
}

package com.lock;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author simple
 */
public class ReadLock {
    public static void main(String[] args) throws InterruptedException {
        ReadWriteLock lock = new ReentrantReadWriteLock(true);
        AtomicInteger num = new AtomicInteger();
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                lock.readLock();
                System.out.println("before add -- " + Thread.currentThread().getName() + ": " + num);
                Lock writeLock = lock.writeLock();
                writeLock.lock();
                num.addAndGet(1);
                System.out.println("after add -- " + Thread.currentThread().getName() + ": " + num);
                writeLock.unlock();
            }).start();
        }
        System.out.println(Thread.currentThread().getPriority());
    }
}

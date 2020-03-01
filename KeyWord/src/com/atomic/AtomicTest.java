package com.atomic;

import java.util.concurrent.atomic.AtomicInteger;
 
/**
 * JDK 原子类测试
 * 
 * 利用CAS无锁算法实现原子性
 * 
 * CAS： 内存值和旧值比较，相同就可以加1，不同就重试。
 * @author listener
 *
 */
public class AtomicTest {
 
//    private int count = 0;
//    
//    public int getAndIncrement() {
//        return count++;
//    }
    
    private AtomicInteger count = new AtomicInteger(0);
    
    public int getAndIncrement() {
    	//底层是CAS方法
        return count.getAndIncrement();
    }
    
    public static void main(String[] args) {
        final AtomicTest test = new AtomicTest();
        //创建三个线程
        for (int i = 0; i <3; i++) {
            new Thread(){
                @Override
                public void run() {
                	//自加10次
                    for (int j = 0; j <10; j++) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(Thread.currentThread().getName() + " 获取递增值：" + test.getAndIncrement());
                    }
                }
            }.start();
        }
    }
    
    
}
package com.CycleAndCount;

import java.util.Set;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CyclicBarrier �����̶߳����˲�ִ��
 * �߳��ж����쳣
 * ���������߳�
 * @author listener
 *
 */
public class CyclicTest implements Runnable{
	//������ʼ��3���̵߳��̳߳�
    private ExecutorService                    threadPool     = Executors.newFixedThreadPool(3);
    //����3��CyclicBarrier����,ִ�����ִ�е�ǰ���run����
    private CyclicBarrier                      cb             = new CyclicBarrier(3, this);
    //����ÿ��ѧ����ƽ���ɼ�
    private ConcurrentHashMap<String, Integer> map            = new ConcurrentHashMap<>();

    private void count() {
        for (int i = 0; i < 3; i++) {
            threadPool.execute(() -> {
                //����ÿ��ѧ����ƽ���ɼ�,������()����Ϊ60~100�������
                int score = (int) (Math.random() * 40 + 60);
                try {
                    Thread.sleep(Math.round(Math.random() * 1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                map.put(Thread.currentThread().getName(), score);
                System.out.println(Thread.currentThread().getName() + "ͬѧ��ƽ���ɼ�Ϊ" + score);
                try {
                    //ִ��������await(),�ȴ�����ѧ��ƽ���ɼ����������
                    cb.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }
        threadPool.shutdown();
    }

    @Override
    public void run() {
        int result = 0;
        Set<String> set = map.keySet();
        for (String s : set) {
            result += map.get(s);
        }
        System.out.println("����ƽ���ɼ�Ϊ:" + (result / 3) + "��");
    }

    public static void main(String[] args) throws InterruptedException {
        long now = System.currentTimeMillis();
        CyclicTest cb = new CyclicTest();
        cb.count();
        Thread.sleep(100);
        long end = System.currentTimeMillis();
        System.out.println(end - now);
    }

}

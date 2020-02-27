package com.CycleAndCount;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CountDownLatchִ��һ����һ��
 * �������߳�
 * @author listener
 *
 */
public class CountDownLatchTest implements Runnable{
 	//������ʼ��3���̵߳��̳߳�
    private ExecutorService                    threadPool     = Executors.newFixedThreadPool(3);
    //����ÿ��ѧ����ƽ���ɼ�
    private ConcurrentHashMap<String, Integer> map            = new ConcurrentHashMap<>();
    //����
    private CountDownLatch                     countDownLatch = new CountDownLatch(3);

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
                countDownLatch.countDown();
            });
        }
        this.run();
        threadPool.shutdown();
    }

    @Override
    public void run() {
        try {
        	//�� count = 0 ���̱߳�����
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int result = 0;
        Set<String> set = map.keySet();
        for (String s : set) {
            result += map.get(s);
        }
        System.out.println("����ƽ���ɼ�Ϊ:" + (result / 3) + "��");
    }

    public static void main(String[] args) throws InterruptedException {
        long now = System.currentTimeMillis();
        CountDownLatchTest cd = new CountDownLatchTest();
        cd.count();
        Thread.sleep(100);
        long end = System.currentTimeMillis();
        System.out.println(end - now);
    }

}

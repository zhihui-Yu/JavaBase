package com.volatiled;

/**
 * @author simple
 */
public class Test2 {
    // 多线程验证volatile的内存可见性， 有时候线程太快了导致修改没感觉，
    public static void main(String[] args) throws InterruptedException {
        Cat cat = new Cat();
        cat.id = 1;
        cat.name = "cat 1";

        Thread t1 = new Thread(() -> {
            while (true) {
                System.out.println();
                if (cat.id == 1) {
                    System.out.println(Thread.currentThread().getName() + "======= in handle");
                    cat.id = 2;
                    cat.name = "cat 2";
                    break;
                }
            }

            System.out.println(Thread.currentThread().getName() + "======== handle over");
        },"cat-1");

        Thread t2 = new Thread(() -> {
            while (true) {

                System.out.println("---");
                System.out.println(cat.id);
                if (cat.id == 2) {
                    System.out.println("cat-2======= in handle");
                    cat.id = 3;
                    cat.name = "cat 3";
                    break;
                }

                // while 空轮询太多
                try {
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("cat-2======== handle over");
        },"cat-2");


        t2.start();
        t1.start();
        t1.join();
        t2.join();

        System.out.println("MAIN over");
    }

    public static class Cat {
        public volatile int id;
        public String name;
    }
}

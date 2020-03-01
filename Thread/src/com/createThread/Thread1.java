package com.createThread;

//继承Thread类来创建线程
public class Thread1 {

  public static void main(String[] args) {
      //设置线程名字
      Thread.currentThread().setName("main thread");
      MyThread myThread = new MyThread();
      myThread.setName("子线程:");
      //开启线程
      myThread.start();
      for(int i = 0;i<5;i++){
          System.out.println(Thread.currentThread().getName() + i);
      }
  }
}

class MyThread extends Thread{
  //重写run()方法
  public void run(){
      for(int i = 0;i < 10; i++){
          System.out.println(Thread.currentThread().getName() + i);
      }
  }
}
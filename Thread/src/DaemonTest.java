/**
 * 守护线程只有当所有线程都结束了才会退出。
 * @author listener
 *
 */
public class DaemonTest {
    public static void main(String[] args){
        System.out.println("main begin....");
        Thread thread = new MyThread();
        thread.setName("线程A");
        thread.setDaemon(true);
        thread.start();
        System.out.println("main end.....");
    }
}
class MyThread extends Thread{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"   begin....");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"   end.....");
    }
}


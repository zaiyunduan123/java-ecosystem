package lock.reentrant;

/**
 * Created by jiangyunxiong on 2018/6/19.
 */
public class SynchronizedTest implements Runnable {

    public synchronized void get() {
        System.out.println("get:" + Thread.currentThread().getName());
        set();
    }

    public synchronized void set() {
        System.out.println("set:" + Thread.currentThread().getName());
    }


    @Override
    public void run() {
        get();
    }

    public static void main(String[] args) {
        SynchronizedTest ss = new SynchronizedTest();
        for (int i = 0; i < 5; i++) {
            new Thread(ss).start();
        }
    }
}

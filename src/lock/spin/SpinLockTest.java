package lock.spin;


/**
 * Created by jiangyunxiong on 2018/6/19.
 * * 自旋锁
 * <p>
 * 自旋锁是采用让当前线程不停地在循环体内执行，当循环的条件被其他线程改变时才能进入临界区
 */
public class SpinLockTest implements Runnable {
    private SpinLock spinLock;

    public SpinLockTest(SpinLock spinLock) {
        this.spinLock = spinLock;
    }

    @Override
    public void run() {
        //尝试获取锁
        spinLock.lock();
        String name = Thread.currentThread().getName();
        System.out.println(name + "已经获得锁！");
        // 模拟业务处理
        try {
            Thread.currentThread().sleep(1500);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        spinLock.unLock();
        System.out.println(name + "处理完毕，并释放锁！");
    }

    public static void main(String[] args) {
        SpinLock spinLock = new SpinLock();
        SpinLockTest task = new SpinLockTest(spinLock);
        for (int i = 1; i < 10; i++) {
            new Thread(task).start();
        }
    }
}

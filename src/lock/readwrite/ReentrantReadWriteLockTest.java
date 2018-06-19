package lock.readwrite;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by jiangyunxiong on 2018/6/19.
 */
public class ReentrantReadWriteLockTest {

    public static void main(String[] args) {
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        //开启20个读任务
        for (int i = 1; i <= 20; i++) {
            new Thread(new ReadTask(lock)).start();
        }

        //开启20个写任务
        for (int i= 1;i<=15;i++){
            new Thread(new WriteTask(lock)).start();
        }
    }
}

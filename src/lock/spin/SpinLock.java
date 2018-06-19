package lock.spin;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by jiangyunxiong on 2018/6/19.
 * 自旋锁
 *
 * 自旋锁是采用让当前线程不停地在循环体内执行，当循环的条件被其他线程改变时才能进入临界区
 */
public class SpinLock {

    private AtomicBoolean ab = new AtomicBoolean(false);

    /**
     * getAndSet原子操作来判断锁状态并尝试获取锁
     * 缺点：getAndSet底层使用CAS来实现，一直在修改共享变量的值，会引发缓存一致性流量风暴
     */
    public void lock() {
        while (ab.getAndSet(true)) {
        }
    }

    /**
     * 释放锁
     */
    public void unLock() {
        ab.set(false);
    }
}

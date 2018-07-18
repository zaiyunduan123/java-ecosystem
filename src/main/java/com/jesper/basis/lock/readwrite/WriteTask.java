package com.jesper.basis.lock.readwrite;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by jiangyunxiong on 2018/6/19.
 *
 * 写任务
 */
public class WriteTask implements Runnable {

    private ReentrantReadWriteLock lock;

    public WriteTask(ReentrantReadWriteLock lock) {
        this.lock = lock;
    }

    @Override
    public void run() {

        String name = Thread.currentThread().getName();
        System.out.println(name + " 尝试请求write锁......");
        ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
        writeLock.lock();
        System.out.println(name + "已拿到write锁, 开始处理业务");

        // 模拟业务处理
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        writeLock.unlock();
        System.out.println(name + "释放write锁....");
    }
}

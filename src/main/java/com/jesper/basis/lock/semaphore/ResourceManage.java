package com.jesper.basis.lock.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by jiangyunxiong on 2018/6/20.
 */
public class ResourceManage {

    private final Semaphore semaphore;
    private boolean resourceArray[];
    private final ReentrantLock lock;

    public ResourceManage() {
        this.resourceArray = new boolean[10];
        this.semaphore = new Semaphore(10, true);// 控制10个共享资源的使用，使用先进先出的公平模式进行共享;公平模式的信号量，先来的先获得信号量
        this.lock = new ReentrantLock(true);// 公平模式的锁，先来的先选
        for (int i = 0; i < 10; i++) {
            resourceArray[i] = true;
        }
    }

    public void useResource(int userId) {
        try {
            semaphore.acquire();
            int id = getResourceId();//占到一个坑
            System.out.println("userId:" + userId + "正在使用资源，资源id：" + id + "\n");
            Thread.sleep(100);// do something，相当于于使用资源
            resourceArray[id] = true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();//释放信号量，计数器加1
        }
    }

    private int getResourceId() {
        int id = -1;
        lock.lock();
        try {
            for (int i = 0; i < 10; i++) {
                if (resourceArray[i]) {
                    resourceArray[i] = false;
                    id = i;
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return id;
    }
}

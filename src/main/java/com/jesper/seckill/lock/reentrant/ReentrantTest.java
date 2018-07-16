package com.jesper.seckill.lock.reentrant;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by jiangyunxiong on 2018/6/19.
 *
 * 可重入锁
 *
 * ReentrantLock 和synchronized 都是 可重入锁
 */
public class ReentrantTest implements Runnable {

   ReentrantLock lock = new ReentrantLock();

   public void get(){
       lock.lock();
       System.out.println("get:" + Thread.currentThread().getName());
       set();
       lock.unlock();
   }
   public void set(){
       lock.lock();
       System.out.println("set:" + Thread.currentThread().getName());
       lock.unlock();
   }

    @Override
    public void run() {
        get();
    }

    public static void main(String[] args){
        ReentrantTest ss = new ReentrantTest();
        for (int i = 0; i < 5; i++) {
            new Thread(ss).start();
        }
    }
}

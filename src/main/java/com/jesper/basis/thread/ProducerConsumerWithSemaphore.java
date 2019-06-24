package com.jesper.basis.thread;

import java.util.concurrent.Semaphore;

/**
 * @Author jiangyunxiong
 * @Date 2019/6/25 12:37 AM
 */
public class ProducerConsumerWithSemaphore {
    private static Integer count = 0;
    //创建三个信号量
    final Semaphore notFull = new Semaphore(10);
    final Semaphore notEmpty = new Semaphore(0);
    //mutex信号量，维护生产者消费者之间的同步关系，保证生产者和消费者之间的交替进行
    final Semaphore mutex = new Semaphore(1);

    class Producer implements Runnable {
        @Override
        public void run() {
            for (int i=0;i<10;i++){
                try{
                    notFull.acquire();
                    mutex.acquire();
                    count++;
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    mutex.release();
                    notEmpty.release();
                }
            }
        }
    }

    class Consumer implements Runnable {
        @Override
        public void run() {
            for (int i=0;i<10;i++){
                try{
                    notEmpty.acquire();
                    mutex.acquire();
                    count--;
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    mutex.release();
                    notFull.release();
                }
            }
        }
    }
}

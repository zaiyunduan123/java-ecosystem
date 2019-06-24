package com.jesper.basis.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @Author jiangyunxiong
 * @Date 2019/6/25 12:32 AM
 */
public class ProducerConsumerWithBlockingQueue {

    private static Integer count =0;

    final BlockingQueue blockingQueue = new ArrayBlockingQueue(10);

    class Producer implements Runnable{

        @Override
        public void run() {
            for (int i=0;i<10;i++){
                try {
                    blockingQueue.put(1);
                    count++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class Consumer implements Runnable{
        @Override
        public void run() {
            for (int i=0;i<10;i++){
                try {
                    blockingQueue.take();
                    count--;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}

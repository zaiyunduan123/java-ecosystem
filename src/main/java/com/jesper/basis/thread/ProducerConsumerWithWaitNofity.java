package com.jesper.basis.thread;

/**
 * @Author jiangyunxiong
 * @Date 2019/6/24 11:48 PM
 */
public class ProducerConsumerWithWaitNofity {

    private static Integer count = 0;
    private static final Integer FULL = 10;
    private static String LOCK = "lock";

    class Producer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                synchronized (LOCK) {
                    while (count == FULL) {
                        try {
                            LOCK.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    count++;
                    LOCK.notifyAll();
                }
            }
        }
    }

    class Consumer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                synchronized (LOCK) {
                    while (count == 0) {
                        try {
                            LOCK.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    count--;
                    LOCK.notifyAll();
                }
            }
        }
    }
}

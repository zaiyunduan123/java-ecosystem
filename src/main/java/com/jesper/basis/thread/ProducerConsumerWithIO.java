package com.jesper.basis.thread;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * @Author jiangyunxiong
 * @Date 2019/6/25 12:44 AM
 */
public class ProducerConsumerWithIO {

    //用生产者线程往管道输出流中写入数据，消费者在管道输入流中读取数据
    final PipedInputStream pis = new PipedInputStream();
    final PipedOutputStream pos = new PipedOutputStream();

    {
        try {
            pis.connect(pos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class Producer implements Runnable {
        @Override
        public void run() {
            try {
                while (true) {
                    int num = (int) (Math.random() * 255);
                    pos.write(num);
                    pos.flush();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    pos.close();
                    pis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class Consumer implements Runnable {
        @Override
        public void run() {
            try {
                while (true) {
                    int num = (int) (Math.random() * 255);
                    int read = pis.read();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    pos.close();
                    pis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

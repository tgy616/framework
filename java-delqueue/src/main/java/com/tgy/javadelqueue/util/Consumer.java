package com.tgy.javadelqueue.util;

import java.util.concurrent.DelayQueue;

/**
 * 消息消费者
 *
 * @author DragonSwimDiving
 * @program java-delqueue
 * @Date 2019-08-12 11:11
 **/

public class Consumer implements Runnable {
    // 延时队列 ,消费者从其中获取消息进行消费
    private DelayQueue<Message> queue;

    public Consumer(DelayQueue<Message> queue) {
        this.queue = queue;
    }
    @Override
    public void run() {
        while (true) {
            try {
                Message take = queue.take();
                System.out.println("消费消息id：" + take.getId() + " 消息体：" + take.getBody());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

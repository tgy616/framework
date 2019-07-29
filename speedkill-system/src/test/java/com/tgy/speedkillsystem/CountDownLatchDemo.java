package com.tgy.speedkillsystem;

import java.util.concurrent.CountDownLatch;

/**
 * Java中的CountDownLatch
 * CountDownLatch用于确保任务在启动之前等待其他线程。要了解它的应用程序，让我们考虑一个服务器，其中主要任务只能在所有必需的服务启动时启动。
 * <p>
 * 使用CountDownLatch：
 * 当我们创建CountDownLatch的对象时，我们指定它应该等待的线程数，所有这样的线程都需要通过在完成或准备好工作后
 * 调用CountDownLatch.countDown（）来倒计时。一旦计数达到零，等待任务就开始运行。
 *
 * @author DragonSwimDiving
 * @program speedkill-system
 * @Date 2019-07-29 14:11
 **/

public class CountDownLatchDemo {
    public static void main(String args[]) throws InterruptedException {
        // Let us create task that is going to
        // wait for four threads before it starts
        CountDownLatch latch = new CountDownLatch(4);

        // Let us create four worker
        // threads and start them.
        Worker first = new Worker(1000, latch,
                "WORKER-1");
        Worker second = new Worker(2000, latch,
                "WORKER-2");
        Worker third = new Worker(3000, latch,
                "WORKER-3");
        Worker fourth = new Worker(4000, latch,
                "WORKER-4");
        first.start();
        second.start();
        third.start();
        fourth.start();

        // The main task waits for four threads
        latch.await();

        // Main thread has started
        System.out.println(Thread.currentThread().getName() +
                " has finished");
    }

}

// A class to represent threads for which
// the main thread waits.
class Worker extends Thread {
    private int delay;
    private CountDownLatch latch;

    public Worker(int delay, CountDownLatch latch, String name) {
        super(name);
        this.delay = delay;
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(delay);
            latch.countDown();
            System.out.println(Thread.currentThread().getName() + " finished");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
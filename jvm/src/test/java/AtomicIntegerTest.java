import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 关于Java中CompareAndSwap（CAS）的一些理解
 *
 * 在这个例子中，我们开启了10个线程，来增加count的值，期待最后输出的结果是10000。
 * 显然，并不是每次运行的结果都是10000。因为多个线程对count的修改操作并不是原子操作。
 *
 * @author DragonSwimDiving
 * @program jvm
 * @Date 2019-10-10 10:25
 **/

public class AtomicIntegerTest {
    /**
     * 会出现线程不安全问题
     */
    /*private static int threadCount = 10;
    private static CountDownLatch countDown = new CountDownLatch(threadCount);
    private static int count = 0;

    public static void main(String[] args) {
        Thread[] threads = new Thread[threadCount];
        for (int i = 0; i < threadCount; i++) {
            threads[i] = new Thread(new Counter());
        }
        for (int i = 0; i < threadCount; i++) {
            threads[i].start();
        }
        try {
            countDown.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("count=" + count);
    }

    private static class Counter implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                count++;
            }
            countDown.countDown();
        }
    }*/
    /**
     * 解决办法 使用AtomicInteger
     */
    private static int threadCount = 10;
    private static CountDownLatch countDown = new CountDownLatch(threadCount);
    private static AtomicInteger count = new AtomicInteger(0);//原子操作类

    public static void main(String[] args) {
        Thread[] threads = new Thread[threadCount];
        for (int i = 0; i < threadCount; i++) {
            threads[i] = new Thread(new Counter());
        }
        for (int i = 0; i < threadCount; i++) {
            threads[i].start();
        }
        try {
            countDown.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("count=" + count);
    }

    private static class Counter implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                //原子操作
                count.getAndIncrement();
            }
            countDown.countDown();
        }
    }
}

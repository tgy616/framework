import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 测试jdk1.7 多线程环境下HashMap容易出现死循环
 *
 * @author DragonSwimDiving
 * @program jvm
 * @Date 2020-03-31 10:23
 **/

public class TestHashmapDeadForeach {
    public static void main(String[] args) {
        HashMapThread thread0 = new HashMapThread();
        HashMapThread thread1 = new HashMapThread();
        HashMapThread thread2 = new HashMapThread();
        HashMapThread thread3 = new HashMapThread();
        HashMapThread thread4 = new HashMapThread();
        thread0.start();
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }
}
class HashMapThread extends Thread {
    private static AtomicInteger ai = new AtomicInteger();
    private static Map<Integer, Integer> map = new HashMap<>();

    @Override
    public void run() {
       while (ai.get() < 1000000) {
            map.put(ai.get(), ai.get());
            ai.incrementAndGet();
        }
    }
}
package com.tgy.loadbalance;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * 单例模式
 * @author DragonSwimDiving
 * @program loadbalance
 * @Date 2019-07-25 11:17
 **/

public class LazyLoadBalancer {
    private static LazyLoadBalancer loadBalancer;
    private List<String> servers = null;

//    private LazyLoadBalancer() {
//        servers = new ArrayList<>();
//    }

    public void addServer(String server) {
        servers.add(server);
    }

    public String getServer() {
        Random random = new Random();
        int i = random.nextInt(servers.size());
        return servers.get(i);
    }
    //方法一，懒汉模式 单一检查（懒汉模式）非线程安全
    /**在单线程环境一切正常，balancer1和balancer2两个对象的hashCode一模一样，由此可以判断出堆栈中只有一份内容，
     * 不过该代码块中存在线程安全隐患，因为缺乏竞争条件，多线程环境资源竞争的时候就显得不太乐观了，请看代码注释内容
     * */
   /* public static LazyLoadBalancer getInstance() {
        // 第一步：假设T1,T2两个线程同时进来且满足 loadBalancer == null
        if (loadBalancer == null) {
            // 第二步：那么 loadBalancer 即会被实例化2次
            loadBalancer = new LazyLoadBalancer();
        }
        return loadBalancer;
    }
*/

    //方法二 ：无脑上锁（懒汉）线程安全，性能较差，第一种升级版
    /**毫无疑问，知道synchronized关键字的都知道，同步方法在锁没释放之前，其它线程都在排队候着呢，想不安全都不行啊，
     * 但在安全的同时，性能方面就显得短板了，我就初始化一次，你丫的每次来都上个锁，不累的吗（没关系，它是为了第三种做铺垫的）..
     * */
   /* public synchronized static LazyLoadBalancer getInstance() {
        if (loadBalancer == null) {
            loadBalancer = new LazyLoadBalancer();
        }
        return loadBalancer;
    }*/

   //方法三：双重查锁（DCL）完全就是前两种的结合体，只是将同步方法升级成同步代码块
    //划重点了 **volatile**
    /**假设new LazyLoadBalancer()加载内容过多
     因重排而导致loadBalancer提前不为空
     正好被其它线程观察到对象非空直接返回使用  一种罕见的单例空指针突然来袭
     存在问题： 首先我们一定要清楚，DCL是不能保证线程安全的，稍微了解过JVM的就清楚，对比C/C++它始终缺少一个正式的内存模型，
     所以为了提升性能，它还会做一次指令重排操作，这个时候就会导致loadBalancer提前不为空，正好被其它线程观察到对象非空直接返回使用（但实际还有部分内容没加载完成）
     解决方案： 用volatile修饰loadBalancer，因为volatile修饰的成员变量可以确保多个线程都能够顺序处理，它会屏蔽JVM指令重排带来的性能优化。
     * */
  /*  private volatile static LazyLoadBalancer loadBalancer;

    public static LazyLoadBalancer getInstance() {
        if (loadBalancer == null) {
            synchronized (LazyLoadBalancer.class) {
                if (loadBalancer == null) {
                    loadBalancer = new LazyLoadBalancer();
                }
            }
        }
        return loadBalancer;
    }*/
    //方法四：静态内部类 （懒汉）线程安全，推荐使用
    private LazyLoadBalancer() {}

    private static class LoadBalancerHolder {
        //在JVM中 final 对象只会被实例化一次,无法修改
        private final static LazyLoadBalancer INSTANCE = new LazyLoadBalancer();
    }

    public static LazyLoadBalancer getInstance() {
        return LoadBalancerHolder.INSTANCE;
    }


    public static void main(String[] args) {
        LazyLoadBalancer balancer1 = LazyLoadBalancer.getInstance();
        LazyLoadBalancer balancer2 = LazyLoadBalancer.getInstance();
        System.out.println("hashCode："+balancer1.hashCode());
        System.out.println("hashCode："+balancer2.hashCode());
        balancer1.addServer("Server 1");
        balancer2.addServer("Server 2");
        IntStream.range(0, 5).forEach(i -> System.out.println("转发至：" + balancer1.getServer()));
    }
}

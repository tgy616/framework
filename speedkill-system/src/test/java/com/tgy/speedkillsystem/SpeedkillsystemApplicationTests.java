package com.tgy.speedkillsystem;

import com.google.common.util.concurrent.RateLimiter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

@RunWith(SpringRunner.class)
@Component
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpeedkillsystemApplicationTests {

    RestTemplate restTemplate = new RestTemplate();

    /**
     * @LocalServerPort 提供了 @Value("${local.server.port}") 的代替
     */
    @LocalServerPort
    private int port;

    private URL base;

    @Before
    public void setUp() throws Exception {
        String url = String.format("http://localhost:%d/", port);
        System.out.println(String.format("port is : [%d]", port));
        this.base = new URL(url);

        //测试nginx的正常请求和限流请求
        url_nginx = "http://127.0.0.1:"+port+"/nginx";
        //测试数据库-无锁
        url_nolock = "http://127.0.0.1:"+port+"/seckill";
        //测试乐观锁
        url_optimistic = "http://127.0.0.1:"+port+"/seckillOptimisticLock";
        //测试带重试的乐观锁
        url_optimisticWithRetry = "http://127.0.0.1:"+port+"/seckillOptimisticLockretry";
        //测试悲观锁
        url_pessimistic = "http://127.0.0.1:"+port+"/seckillPessimisticLock";
        //使用redis原子操作保障原子性
        url_redis = "http://127.0.0.1:"+port+"/seckillRedis";
    }

    //测试nginx的正常请求和限流请求
    String url_nginx = "http://127.0.0.1:8080/nginx";
    //测试数据库-无锁
    String url_nolock = "http://127.0.0.1:8080/seckill";
    //测试乐观锁
    String url_optimistic = "http://127.0.0.1:8080/seckillOptimisticLock";
    //测试带重试的乐观锁
    String url_optimisticWithRetry = "http://127.0.0.1:8080/seckillOptimisticLockretry";
    //测试悲观锁
    String url_pessimistic = "http://127.0.0.1:8080/seckillPessimisticLock";
    //使用redis原子操作保障原子性
    String url_redis = "http://127.0.0.1:8080/seckillRedis";


    //测试nginx 使用20个并发，测试购买商品使用200个并发
    private static final int amount = 200;
    //发令枪，目的是模拟真正的并发，等所有线程都准备好一起请求
    private CountDownLatch countDownLatch = new CountDownLatch(amount);

    @Test
    public void contextLoads() throws InterruptedException {
        System.out.println("开始卖："+System.currentTimeMillis());
        for (int i = 0; i < amount; i++) {
            new Thread(new Request()).start();
            countDownLatch.countDown();
        }
        Thread.currentThread().sleep(100000);
    }

    public class Request implements Runnable{

        @Override
        public void run() {
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //System.out.println(restTemplate.getForObject(url_nginx,String.class));
            //restTemplate.getForObject(url_redis,String.class);
            //无锁的情况下，200个并发抢购100个商品( private static final int amount = 200;)
            //restTemplate.getForObject(url_nolock,String.class);
            //测试悲观锁
            //restTemplate.getForObject(url_pessimistic,String.class);
            //测试乐观锁
            //restTemplate.getForObject(url_optimistic,String.class);
            //测试带重试的乐观锁
            //restTemplate.getForObject(url_optimisticWithRetry,String.class);
            //测试使用redis原子操作保障原子性
            restTemplate.getForObject(url_redis,String.class);
        }
    }

    public static void main(String[] args) {
        /*//每秒只发出2个令牌
        RateLimiter rateLimiter = RateLimiter.create(2);
        //一次取5个
        System.out.println(rateLimiter.acquire(5));
        //一次取2个
        System.out.println(rateLimiter.acquire(2));
        //一次取一个
        System.out.println(rateLimiter.acquire(1));*/
        //限流服务封装到一个类中AccessLimitService，提供tryAcquire()方法，用来尝试获取令牌，返回true表示获取到
        System.out.println("guava:"+access());

    }

    public static boolean accessLimit(){
        //每秒只发出5个令牌
        RateLimiter rateLimiter = RateLimiter.create(5.0);
        //尝试获取令牌
        return rateLimiter.tryAcquire();
    }
    public static String access(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //尝试获取令牌
        if(accessLimit()){
            //模拟业务执行500毫秒
            try {
                Thread.sleep(500);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            return "aceess success [" + sdf.format(new Date()) + "]";
        }else{
            return "aceess limit [" + sdf.format(new Date()) + "]";
        }
    }
}

package com.tgy.speedkillsystem.service.impl;

import com.tgy.speedkillsystem.entity.Goods;
import com.tgy.speedkillsystem.mapper.OrderMapper;
import com.tgy.speedkillsystem.service.GoodsService;
import com.tgy.speedkillsystem.service.OrderService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author DragonSwimDiving
 * @program speedkill-system
 * @Date 2019-07-26 13:59
 **/
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public int insertOrder(String name, String createTime) {
        return orderMapper.insertOrder(name,createTime);
    }

    @Autowired
    private GoodsService goodsService;

    @Resource
    private SqlSessionFactory sqlSessionFactory;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 悲观锁
     * @return
     */
    @Override
    public void seckillPessimism() throws Exception {
        //悲观锁begin
        SqlSession sqlSession = sqlSessionFactory.openSession(false);
        sqlSession.getConnection().setAutoCommit(false);

        //查询库存，如果库存大于0，则继续秒杀逻辑
        Goods goods = goodsService.getGoods();
        if (null != goods && goods.getCount() <= 0) {
            System.out.println(Thread.currentThread().getName() + "悲观锁方式商品卖光了！！！当前时间：" + System.currentTimeMillis());
            return;
        }

        //库存-1，销量+1
        Goods goodsForUpdate = new Goods();
        goodsForUpdate.setCount(goods.getCount()-1);
        goodsForUpdate.setSale(goods.getSale()+1);
        goodsForUpdate.setId(1);
        int i = goodsService.updateGoodsCount(goodsForUpdate);

        //当库存更新成功后创建订单
        if(i>0){
            //创建订单
            String time = System.currentTimeMillis()+"";
            String custname = "zhangsan"+time.substring(8,time.length());
            String createTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            insertOrder(custname,createTime);
        }

        sqlSession.getConnection().commit();
    }

    @Override
    public void seckillOptimistic() {
        //查询库存，如果库存大于0，则继续秒杀逻辑
        Goods goods = goodsService.getGoods();
        if (null != goods && goods.getCount() <= 0) {
            System.out.println(Thread.currentThread().getName() + "乐观锁方式商品卖光了！！！当前时间：" + System.currentTimeMillis());
            return;
        }
        int currentVersion = goods.getVersion();
        Goods goodsForUpdate = new Goods();
        goodsForUpdate.setVersion(currentVersion);
        goodsForUpdate.setCount(goods.getCount()-1);
        goodsForUpdate.setSale(goods.getSale()+1);
        goodsForUpdate.setId(1);
        int i = goodsService.updateGoodsCountOptimisticLock(goodsForUpdate,currentVersion);

        //当库存更新成功后创建订单
        if(i>0){
            String time = System.currentTimeMillis()+"";
            String custname = "zhangsan"+time.substring(8,time.length());
            String createTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            insertOrder(custname,createTime);
        }
    }


    /**
     * 会重试的乐观锁
     * @return
     */
    @Override
    public int seckillWithOptimistic() {

        //查询库存，如果库存大于0，则继续秒杀逻辑
        Goods goods = goodsService.getGoods();
        if (null != goods && goods.getCount() <= 0) {
            System.out.println(Thread.currentThread().getName() + "乐观锁方式商品卖光了！！！当前时间：" + System.currentTimeMillis());
            return -1;
        }
        int currentVersion = goods.getVersion();
        Goods goodsForUpdate = new Goods();
        goodsForUpdate.setVersion(currentVersion);
        goodsForUpdate.setCount(goods.getCount()-1);
        goodsForUpdate.setSale(goods.getSale()+1);
        goodsForUpdate.setId(1);
        int i = goodsService.updateGoodsCountOptimisticLock(goodsForUpdate,currentVersion);

        //当库存更新成功后创建订单
        if(i>0){
            String time = System.currentTimeMillis()+"";
            String custname = "zhangsan"+time.substring(8,time.length());
            String createTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            insertOrder(custname,createTime);
            return 1;
        }else{      //乐观锁如何重试呢？
            return 0;
        }
    }

    /**
     * 无锁
     */
    @Override
    public void seckill() {
        //查询库存，如果库存大于0，则继续秒杀逻辑
        Goods goods = goodsService.getGoods();
        if (null != goods && goods.getCount() <= 0) {
            System.out.println(Thread.currentThread().getName() + "无锁方式商品卖光了！！！当前时间：" + System.currentTimeMillis());
            return;
        }

        //库存-1，销量+1
        Goods goodsForUpdate = new Goods();
        goodsForUpdate.setCount(goods.getCount()-1);
        goodsForUpdate.setSale(goods.getSale()+1);
        goodsForUpdate.setId(1);
        int i = goodsService.updateGoodsCount(goodsForUpdate);

        //当库存更新成功后创建订单
        if(i>0){
            //创建订单
            String time = System.currentTimeMillis()+"";
            String name = "zhangsan"+time.substring(8,time.length());
            String createTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            insertOrder(name,createTime);
        }

    }

    @Override
    public void seckillwithRedis() {
        String key = "seckill";     //定义一个key，key的值就是商品的数量
        long count = stringRedisTemplate.opsForValue().increment(key, -1l);
        if (count >= 0) {
            //创建订单
            String time = System.currentTimeMillis() + "";
            String name = "zhangsan" + time.substring(8, time.length());
            String createTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            insertOrder(name, createTime);
        } else {
            System.out.println("卖光了" + System.currentTimeMillis());
        }
    }
}

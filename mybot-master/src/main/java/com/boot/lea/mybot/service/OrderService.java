package com.boot.lea.mybot.service;

/**
 * @Title: OrderService.java
 * @Package com.boot.lea.mybot.service
 * @Description: TODO(用一句话描述该文件做什么)
 * @author LiJing
 * @date 2019/8/9 15:33
 * @version v.3.0
 */

import com.boot.lea.mybot.annotation.NeedSetFiledValue;
import com.boot.lea.mybot.entity.Order;

import java.util.List;

/**
 * @ClassName: OrderService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author LiJing
 * @date 2019/8/9 15:33 
 *
 */
public interface OrderService {
    // 使用注解定在需要的方法上
    @NeedSetFiledValue
    List<Order> getAllOrder();
}

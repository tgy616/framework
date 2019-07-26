package com.tgy.speedkillsystem.service;

/**
 * @author DragonSwimDiving
 * @program speedkill-system
 * @Date 2019-07-26 13:56
 **/

import com.tgy.speedkillsystem.entity.Goods;
public interface GoodsService {
    /**
     * 减掉商品库存——悲观锁
     * @return
     */
    int updateGoodsCount(Goods goods);

    /**
     * 减掉商品库存——乐观锁
     * @return
     */
    int updateGoodsCountOptimisticLock(Goods goods,int version);

    /**
     * 查询商品
     * @return
     */
    Goods getGoods();
}
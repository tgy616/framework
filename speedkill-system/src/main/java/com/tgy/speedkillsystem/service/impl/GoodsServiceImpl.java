package com.tgy.speedkillsystem.service.impl;

import com.tgy.speedkillsystem.entity.Goods;
import com.tgy.speedkillsystem.mapper.GoodsMapper;
import com.tgy.speedkillsystem.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author DragonSwimDiving
 * @program speedkill-system
 * @Date 2019-07-26 13:57
 **/
@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsMapper userMapper;

    @Override
    public int updateGoodsCount(Goods goods) {
        return userMapper.updateGoodsCount(goods);
    }

    @Override
    public int updateGoodsCountOptimisticLock(Goods goods,int version) {
        return userMapper.updateGoodsCountOptimisticLock(goods,version);
    }

    @Override
    public Goods getGoods() {
        return userMapper.getGoods();
    }
}

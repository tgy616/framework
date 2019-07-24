package com.tgy.pay.service.impl;

import com.tgy.pay.entity.UserDO;
import com.tgy.pay.mapper.UserMapper;
import com.tgy.pay.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author DragonSwimDiving
 * @program activemqdemo
 * @Date 2019-07-12 11:15
 **/
@Service
public class PayServiceImpl implements PayService {
     @Autowired
     private UserMapper mapper;

    @Override
    public String getDataBaseContection(Integer threadNumber) {
        String returnStr="fail info";
        UserDO userDO=new UserDO();
        userDO.setId(10);
        userDO.setInfo("测试Info");
        int i = mapper.updateByPrimaryKeySelective(userDO);
        if (i==1){
            returnStr="成功调用,线程数为："+threadNumber;
        }
        return returnStr;
    }
}

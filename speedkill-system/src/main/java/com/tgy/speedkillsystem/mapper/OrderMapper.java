package com.tgy.speedkillsystem.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author DragonSwimDiving
 * @program speedkill-system
 * @Date 2019-07-26 13:55
 **/
public interface OrderMapper {
    /**
     * 生成订单
     * @param name
     * @param createTime
     * @return
     */
    @Insert("INSERT INTO `tgy-interviews`.`seckill_order`(`custname`, `create_time`) VALUES (#{name}, #{createTime});")
    int insertOrder(@Param("name") String name, @Param("createTime") String createTime);

}

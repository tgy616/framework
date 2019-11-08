package com.tgy.nearbyredis;

import com.tgy.nearbyredis.utils.Coordinate;
import com.tgy.nearbyredis.utils.RedisUtil;
import redis.clients.jedis.GeoRadiusResponse;
import redis.clients.jedis.Jedis;

import java.util.List;

import static com.tgy.nearbyredis.utils.RedisUtil.geoQuery;

/**
 * 测试方法
 *
 * @author DragonSwimDiving
 * @program nearbyredis
 * @Date 2019-11-07 16:12
 **/

public class TestMethod {
    public static void main(String[] args) {
        Jedis jedis = RedisUtil.getJedis();

        //添加经纬度
        Coordinate coordinate=new Coordinate();
        coordinate.setLatitude(31.244803);  //维度
        coordinate.setLongitude(121.483671); //经度
        coordinate.setKey("1");  //用户表的id 以当前用户作为查询条件，查询他周围的人数
        List<GeoRadiusResponse> list=geoQuery(coordinate);
        for(GeoRadiusResponse geo:list){
            System.out.println(geo.getMemberByString()); //主键 有主键了个人信息就很简单了
            System.out.println(geo.getDistance());  //距离多少米
        }

        RedisUtil.close(jedis);
    }
}

package com.tgy.speedkillsystem.entity;

/**
 * 订单实体类
 *
 * @author DragonSwimDiving
 * @program speedkill-system
 * @Date 2019-07-26 13:53
 **/

public class Order {
    private int id;
    private String custname;
    private String createTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustname() {
        return custname;
    }

    public void setCustname(String custname) {
        this.custname = custname;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}

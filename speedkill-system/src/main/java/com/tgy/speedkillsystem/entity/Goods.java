package com.tgy.speedkillsystem.entity;

/**
 * 货物实体类
 *
 * @author DragonSwimDiving
 * @program speedkill-system
 * @Date 2019-07-26 13:53
 **/

public class Goods {
    private int id;
    private String name;
    private int count;
    private int sale;
    private int version;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getSale() {
        return sale;
    }

    public void setSale(int sale) {
        this.sale = sale;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}

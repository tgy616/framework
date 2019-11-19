package com.tgy.redis4likeorunlike.dto;

import lombok.Getter;
/**
 * 用户点赞状态的枚举类
 *
 * @author DragonSwimDiving
 * @program redis4likeorunlike
 * @Date 2019-11-18 14:32
 **/
@Getter
public enum  LikedStatusEnum {
    LIKE(1, "点赞"),
    UNLIKE(0, "取消点赞/未点赞"),
            ;

    private Integer code;

    private String msg;

    LikedStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}

package com.tgy.redis4likeorunlike.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 点赞统计实体类
 *
 * @author DragonSwimDiving
 * @program redis4likeorunlike
 * @Date 2019-11-18 14:34
 **/
@Getter
@Setter
public class LikedCountDTO {
    private Integer id;//用户id
    private String key;
    private Integer value;
    private Integer Count;

    public LikedCountDTO(String key, Integer value) {
        this.key = key;
        this.value = value;
    }
}

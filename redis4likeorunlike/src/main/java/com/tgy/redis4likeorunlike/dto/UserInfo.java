package com.tgy.redis4likeorunlike.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author DragonSwimDiving
 * @program redis4likeorunlike
 * @Date 2019-11-18 15:24
 **/
@Getter
@Setter
public class UserInfo {
    private String id;
    private Integer likeNum;

}

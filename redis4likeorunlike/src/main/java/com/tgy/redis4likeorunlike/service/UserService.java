package com.tgy.redis4likeorunlike.service;

import com.tgy.redis4likeorunlike.dto.UserInfo;

/**
 * @author DragonSwimDiving
 * @program redis4likeorunlike
 * @Date 2019-11-18 15:29
 **/
public interface UserService {
    UserInfo findById(Integer id);

    void updateInfo(UserInfo user);

}

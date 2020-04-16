package com.nettyRpc.server;


import com.nettyRpc.client.UserService;

/**
 *
 */
public class UserServiceImpl implements UserService {

    @Override
    public String callRpc(String param) {
        System.out.println(param);
        return param;
    }
}

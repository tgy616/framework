package com.tgy.apisafetydemo.controller;

import com.tgy.apisafetydemo.util.RSA;
import com.tgy.apisafetydemo.util.RSA2;

import java.util.Map;

/**
 * @author DragonSwimDiving
 * @program api-safety-demo
 * @Date 2019-07-30 16:31
 **/

public class UserController {

    public static void main(String[] args) throws Exception {
        Map<String,Object> keyMap=RSA2.initKey();
        String publicKeyStr = RSA2.getPublicKey(keyMap);
        String privateKeyStr = RSA2.getPrivateKey(keyMap);
        System.out.println("RSA公钥Base64编码:" + publicKeyStr);
        System.out.println("RSA私钥Base64编码:" + privateKeyStr);
    }
}

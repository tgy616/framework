package com.tgy.apisafetydemo.controller;

import com.tgy.apisafetydemo.util.RSA;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Map;

/**
 * test controller
 *
 * @author DragonSwimDiving
 * @program api-safety-demo
 * @Date 2019-07-30 11:06
 **/

public class TestController {



    public static void main(String[] args) throws Exception {
        //===============生成公钥和私钥，公钥传给客户端，私钥服务端保留==================
        //生成RSA公钥和私钥，并Base64编码
        KeyPair keyPair = RSA.getKeyPair();
        String publicKeyStr = RSA.getPublicKey(keyPair);
        String privateKeyStr = RSA.getPrivateKey(keyPair);
        System.out.println("RSA公钥Base64编码:" + publicKeyStr);
        System.out.println("RSA私钥Base64编码:" + privateKeyStr);
        //=================客户端=================
        //hello, i am infi, good night!加密
        String message = "hello, i am fine, good night!";
        //将Base64编码后的公钥转换成PublicKey对象
        PublicKey publicKey = RSA.string2PublicKey(publicKeyStr);
        //用公钥加密
        byte[] publicEncrypt = RSA.publicEncrypt(message.getBytes(), publicKey);
        //加密后的内容Base64编码
        String byte2Base64 = RSA.byte2Base64(publicEncrypt);
        System.out.println("公钥加密并Base64编码的结果：" + byte2Base64);
        //##############	网络上传输的内容有Base64编码后的公钥 和 Base64编码后的公钥加密的内容     #################
        //===================服务端================
        //将Base64编码后的私钥转换成PrivateKey对象
        PrivateKey privateKey = RSA.string2PrivateKey(privateKeyStr);
        //加密后的内容Base64解码
        byte[] base642Byte = RSA.base642Byte(byte2Base64);
        //用私钥解密
        byte[] privateDecrypt = RSA.privateDecrypt(base642Byte, privateKey);
        //解密后的明文
        System.out.println("解密后的明文: " + new String(privateDecrypt));
    }
}

package com.tgy.apisafetydemo.util;

import com.sun.org.apache.bcel.internal.classfile.Attribute;
import com.sun.org.apache.bcel.internal.classfile.Code;
import com.sun.org.apache.bcel.internal.classfile.CodeException;
import com.sun.org.apache.bcel.internal.classfile.ConstantPool;
import com.sun.xml.internal.ws.api.server.SDDocument;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import java.io.IOException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Map;

/**
 * RSA路飞版
 *
 * @author DragonSwimDiving
 * @program api-safety-demo
 * @Date 2019-07-30 15:54
 **/

public abstract class RSA2{
    public static final String KEY_ALGORITHM="RSA";
    public static final String SIGNATURE_ALGORITHM="MD5withRSA";

    private static final  String PUBLIC_KEY="RSAPublicKey";
    private static final  String PRIVATE_KEY="RSAPrivateKey";

    public static String getPublicKey(Map<String,Object> keyMap) throws Exception{
        Key key= (Key) keyMap.get("PUBLIC_KEY");

        return encryptBASE64(key.getEncoded());
    }
    public static String getPrivateKey(Map<String,Object> keyMap) throws Exception{
        Key key= (Key) keyMap.get("PRIVATE_KEY");

        return encryptBASE64(key.getEncoded());
    }
    public static byte[] encryptByPublicKey(byte[] data, String key) throws Exception {
        //对公钥进行解密
        byte[] keyBytes=decryptBASE64(key);
        //获取公钥
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);

        //对数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE,publicKey);

        return cipher.doFinal(data);
    }




    //字节数组转Base64编码
    public static String encryptBASE64(byte[] bytes){
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(bytes);
    }

    //Base64编码转字节数组
    public static byte[] decryptBASE64(String base64Key) throws IOException {
        BASE64Decoder decoder = new BASE64Decoder();
        return decoder.decodeBuffer(base64Key);
    }

    public static Map<String, Object> initKey() {
        return null;
    }
}

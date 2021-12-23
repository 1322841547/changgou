package com.changgou.oauth;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaSigner;
import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;
import sun.security.rsa.RSASignature;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.util.HashMap;

public class CreateJwtTest {

    /**
     * 创建令牌
     */
    @Test
    public void testCreateToken(){

        String key_location = "changgou.jks";
        String key_password = "changgou";  //密钥库密码
        String keyPwd = "changgou";  // 密钥对密码
        String alias = "changgou";
        //访问证书路径
        ClassPathResource resource = new ClassPathResource(key_location);
        //创建密钥工厂
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(resource, key_password.toCharArray());
        //获取密钥对
        KeyPair keyPair = keyStoreKeyFactory.getKeyPair(alias, keyPwd.toCharArray());
        RSAPrivateKey rsaPrivate = (RSAPrivateKey) keyPair.getPrivate(); //获取私钥

        //定义Payload
        HashMap<String, Object> tokenMap = new HashMap<>();
        tokenMap.put("id", 1);
        tokenMap.put("name", "ccwu");
        tokenMap.put("roles", "ROLE_VIP,ROLE_USER");
        //生成JWT令牌
        Jwt jwt = JwtHelper.encode(JSON.toJSONString(tokenMap), new RsaSigner(rsaPrivate));

        //decode
        String encoded = jwt.getEncoded();
        System.out.println(encoded);

    }
}

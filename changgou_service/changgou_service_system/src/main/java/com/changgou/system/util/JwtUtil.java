package com.changgou.system.util;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;

/**
 * @author ccwu
 * @date 2021/10/25 10:42
 *
 *  JWT工具类
 */


public class JwtUtil {

    public static final Long JWT_TTL = (long) (60 * 60 * 1000);  //有效期为 1小时
    public static final String JWT_KEY = "ccwu"; //设置密钥明文


    /**
     * 创建Token
     * @param id
     * @param subject
     * @param ttlMillis
     * @return
     */
    public static String CreateJWT(String id, String subject, Long ttlMillis){

        SignatureAlgorithm signatureAlgorithmHS256 = SignatureAlgorithm.HS256; //签名算法

        long currentTimeMillis = System.currentTimeMillis();
        Date now = new Date(currentTimeMillis);   //系统当前时间（第一次创建）

        if (ttlMillis == null) ttlMillis = JwtUtil.JWT_TTL;
        long expMillis = currentTimeMillis + ttlMillis;
        Date expDate = new Date(expMillis); //到期时间

        JwtBuilder builder = Jwts.builder()
                .setId(id)  //唯一的ID
                .setSubject(subject)  // 主题  可以是JSON数据
                .setIssuer("user")  // 签发者
                .setIssuedAt(now)   // 签发时间
                .signWith(signatureAlgorithmHS256, generalKey(JwtUtil.JWT_KEY))  //使用HS256对称加密算法签名, 第二个参数为秘钥
                .setExpiration(expDate); // 设置过期时间

        return  builder.compact();

    }

    /**
     * 生成加密后的秘钥 secretKey
     * @return
     */
    public static SecretKey generalKey(String key) {
        byte[] encodedKey = Base64.getDecoder().decode(key);
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
    }
}


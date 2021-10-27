package com.changgou.system.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * @author ccwu
 * @date 2021/10/25 11:29
 *
 * jwt校验工具类
 */


public class JWTUtil {

    public static final Long JWT_TTL = (long) (10 * 60 * 60 * 1000);  //有效期为 10小时
    public static final String JWT_KEY = "ccwu"; //设置密钥明文\


    /**
     * 生成加密后的秘钥 secretKey
     * @return
     */
    public static SecretKey generalKey(String key) {
        byte[] encodedKey = Base64.getDecoder().decode(key);
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
    }

    /**
     * 解析jwt
     * @param jwt
     * @return
     */
    public static Claims parseJWT(String jwt){
        return Jwts.parser()
                .setSigningKey(generalKey(JWT_KEY))
                .parseClaimsJws(jwt)
                .getBody();

    }

    
}

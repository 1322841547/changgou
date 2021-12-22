package com.changgou.oauth;

import org.junit.Test;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;

public class ParseJwtTest {
    /**
     * 校验令牌
     */
    @Test
    public void testParseJwtTest(){

        //密文
        String jwt = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlcyI6IlJPTEVfVklQLFJPTEVfVVNFUiIsIm5hbWUiOiJjY3d1IiwiaWQiOjF9.DODkrorbaab_kKIKaLFm40Igf1yCd8GEFy_4xb29v9_B8Cn32y4udCOVWbVbcjFAs74OoyaCJX8Tl-VJh5Q1WkNq7ANELbmmx_118b_k7e4zFefnFcpj11383jjFBUdskmZxJZOmuAmuW2W2oxcguU-EUUaJ8UDPfen-GnWWvlcfr-dhw2m-LN_L725lZWeOFNC8nlPg8ozGnuZpac-4YxQT724nAzSlHJJ3inx-fHV-ZfP42trZNeQXJYp7OEtaZ9H7247AV5NqGcd132jnfRmrpHlPLAb9QBv6Kd1H_qTk3bPmxux4iNipnPCcuwuVeOZgFPVUvs0zQcibjWu2xA";
        //公钥(通过openSSL工具获取)
        String publicKey ="-----BEGIN PUBLIC KEY-----MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvFsEiaLvij9C1Mz+oyAmt47whAaRkRu/8kePM+X8760UGU0RMwGti6Z9y3LQ0RvK6I0brXmbGB/RsN38PVnhcP8ZfxGUH26kX0RK+tlrxcrG+HkPYOH4XPAL8Q1lu1n9x3tLcIPxq8ZZtuIyKYEmoLKyMsvTviG5flTpDprT25unWgE4md1kthRWXOnfWHATVY7Y/r4obiOL1mS5bEa/iNKotQNnvIAKtjBM4RlIDWMa6dmz+lHtLtqDD2LF1qwoiSIHI75LQZ/CNYaHCfZSxtOydpNKq8eb1/PGiLNolD4La2zf0/1dlcr5mkesV570NxRmU1tFm8Zd3MZlZmyv9QIDAQAB-----END PUBLIC KEY-----";

        //校验Jwt
        Jwt token = JwtHelper.decodeAndVerify(jwt, new RsaVerifier(publicKey));
        //获取Jwt原始内容
        String claims = token.getClaims();
        System.out.println(claims);
    }
}
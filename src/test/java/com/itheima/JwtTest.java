package com.itheima;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {
    //        生成jwt
    @Test
    public void testGenerateJwt() {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("id", 1);
        dataMap.put("username", "admin");

        String jwt = Jwts.builder()
            .signWith(SignatureAlgorithm.HS256,"aXRoZWltYQ==")//指定加密算法，密钥
            .addClaims(dataMap)//添加自定义信息
            .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))//令牌有效时间
            .compact();//生成令牌
        System.out.println(jwt);

    }
}

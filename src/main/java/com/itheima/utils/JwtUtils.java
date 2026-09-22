package com.itheima.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

public class JwtUtils {

    // 秘钥（和课程测试保持一致，长度必须≥32位，适配0.11.5）
    private static final String SECRET_KEY = "itheima666itheima666itheima666itheima666";
    // 12小时 单位毫秒
    private static final long EXPIRE_TIME = 12 * 60 * 60 * 1000L;

    /**
     * 生成JWT令牌
     * @param claims 自定义载荷数据
     * @return jwt字符串
     */
    public static String generateToken(Map<String, Object> claims){
        return Jwts.builder()
                .addClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_TIME))
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8)), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * 解析JWT令牌
     * @param jwt jwt令牌字符串
     * @return 载荷Claims
     */
    public static Claims parseToken(String jwt){
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8)))
                .build()
                .parseClaimsJws(jwt)
                .getBody();
    }

}

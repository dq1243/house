package com.dq.house.common.utlis;

import com.dq.house.common.exception.HouseException;
import com.dq.house.common.result.ResultCodeEnum;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;

/**
 * @author DQ1243
 */
public class JwtUtils {

    // 至少256位的密钥，使用HS256算法进行签名
    private static SecretKey secretKey = Keys.hmacShaKeyFor("4rTZ5lnTBSovLbjc9cGdJZlFUqWj0oBn".getBytes());

    // 创建JWT令牌的方法： header.payload.密钥
    public static String createToken(Long userId, String username) {
        return Jwts.builder()
                .expiration(new Date(System.currentTimeMillis() + 3600000))  // 设置JWT的过期时间为1小时
                .signWith(secretKey)  // 使用HS256算法和密钥签名JWT
                .subject("LOGIN_USER")  // 设置JWT的主题
                .claim("userId", userId)  // 将用户ID作为自定义声明添加到JWT中
                .claim("username", username)  // 将用户名作为自定义声明添加到JWT中
                .compact();  // 构建JWT并返回其字符串表示形式
    }

    // 解析令牌
    public static Claims parseToken(String token) {
        if (token == null) {
            // 未登录，或者token过期
            throw new HouseException(ResultCodeEnum.ADMIN_LOGIN_AUTH);
        }
        try {
            JwtParser jwtParser = Jwts.parser()
                    .verifyWith(secretKey)
                    .build();
            Jws<Claims> jws = jwtParser.parseSignedClaims(token);
            return jws.getPayload();
        } catch (ExpiredJwtException e) {
            // 如果令牌无效、过期或签名不正确，抛出异常
            throw new HouseException(ResultCodeEnum.TOKEN_EXPIRED);
        } catch (IllegalArgumentException e) {
            // 如果令牌为空或格式不正确，抛出异常
            throw new HouseException(ResultCodeEnum.TOKEN_INVALID);
        }
    }
}
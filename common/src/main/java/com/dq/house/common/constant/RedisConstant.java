package com.dq.house.common.constant;

/**
 * @author DQ1243
 */

// Redis相关的常量
public class RedisConstant {

    /**
     * 后台管理系统登录验证码前缀
     */
    public static final String ADMIN_LOGIN_CAPTCHA_PREFIX = "admin:login";

    /**
     * 后台登录验证码过期时间，单位为秒
     */
    public static final Integer ADMIN_LOGIN_CAPTCHA_TTL_SEC = 60;

    /**
     * 移动端登录验证码前缀
     */
    public static final String APP_LOGIN_CAPTCHA_PREFIX = "user:login";

    /**
     * 移动端登录验证码重发时间，单位为秒
     */
    public static final Integer APP_LOGIN_CODE_RESEND_TIME_SEC = 60;

    /**
     * 移动端登录验证码过期时间，单位为秒
     */
    public static final Integer APP_LOGIN_CODE_TTL_SEC = 60 * 10;

    /**
     * 房间信息缓存前缀
     */
    public static final String APP_ROOM_PREFIX = "app:room:";
}

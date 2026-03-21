package com.dq.house.common.login;

/**
 * @author DQ1243
 */

// 登录用户信息的Holder类，使用ThreadLocal存储当前线程的登录用户信息
public class LoginUserHolder {

    private static final ThreadLocal<LoginUser> threadLocal = new ThreadLocal<>();

    public static void setThreadLocal(LoginUser loginUser) {
        threadLocal.set(loginUser);
    }

    public static LoginUser getLoginUser() {
        return threadLocal.get();
    }

    public static void clear() {
        threadLocal.remove();
    }
}
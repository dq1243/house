package com.dq.house.web.app.custom.interceptor;

import com.dq.house.common.login.LoginUser;
import com.dq.house.common.login.LoginUserHolder;
import com.dq.house.common.utlis.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author DQ1243
 */

// 认证拦截器，验证用户登录状态，获取用户信息等
@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("access-token");
        Claims claims = JwtUtils.parseToken(token);
        Long userId = claims.get("userId", Long.class);
        String username = claims.get("username", String.class);
        LoginUserHolder.setThreadLocal(new LoginUser(userId, username));
        return true;
    }

    // 请求处理完成后，清除ThreadLocal中的用户信息，避免内存泄漏
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        LoginUserHolder.clear();
    }
}

package com.dq.house.web.admin.custom.interceptor;

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

// token拦截器
@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    // 在请求处理之前进行token验证
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("access-token");
        // 检查token是否合法
        Claims claims = JwtUtils.parseToken(token);
        // 将用户信息存储到ThreadLocal中，供后续使用
        Long userId = claims.get("userId", Long.class);
        String username = claims.get("username", String.class);
        LoginUserHolder.setThreadLocal(new LoginUser(userId, username));
        // token通过
        return true;
    }

    // 请求处理完成后，清除ThreadLocal中的用户信息，避免内存泄漏
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        LoginUserHolder.clear();
    }
}
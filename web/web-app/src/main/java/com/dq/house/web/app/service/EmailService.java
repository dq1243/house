package com.dq.house.web.app.service;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author DQ1243
 */
public interface EmailService {

    // 发送qq邮箱验证码
    void sendQQEmail(String email, String code);
}

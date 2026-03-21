package com.dq.house.web.app.service;

import com.dq.house.web.app.vo.user.LoginVo;

public interface LoginService {

    /**
     * 获取短信验证码
     * //@param phone 手机号
     *
     * @param email 邮箱
     */
    void getCode(String phone, String email);

    /**
     * 登录
     *
     * @param loginVo 登录信息
     * @return token
     */
    String login(LoginVo loginVo);
}

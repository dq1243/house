package com.dq.house.web.admin.service;

import com.dq.house.web.admin.vo.login.CaptchaVo;
import com.dq.house.web.admin.vo.login.LoginVo;
import com.dq.house.web.admin.vo.system.user.SystemUserInfoVo;

public interface LoginService {

    /**
     * 获取图形验证码
     *
     * @return
     */
    CaptchaVo getCaptcha();

    /**
     * 登录
     *
     * @param loginVo
     * @return
     */
    String login(LoginVo loginVo);

        /**
        * 根据用户ID获取用户信息
        *
        * @param userId
        * @return
        */
    SystemUserInfoVo getUserInfoByUserId(Long userId);
}

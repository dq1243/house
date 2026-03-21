package com.dq.house.web.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dq.house.common.constant.RedisConstant;
import com.dq.house.common.exception.HouseException;
import com.dq.house.common.result.ResultCodeEnum;
import com.dq.house.common.utlis.CodeUtils;
import com.dq.house.common.utlis.JwtUtils;
import com.dq.house.model.entity.UserInfo;
import com.dq.house.model.enums.BaseStatus;
import com.dq.house.web.app.mapper.UserInfoMapper;
import com.dq.house.web.app.service.EmailService;
import com.dq.house.web.app.service.LoginService;
import com.dq.house.web.app.vo.user.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class LoginServiceImpl implements LoginService {

    //    @Autowired
//    private SmsService smsService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private UserInfoMapper userInfoMapper;

    /**
     * 获取验证码
     */
    @Override
    public void getCode(String phone, String email) {
        // 生成验证码
        String code = CodeUtils.getCode(6);
        String key = RedisConstant.APP_LOGIN_CAPTCHA_PREFIX + phone + email;
        Boolean hasKey = stringRedisTemplate.hasKey(key);
        if (hasKey) {
            Long ttl = stringRedisTemplate.getExpire(key, TimeUnit.SECONDS);
            if (RedisConstant.APP_LOGIN_CODE_TTL_SEC - ttl < RedisConstant.APP_LOGIN_CODE_RESEND_TIME_SEC) {
                // 如果距离过期时间还有足够的时间，说明验证码还未过期，不需要重新发送
                throw new HouseException(ResultCodeEnum.APP_SEND_SMS_TOO_OFTEN);
            }
        }
        // 发送短信验证码
//        smsService.sendCode(phone, code);
        // 发送验证码
//        smsService.sendCode(phone, code);
        emailService.sendQQEmail(email, code);
        // 存储验证码到 Redis，设置过期时间为 5 分钟
        stringRedisTemplate.opsForValue().set(key, code, RedisConstant.APP_LOGIN_CODE_TTL_SEC, TimeUnit.SECONDS);
    }

    /**
     * 登录
     *
     * @param loginVo 登录信息
     * @return token
     */
    @Override
    public String login(LoginVo loginVo) {
        if (loginVo.getPhone() == null) {
            throw new HouseException(ResultCodeEnum.APP_LOGIN_PHONE_EMPTY);
        }
        if (loginVo.getEmail() == null) {
            throw new HouseException(ResultCodeEnum.EMAIL_ERROR);
        }
        if (loginVo.getCode() == null) {
            throw new HouseException(ResultCodeEnum.APP_LOGIN_CODE_EMPTY);
        }
        String key = RedisConstant.APP_LOGIN_CAPTCHA_PREFIX + loginVo.getPhone() + loginVo.getEmail();
        String code = stringRedisTemplate.opsForValue().get(key);
        if (code == null) {
            throw new HouseException(ResultCodeEnum.ADMIN_CAPTCHA_CODE_EXPIRED);
        }
        if (!code.equals(loginVo.getCode())) {
            throw new HouseException(ResultCodeEnum.ADMIN_CAPTCHA_CODE_ERROR);
        }
        LambdaQueryWrapper<UserInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserInfo::getPhone, loginVo.getPhone()).eq(UserInfo::getEmail, loginVo.getEmail());
        UserInfo userInfo = userInfoMapper.selectOne(queryWrapper);
        if (userInfo != null) {
            if (userInfo.getEmail().equals(loginVo.getEmail())) {
                if (userInfo.getStatus() == BaseStatus.DISABLE) {
                    // 禁用
                    throw new HouseException(ResultCodeEnum.ADMIN_ACCOUNT_DISABLED_ERROR);
                }
                return JwtUtils.createToken(userInfo.getId(), userInfo.getPhone());
            } else {
                throw new HouseException(ResultCodeEnum.APP_LOGIN_EMAIL_OR_PHONE_ERROR);
            }
        } else {
            queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(UserInfo::getEmail, loginVo.getEmail());
            userInfo = userInfoMapper.selectOne(queryWrapper);
            if (userInfo != null) {
                throw new HouseException(ResultCodeEnum.APP_LOGIN_EMAIL_OR_PHONE_ERROR);
            }
            // 如果用户不存在，自动注册一个新用户
            userInfo = new UserInfo();
            userInfo.setPhone(loginVo.getPhone());
            userInfo.setEmail(loginVo.getEmail());
            userInfo.setNickname("用户" + loginVo.getPhone().substring(7));
            userInfo.setStatus(BaseStatus.ENABLE);
            userInfo.setAvatarUrl("http://192.168.100.101:9000/house/userImage/public.png");
            userInfoMapper.insert(userInfo);
            return JwtUtils.createToken(userInfo.getId(), userInfo.getPhone());
        }
    }
}
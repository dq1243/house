package com.dq.house.web.admin.service.impl;

import com.dq.house.common.constant.RedisConstant;
import com.dq.house.common.exception.HouseException;
import com.dq.house.common.result.ResultCodeEnum;
import com.dq.house.common.utlis.JwtUtils;
import com.dq.house.model.entity.SystemUser;
import com.dq.house.model.enums.BaseStatus;
import com.dq.house.web.admin.mapper.SystemUserMapper;
import com.dq.house.web.admin.service.LoginService;
import com.dq.house.web.admin.vo.login.CaptchaVo;
import com.dq.house.web.admin.vo.login.LoginVo;
import com.dq.house.web.admin.vo.system.user.SystemUserInfoVo;
import com.wf.captcha.SpecCaptcha;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private SystemUserMapper systemUserMapper;

    /**
     * 获取图形验证码
     *
     * @return
     */
    @Override
    @ResponseBody
    public CaptchaVo getCaptcha() {
        SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 5);

        String code = specCaptcha.text().toLowerCase();
        String key = RedisConstant.ADMIN_LOGIN_CAPTCHA_PREFIX + UUID.randomUUID();

        // 将验证码存储到Redis中，设置过期时间为60秒
        stringRedisTemplate.opsForValue().set(key, code, RedisConstant.ADMIN_LOGIN_CAPTCHA_TTL_SEC, TimeUnit.SECONDS);
        return new CaptchaVo(specCaptcha.toBase64(), key);
    }

    /**
     * 登录
     *
     * @param loginVo
     * @return
     */
    @Override
    public String login(LoginVo loginVo) {
        // 验证验证码
        if (loginVo.getCaptchaKey() == null || loginVo.getCaptchaCode() == null) {
            throw new HouseException(ResultCodeEnum.APP_LOGIN_CODE_EMPTY);
        }
        String value = stringRedisTemplate.opsForValue().get(loginVo.getCaptchaKey());
        if (value == null) {
            // 验证码过期
            throw new HouseException(ResultCodeEnum.ADMIN_CAPTCHA_CODE_EXPIRED);
        } else if (!value.equals(loginVo.getCaptchaCode().toLowerCase())) {
            // 验证码错误
            throw new HouseException(ResultCodeEnum.ADMIN_CAPTCHA_CODE_ERROR);
        }

        // 验证用户名和密码
        if (loginVo.getUsername() == null || loginVo.getPassword() == null) {
            throw new HouseException(ResultCodeEnum.ADMIN_ACCOUNT_ERROR);
        }

        SystemUser systemUser = systemUserMapper.selectOneByUserName(loginVo.getUsername());

        if (systemUser == null) {
            throw new HouseException(ResultCodeEnum.ADMIN_ACCOUNT_NOT_EXIST_ERROR);
        } else if (systemUser.getStatus() == BaseStatus.DISABLE) {
            throw new HouseException(ResultCodeEnum.ADMIN_ACCOUNT_DISABLED_ERROR);
        } else if (!systemUser.getPassword().equals(DigestUtils.md5Hex(loginVo.getPassword()))) {
            throw new HouseException(ResultCodeEnum.ADMIN_ACCOUNT_ERROR);
        }
        return JwtUtils.createToken(systemUser.getId(), systemUser.getUsername());
    }

    /**
     * 根据用户ID获取用户信息
     *
     * @param userId
     * @return
     */
    @Override
    public SystemUserInfoVo getUserInfoByUserId(Long userId) {
        SystemUser systemUser = systemUserMapper.selectById(userId);
        if (systemUser == null) {
            throw new HouseException(ResultCodeEnum.ADMIN_ACCOUNT_NOT_EXIST_ERROR);
        }
        SystemUserInfoVo userInfoVo = new SystemUserInfoVo();
        BeanUtils.copyProperties(systemUser, userInfoVo);
        return userInfoVo;
    }
}
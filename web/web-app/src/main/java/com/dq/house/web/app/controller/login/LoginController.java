package com.dq.house.web.app.controller.login;

import com.dq.house.common.login.LoginUserHolder;
import com.dq.house.common.result.Result;
import com.dq.house.model.entity.UserInfo;
import com.dq.house.web.app.mapper.UserInfoMapper;
import com.dq.house.web.app.service.LoginService;
import com.dq.house.web.app.vo.user.LoginVo;
import com.dq.house.web.app.vo.user.UserInfoVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


// 因为某种原因，手机号短信登录修改为（qq/163）邮箱登录
@Tag(name = "登录管理")
@RestController
@RequestMapping("/app/")
public class LoginController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private UserInfoMapper userInfoMapper;

    @GetMapping("login/getCode")
//    @Operation(summary = "获取短信验证码")
    @Operation(summary = "获取邮箱验证码")
    public Result getCode(@RequestParam String phone, String email) {
        loginService.getCode(phone, email);
        return Result.ok();
    }

    @PostMapping("login")
    @Operation(summary = "登录")
    public Result<String> login(@RequestBody LoginVo loginVo) {
        String token = loginService.login(loginVo);
        return Result.ok(token);
    }

    @GetMapping("info")
    @Operation(summary = "获取登录用户信息")
    public Result<UserInfoVo> info() {
        UserInfo userInfo = userInfoMapper.selectById(LoginUserHolder.getLoginUser().getUserId());
        UserInfoVo userInfoVo = new UserInfoVo(userInfo.getNickname(), userInfo.getAvatarUrl());
        return Result.ok(userInfoVo);
    }
}
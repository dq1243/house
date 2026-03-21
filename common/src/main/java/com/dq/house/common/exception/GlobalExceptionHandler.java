package com.dq.house.common.exception;

import com.dq.house.common.result.Result;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author DQ1243
 */

// 全局异常处理器
// @ControllerAdvice用于定义全局异常处理、全局数据绑定、全局模型属性等，能够让这些功能作用于所有的 Controller。
@RestControllerAdvice
@Hidden
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)  // 处理所有异常
    // @ResponseBody 保证了 handleException 方法返回的 Result 对象
    // 会被自动序列化为 JSON（或其他格式），直接返回给前端，而不是解析为页面路径
    @ResponseBody
    public Result handleException(Exception e) {
        e.printStackTrace();
        return Result.fail();
    }

    @ExceptionHandler(HouseException.class)  // 处理所有异常
    @ResponseBody
    public Result handleException(HouseException e) {
        e.printStackTrace();
        return Result.fail(e.getCode(), e.getMessage());
    }
}

package com.dq.house.web.admin.custom.config;

import com.dq.house.web.admin.custom.converter.StringToBaseEnumConverter;
//import com.dq.house.web.admin.custom.converter.StringToItemTypeConverter;
import com.dq.house.web.admin.custom.interceptor.AuthenticationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author DQ1243
 */

// SpringWebMvc的配置类
// 主要用于注册自定义的内容
// 自定义类型转换器（Converter）：实现请求参数到枚举类型的自动转换
// 格式化器（Formatter）：实现请求参数到日期类型的自动转换
// 拦截器（Interceptor）：可以添加登录校验、权限校验、日志等功能
// 全局异常处理器：注册全局异常处理逻辑
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

//    @Autowired
//    private StringToItemTypeConverter stringToItemTypeConverter;

//    注册自定义的Converter
//    @Override
//    public void addFormatters(FormatterRegistry registry) {
//        registry.addConverter(stringToItemTypeConverter);
//    }

    @Autowired
    private StringToBaseEnumConverter stringToBaseEnumConverter;
    @Autowired
    private AuthenticationInterceptor authenticationInterceptor;

    // 注册自定义的ConverterFactory
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverterFactory(stringToBaseEnumConverter);
    }

    // 注册自定义拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor)
                .addPathPatterns("/admin/**")  // 增加拦截路径
                .excludePathPatterns("/admin/login/**");  // 排除拦截路径
    }
}

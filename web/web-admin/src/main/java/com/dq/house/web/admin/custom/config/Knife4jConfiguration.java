package com.dq.house.web.admin.custom.config;

import io.swagger.v3.oas.models.OpenAPI;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author DQ1243
 */

// Knife4j配置类
@Configuration
public class Knife4jConfiguration {

    // 配置OpenAPI文档信息
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new io.swagger.v3.oas.models.info.Info()
                        .title("后台管理系统API")
                        .version("1.0")
                        .description("后台管理系统API"));
    }

    // 后台登录管理API分组
    @Bean
    public GroupedOpenApi adminApi() {
        return GroupedOpenApi.builder()
                .group("后台登录管理")
                .pathsToMatch("/admin/login/**",
                        "/admin/info")
                .build();
    }

    // 系统信息管理API分组
    @Bean
    GroupedOpenApi systemApi() {
        return GroupedOpenApi.builder()
                .group("系统信息管理")
                .pathsToMatch("/admin/system/**")
                .build();
    }

    // 公寓信息管理API分组
    @Bean
    GroupedOpenApi apartmentApi() {
        return GroupedOpenApi.builder()
                .group("公寓信息管理")
                .pathsToMatch(
                        "/admin/apartment/**",
                        "/admin/room/**",
                        "/admin/label/**",
                        "/admin/facility/**",
                        "/admin/fee/**",
                        "/admin/attr/**",
                        "/admin/payment/**",
                        "/admin/region/**",
                        "/admin/term/**",
                        "/admin/file/**")
                .build();
    }

    // 租赁信息管理API分组
    @Bean
    public GroupedOpenApi houseApi() {
        return GroupedOpenApi.builder()
                .group("租凭信息管理")
                .pathsToMatch(
                        "/admin/appointment/**",
                        "/admin/agreement/**")
                .build();
    }

    // 平台用户管理API分组
    @Bean
    public GroupedOpenApi userApi() {
        return GroupedOpenApi.builder()
                .group("平台用户管理")
                .pathsToMatch(
                        "/admin/user/**")
                .build();
    }
}

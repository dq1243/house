//package com.dq.house.common.sms;
//
//import com.aliyun.dysmsapi20170525.Client;
//import com.aliyun.teaopenapi.models.Config;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * @author DQ1243
// */
//
//// sms配置类
//@Configuration
//@EnableConfigurationProperties(AliyunSMSProperties.class)  // 启用 Ali
//public class AliyunSMSConfiguration {
//
//    @Autowired
//    private AliyunSMSProperties aliyunSMSProperties;
//
//    // 创建阿里云短信服务的客户端对象，并将其注册为 Spring Bean，以便在应用程序中使用
//    @Bean
//    public Client createClient() {
//        Config config = new Config();
//        config.setAccessKeyId(aliyunSMSProperties.getAccessKeyId());
//        config.setAccessKeySecret(aliyunSMSProperties.getAccessKeySecret());
//        config.setEndpoint(aliyunSMSProperties.getEndpoint());
//        try {
//            return new Client(config);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//}

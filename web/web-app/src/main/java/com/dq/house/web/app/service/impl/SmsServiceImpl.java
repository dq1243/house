//package com.dq.house.web.app.service.impl;
//
//import com.aliyun.dysmsapi20170525.Client;
//import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
//import com.dq.house.web.app.service.SmsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class SmsServiceImpl implements SmsService {
//
//    @Autowired
//    private Client aliyunSMSClient;
//
//    /**
//     * 发送短信验证码
//     *
//     * @param phone 手机号
//     */
//
//    // TODO: 因为某些原因阿里云的通信服务使用不了，所以暂时先打印验证码，后续可以改成qq邮箱发送验证码
//    @Override
//    public void sendCode(String phone, String code) {
//        SendSmsRequest smsRequest = new SendSmsRequest();
//        smsRequest.setPhoneNumbers(phone);
//        smsRequest.setSignName("阿里云短信测试");
//        smsRequest.setTemplateCode("SMS_154950909");
//        smsRequest.setTemplateParam("{\"code\":\"" + code + "\"}");
//        try {
//            aliyunSMSClient.sendSms(smsRequest);
//            System.out.println(code);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//}

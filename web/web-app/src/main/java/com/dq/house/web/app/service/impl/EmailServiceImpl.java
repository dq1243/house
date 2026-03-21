package com.dq.house.web.app.service.impl;

import com.dq.house.common.exception.HouseException;
import com.dq.house.common.result.ResultCodeEnum;
import com.dq.house.web.app.service.EmailService;
import jakarta.mail.Message;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author DQ1243
 */
@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private Session session;  // 注入邮件发送的session对象
    @Value("${email.qq.send-username}")
    private String from;  // 1.获取邮件对象

    /**
     * 发送qq邮箱验证码
     *
     * @param email
     * @param code
     */
    @Override
    public void sendQQEmail(String email, String code) {
        try {
            // 2.创建邮件对象
            Message message = new MimeMessage(session);
            // 2.1设置发件人
            message.setFrom(new InternetAddress(from));
            // 2.2设置接收人
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            // 2.3设置邮件主题
            message.setSubject("账号激活");
            // 2.4设置邮件内容
            String content = "<html><head></head><body><h1>这是一份激活文件</h1><h3>激活码，code="
                    + code + "</href></h3></body></html>";
            message.setContent(content, "text/html;charset=UTF-8");
            // 3.发送邮件
            Transport.send(message);
        } catch (Exception e) {
            throw new HouseException(ResultCodeEnum.EMAIL_SEND_ERROR);
        }
    }
}

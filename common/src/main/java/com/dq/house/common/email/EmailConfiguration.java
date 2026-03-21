package com.dq.house.common.email;

import com.dq.house.common.exception.HouseException;
import com.dq.house.common.result.ResultCodeEnum;
import com.sun.mail.util.MailSSLSocketFactory;
import jakarta.mail.Authenticator;
import jakarta.mail.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

// 邮件配置类
@Configuration
@ConditionalOnProperty(name = "email.qq.send-username") // 只有当配置了email.qq.send-username属性时才加载这个配置类
@EnableConfigurationProperties(QQEmailProperties.class)
public class EmailConfiguration {

    @Autowired
    private QQEmailProperties emailProperties;

    @Bean
    // 发送qq邮箱
    public Session sendQQEmail() {
        String from = emailProperties.getSendUsername();// 发件人电子邮箱
        String host = emailProperties.getSendHost(); // 指定发送邮件的主机smtp.qq.com(QQ)
        String paw = emailProperties.getSendPassword(); // 发件人邮箱授权码

        // 3.发送一封激活邮件
        Properties properties = System.getProperties();// 获取系统属性
        properties.setProperty("mail.smtp.host", host);// 设置邮件服务器
        properties.setProperty("mail.smtp.auth", "true");// 打开认证

        try {
            //QQ邮箱需要下面这段代码
            MailSSLSocketFactory sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            properties.put("mail.smtp.ssl.enable", "true");
            properties.put("mail.smtp.ssl.socketFactory", sf);

            // 1.获取默认session对象
            Session session = Session.getDefaultInstance(properties, new Authenticator() {
                public jakarta.mail.PasswordAuthentication getPasswordAuthentication() {
                    return new jakarta.mail.PasswordAuthentication(from, paw); // 发件人邮箱账号、授权码
                }
            });
            return session;
        } catch (Exception e) {
            throw new HouseException(ResultCodeEnum.EMAIL_SEND_ERROR);
        }
    }
}
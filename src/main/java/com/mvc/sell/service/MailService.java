package com.mvc.sell.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * mail service
 *
 * @author qiyichen
 * @create 2018/3/14 17:00
 */
@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    public void send(String email, String code) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setFrom("buytoken@163.com");
        message.setSubject(String.format("您的验证码是 %s , 请在5分钟内输入", code));
        message.setText(String.format("buyToken 验证码: %s", code));
        mailSender.send(message);
    }
}

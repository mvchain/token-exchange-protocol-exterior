package com.mvc.sell.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
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

    @Async
    public void send(String email, String code) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setFrom("buytoken.one@gmail.com");
        message.setSubject(String.format("your buyToken code:%s", code));
        message.setText(String.format("your buyToken code:%s", code));
        mailSender.send(message);
    }
}

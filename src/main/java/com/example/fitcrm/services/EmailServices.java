package com.example.fitcrm.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServices{

    @Autowired
    private JavaMailSender mailSender;

    public void SendMail(String addressee, String subject, String messageBody) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(addressee);
        message.setSubject(subject);
        message.setText(messageBody);
        mailSender.send(message);
    }
}

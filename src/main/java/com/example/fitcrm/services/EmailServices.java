package com.example.fitcrm.services;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EmailServices{


    private final JavaMailSender mailSender;

    public void sendMail(String addressee, String subject, String messageBody) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("fitcrm.empresariales@gmail.com");
        message.setTo(addressee);
        message.setSubject(subject);
        message.setText(messageBody);
        mailSender.send(message);
    }
}

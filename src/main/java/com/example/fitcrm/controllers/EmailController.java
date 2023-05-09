package com.example.fitcrm.controllers;

import com.example.fitcrm.models.MailRequest;
import com.example.fitcrm.services.EmailServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/send-email")
public class EmailController {
    @Autowired
    private EmailServices emailServices;

    @PostMapping
    public ResponseEntity<String> enviarCorreo(@RequestBody MailRequest mailRequest) {
        emailServices.SendMail(mailRequest.getAddressee(), mailRequest.getSubject(), mailRequest.getBody());
        return ResponseEntity.ok("Mail sent successfully");
    }
}

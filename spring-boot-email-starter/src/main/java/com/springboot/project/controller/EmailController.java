package com.springboot.project.controller;

import com.springboot.project.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    private final EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/v1/emails/action/send-email")
    public ResponseEntity<Void> sendEmail() {
        this.emailService.sendEmail();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}

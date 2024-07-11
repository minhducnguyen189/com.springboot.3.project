package com.springboot.project.controller;

import com.springboot.project.generated.api.EmailSchedulerApi;
import com.springboot.project.service.EmailSchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class EmailSchedulerController implements EmailSchedulerApi {

    private final EmailSchedulerService emailSchedulerService;

    @Autowired
    public EmailSchedulerController(EmailSchedulerService emailSchedulerService) {
        this.emailSchedulerService = emailSchedulerService;
    }

    @Override
    public ResponseEntity<Void> triggerEmailScheduler(UUID emailConfigId) {
        this.emailSchedulerService.triggerScheduler(emailConfigId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

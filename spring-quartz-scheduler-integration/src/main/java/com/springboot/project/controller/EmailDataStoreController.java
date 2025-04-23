package com.springboot.project.controller;

import com.springboot.project.generated.api.EmailDataStoreApi;
import com.springboot.project.generated.model.EmailSchedulerDataRequest;
import com.springboot.project.generated.model.EmailSchedulerDataResponse;
import com.springboot.project.service.EmailDataService;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailDataStoreController implements EmailDataStoreApi {

    private final EmailDataService emailDataService;

    @Autowired
    public EmailDataStoreController(EmailDataService emailDataService) {
        this.emailDataService = emailDataService;
    }

    @Override
    public ResponseEntity<EmailSchedulerDataResponse> saveEmailData(
            EmailSchedulerDataRequest emailSchedulerDataRequest) {
        return new ResponseEntity<>(
                this.emailDataService.createEmailScheduleData(emailSchedulerDataRequest),
                HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<EmailSchedulerDataResponse> updateEmailData(
            UUID emailConfigId, EmailSchedulerDataRequest emailSchedulerDataRequest) {
        return new ResponseEntity<>(
                this.emailDataService.updateEmailScheduleData(
                        emailConfigId, emailSchedulerDataRequest),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<EmailSchedulerDataResponse> getEmailData(UUID emailConfigId) {
        return new ResponseEntity<>(
                this.emailDataService.getEmailEmailScheduleData(emailConfigId), HttpStatus.OK);
    }
}

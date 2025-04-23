package com.springboot.project.controller;

import com.springboot.project.annotation.LogExecutionTime;
import com.springboot.project.helper.RandomHelper;
import com.springboot.project.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @LogExecutionTime
    @RequestMapping(
            method = RequestMethod.GET,
            path = "/v1/accounts",
            produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> getAccountDetail() {
        RandomHelper.randomSleepInSeconds();
        return new ResponseEntity<>(this.accountService.addAccount(), HttpStatus.OK);
    }
}

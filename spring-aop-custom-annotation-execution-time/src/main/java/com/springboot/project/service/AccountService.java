package com.springboot.project.service;

import com.springboot.project.annotation.LogExecutionTime;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AccountService {

    @LogExecutionTime
    public String addAccount() {
        try {
            int randomMilliSeconds = (new Random().nextInt(9 ) + 1) * 1000;
            Thread.sleep(randomMilliSeconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(getClass() + ": Doing my DB work: Adding an account");
        return "Sample account: duc.nguyen@example.com";
    }

}

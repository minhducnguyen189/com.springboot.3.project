package com.springboot.project.service;

import com.springboot.project.annotation.LogExecutionTime;
import com.springboot.project.helper.RandomHelper;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

  @LogExecutionTime
  public String addAccount() {
    System.out.println(getClass() + ": Doing my DB work: Getting an account");
    RandomHelper.randomSleepInSeconds();
    return "Sample account: duc.nguyen@example.com";
  }
}

package com.springboot.project.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

  @Before("execution(public void com.springboot.project.dao.AccountDAO.addAccount())")
  public void beforeAddAccountAdvice() {
    System.out.println("=======>>> Executing @Before advice on addAccount()");
  }
}

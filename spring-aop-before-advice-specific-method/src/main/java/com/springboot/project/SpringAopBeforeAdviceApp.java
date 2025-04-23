package com.springboot.project;

import com.springboot.project.dao.AccountDAO;
import com.springboot.project.dao.MembershipDAO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringAopBeforeAdviceApp {
    public static void main(String[] args) {
        SpringApplication.run(SpringAopBeforeAdviceApp.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(AccountDAO accountDAO, MembershipDAO membershipDAO) {
        return runner -> {
            demoTheBeforeAdvice(accountDAO, membershipDAO);
        };
    }

    private void demoTheBeforeAdvice(AccountDAO accountDAO, MembershipDAO membershipDAO) {
        // call the first time
        accountDAO.addAccount();
        membershipDAO.addAccount();

        System.out.println("\n call the second time! \n");

        // call the second time
        accountDAO.addAccount();
        membershipDAO.addAccount();
    }
}

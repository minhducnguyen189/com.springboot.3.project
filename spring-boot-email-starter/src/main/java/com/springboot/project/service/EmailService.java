package com.springboot.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

  private final JavaMailSender javaMailSender;

  @Autowired
  public EmailService(JavaMailSender javaMailSender) {
    this.javaMailSender = javaMailSender;
  }

  public void sendEmail() {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setFrom("no.reply@example.com");
    message.setTo("thang.do@example.com");
    message.setSubject("Spring Boot Service Test");
    message.setText("Hello this is text message!");
    javaMailSender.send(message);
  }
}

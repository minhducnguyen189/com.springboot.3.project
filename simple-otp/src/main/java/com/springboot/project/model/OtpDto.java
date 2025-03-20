package com.springboot.project.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class OtpDto {

  private String otpCode;
  private LocalDateTime expirationTime;
  private Integer triedNumber;
}

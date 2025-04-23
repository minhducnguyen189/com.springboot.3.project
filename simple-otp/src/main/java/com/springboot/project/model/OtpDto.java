package com.springboot.project.model;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OtpDto {

    private String otpCode;
    private LocalDateTime expirationTime;
    private Integer triedNumber;
}

package com.springboot.project.service;

import com.springboot.project.model.OtpDto;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class OtpService {

    private final Map<String, OtpDto> otpStorage = new ConcurrentHashMap<>();

    public String getOtpCode(String email) {
        String otpCode = this.generateOptCode();
        OtpDto otpDto = new OtpDto();
        otpDto.setOtpCode(otpCode);
        otpDto.setExpirationTime(LocalDateTime.now().plusMinutes(5));
        this.otpStorage.remove(email);
        this.otpStorage.put(email, otpDto);
        return otpCode;
    }

    public String verifyOtpCode(String email, String otpCode) {
        if (this.checkValidOtp(email, otpCode)) {
            return "OTP verified successfully: This is the secret message!";
        } else {
            return "Invalid OTP or expired please try input your OTP again or regenerate OTP";
        }
    }

    private boolean checkValidOtp(String email, String otpCode) {
        OtpDto storedOtp = otpStorage.get(email);
        if (storedOtp != null && storedOtp.getExpirationTime().isAfter(LocalDateTime.now())) {
            return storedOtp.getOtpCode().equals(otpCode);
        }
        return false;
    }

    @Scheduled(fixedDelay = 60000) // Run every minute
    public void cleanupExpiredOtpCodes() {
        LocalDateTime now = LocalDateTime.now();
        otpStorage.entrySet().removeIf(entry -> entry.getValue().getExpirationTime().isBefore(now));
    }

    private String generateOptCode() {
        return RandomStringUtils.randomNumeric(6);
    }


}

package com.springboot.project.service;

import com.springboot.project.model.OtpDto;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class OtpService {

    private static final Integer MAXIMUM_TRIED_NUMBER = 5;
    private final Map<String, OtpDto> otpStorage = new ConcurrentHashMap<>();

    public String getOtpCode(String email) {
        String otpCode = this.generateOptCode();
        OtpDto otpDto = new OtpDto();
        otpDto.setOtpCode(otpCode);
        otpDto.setExpirationTime(LocalDateTime.now().plusMinutes(5));
        otpDto.setTriedNumber(0);
        this.otpStorage.remove(email);
        this.otpStorage.put(email, otpDto);
        return otpCode;
    }

    public String verifyOtpCode(String email, String otpCode) {
        OtpDto storedOtp = otpStorage.get(email);
        if (this.checkValidOtp(storedOtp, otpCode)) {
            this.otpStorage.remove(email);
            return "OTP verified successfully: This is the secret message!";
        } else {
            if (storedOtp != null) {
                Integer currentTriedNumber = storedOtp.getTriedNumber();
                if (currentTriedNumber < 5) {
                    storedOtp.setTriedNumber(currentTriedNumber + 1);
                    this.otpStorage.put(email, storedOtp);
                } else {
                    this.otpStorage.remove(email);
                    return "OTP reached more than 5 tried, please request another OPT code!";
                }
                return "OTP is not correct or expired, please try again!";
            }
            return "Not found! please request a new OTP code!";
        }
    }

    private boolean checkValidOtp(OtpDto storedOtp, String otpCode) {
        if (storedOtp != null
                && storedOtp.getExpirationTime().isAfter(LocalDateTime.now())
                && storedOtp.getTriedNumber() < 5) {
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

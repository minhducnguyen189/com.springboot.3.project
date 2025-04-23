package com.springboot.project.controller;

import com.springboot.project.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OtpController {

    private final OtpService otpService;

    @Autowired
    public OtpController(OtpService otpService) {
        this.otpService = otpService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/v1/authentication/otp")
    public ResponseEntity<String> getOtpCode(@RequestParam("email") String email) {
        return ResponseEntity.ok(this.otpService.getOtpCode(email));
    }

    @RequestMapping(method = RequestMethod.POST, path = "/v1/authentication/actions/verify-otp")
    public ResponseEntity<String> verifyOtpCode(
            @RequestParam("email") String email, @RequestParam("otpCode") String otpCode) {
        return ResponseEntity.ok(this.otpService.verifyOtpCode(email, otpCode));
    }
}

package com.springboot.project.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedHeaders("Authorization", "Requester-Type")
                .allowedOriginPatterns("http://localhost:4200")
                .allowedMethods("GET","POST","PATCH", "PUT", "DELETE", "OPTIONS", "HEAD")
                .exposedHeaders("X-Get-Header")
                .maxAge(3600L);
    }

}

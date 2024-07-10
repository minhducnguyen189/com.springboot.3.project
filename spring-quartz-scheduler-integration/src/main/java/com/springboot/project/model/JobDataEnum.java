package com.springboot.project.model;

import lombok.Getter;

@Getter
public enum JobDataEnum {

    SUBJECT("subject"),
    EMAIL_CONTENT("emailContent"),
    EMAIL_ADDRESS("emailAddress");

    private final String value;

    JobDataEnum(String value) {
        this.value = value;
    }
}

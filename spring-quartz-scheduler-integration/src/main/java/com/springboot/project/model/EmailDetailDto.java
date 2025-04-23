package com.springboot.project.model;

import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailDetailDto {

    private UUID id;
    private String subject;
    private String emailContent;
    private String emailAddress;
}

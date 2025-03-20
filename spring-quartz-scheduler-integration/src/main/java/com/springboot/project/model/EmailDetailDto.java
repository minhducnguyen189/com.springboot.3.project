package com.springboot.project.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class EmailDetailDto {

  private UUID id;
  private String subject;
  private String emailContent;
  private String emailAddress;
}

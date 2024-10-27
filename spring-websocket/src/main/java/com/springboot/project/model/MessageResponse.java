package com.springboot.project.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class MessageResponse {
  private String code;
  private String message;
  private String traceId;
}

package com.springboot.project.handler.command.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.springboot.project.handler.command.WebSocketExchange;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class CommandResponse {
  private boolean isBroker;
  private boolean requiredSendingBroker;
  private WebSocketExchange payload;
}

package com.springboot.project.handler.command;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WebSocketExchange {
  private String event;
  private JsonNode data;
}

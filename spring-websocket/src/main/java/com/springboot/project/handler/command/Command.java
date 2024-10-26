package com.springboot.project.handler.command;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

public interface Command {
  default CommandResponse execute(CommandType commandType, JsonNode data, WebSocketSession session)
      throws IOException {
    throw new RuntimeException("Required method not implemented");
  }

  default CommandResponse execute(CommandType commandType, WebSocketSession session)
      throws IOException {
    throw new RuntimeException("Required method not implemented");
  }
}

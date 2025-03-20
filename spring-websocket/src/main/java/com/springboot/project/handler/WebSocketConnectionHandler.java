package com.springboot.project.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class WebSocketConnectionHandler extends TextWebSocketHandler {

  private final WebSocketMessageHandler webSocketMessageHandler;

  @Autowired
  public WebSocketConnectionHandler(WebSocketMessageHandler webSocketMessageHandler) {
    this.webSocketMessageHandler = webSocketMessageHandler;
  }

  @Override
  public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    super.afterConnectionEstablished(session);
    long userId = Long.parseLong(session.getAttributes().get("userId").toString());
    this.webSocketMessageHandler.registerSession(session, userId);
    this.webSocketMessageHandler.joinRoom();
    System.out.println("connection established!");
  }

  @Override
  public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
    super.afterConnectionClosed(session, status);
    this.webSocketMessageHandler.unRegisterSession(session);
    System.out.println("connection closed!");
  }

  @Override
  protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    System.out.println("handle message!");
    String payload = message.getPayload();
    this.webSocketMessageHandler.sendCommand(payload);
  }
}

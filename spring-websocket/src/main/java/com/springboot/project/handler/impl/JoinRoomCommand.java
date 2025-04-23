package com.springboot.project.handler.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.springboot.project.entity.WebsocketLoginEntity;
import com.springboot.project.handler.command.Command;
import com.springboot.project.handler.command.CommandType;
import com.springboot.project.handler.command.WebSocketExchange;
import com.springboot.project.handler.command.model.CommandResponse;
import com.springboot.project.service.WebSocketLoginService;
import com.springboot.project.service.WebSocketService;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.WebSocketSession;

public class JoinRoomCommand implements Command {

    private final WebSocketLoginService webSocketLoginService;
    private final WebSocketService webSocketService;

    @Autowired
    public JoinRoomCommand(
            WebSocketLoginService webSocketLoginService, WebSocketService webSocketService) {
        this.webSocketLoginService = webSocketLoginService;
        this.webSocketService = webSocketService;
    }

    @Override
    public CommandResponse execute(CommandType commandType, JsonNode data, WebSocketSession session)
            throws IOException {

        WebsocketLoginEntity websocketLoginEntity =
                webSocketService.getWebsocketLogin(session.getId());

        this.webSocketLoginService.setCurrentLogin(websocketLoginEntity);

        return CommandResponse.builder()
                .isBroker(commandType.isMessageBroker())
                .payload(
                        WebSocketExchange.builder().event(commandType.getResponseCommand()).build())
                .build();
    }
}

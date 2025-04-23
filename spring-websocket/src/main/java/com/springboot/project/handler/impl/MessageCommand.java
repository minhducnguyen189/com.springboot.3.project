package com.springboot.project.handler.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.springboot.project.handler.command.Command;
import com.springboot.project.handler.command.CommandType;
import com.springboot.project.handler.command.WebSocketExchange;
import com.springboot.project.handler.command.model.CommandResponse;
import java.io.IOException;
import org.springframework.web.socket.WebSocketSession;

public class MessageCommand implements Command {

    @Override
    public CommandResponse execute(CommandType commandType, JsonNode data, WebSocketSession session)
            throws IOException {
        return CommandResponse.builder()
                .isBroker(commandType.isMessageBroker())
                .payload(
                        WebSocketExchange.builder().event(commandType.getResponseCommand()).build())
                .build();
    }
}

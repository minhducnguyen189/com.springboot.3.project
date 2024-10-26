package com.springboot.project.handler;

import com.springboot.project.handler.command.model.CommandDetail;
import com.springboot.project.service.WebSocketLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class WebSocketMessageHandler {

    private final WebSocketLoginService websocketLoginService;
    private final HashMap<String, CommandDetail> commands = new HashMap<>();
    private final List<WebSocketSession> sessions = new ArrayList<>();

    @Autowired
    public WebSocketMessageHandler(WebSocketLoginService websocketLoginService) {
        this.websocketLoginService = websocketLoginService;
    }
}

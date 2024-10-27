package com.springboot.project.handler;

import com.springboot.project.entity.WebsocketLoginEntity;
import com.springboot.project.exception.BusinessException;
import com.springboot.project.handler.command.CommandType;
import com.springboot.project.handler.command.WebSocketExchange;
import com.springboot.project.handler.command.model.CommandDetail;
import com.springboot.project.handler.command.model.CommandResponse;
import com.springboot.project.helper.JacksonHelper;
import com.springboot.project.model.MessageResponse;
import com.springboot.project.service.WebSocketLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Component
public class WebSocketMessageHandler {

    private final WebSocketLoginService websocketLoginService;
    private final HashMap<String, CommandDetail> commands = new HashMap<>();
    private final List<WebSocketSession> sessions = new ArrayList<>();
    private WebSocketSession session;

    @Autowired
    public WebSocketMessageHandler(WebSocketLoginService websocketLoginService) {
        this.websocketLoginService = websocketLoginService;
    }

    public void registerSession(WebSocketSession session, long userId) throws IOException {
        if (this.websocketLoginService.isLogin(userId)) {
            WebsocketLoginEntity websocketLoginEntity = this.websocketLoginService.getLogin(userId);
            WebSocketSession loginSession =
                    this.sessions.stream()
                            .filter(s -> s.getId().equals(websocketLoginEntity.getSessionId()))
                            .findFirst()
                            .orElse(null);
            if (loginSession != null) {
                this.unRegisterSession(loginSession);
            }
        }
        this.websocketLoginService.login(session.getId(), userId);
        this.session = session;
        this.sessions.add(session);
    }

    public void unRegisterSession(WebSocketSession session) throws IOException {
        this.sessions.remove(session);
        this.websocketLoginService.logout(session.getId());
        session.close();
        this.session = null;
    }

    public void joinRoom() throws IOException {
        CommandResponse response =
                commands
                        .get(CommandType.JOIN_ROOM.getResponseCommand())
                        .getCommand()
                        .execute(CommandType.JOIN_ROOM, session);
        this.sendMessage(response);
    }

    public void sendCommand(String payload) throws IOException {
        try {
            WebSocketExchange request = JacksonHelper.toObject(payload, WebSocketExchange.class);
            String command = request.getEvent();
            if (!commands.containsKey(command)) {
                throw new NoSuchMethodException("Invalid event: " + command);
            }
            CommandDetail commandDetail = commands.get(command);

            responseCommand(
                    commandDetail
                            .getCommand()
                            .execute(commandDetail.getCommandType(), request.getData(), session));
        } catch (Exception e) {
            UUID uuid = UUID.randomUUID();
            if (e instanceof BusinessException) {
                sendErrorMessage(((BusinessException) e).getCode(), e.getMessage(), uuid.toString());
            } else {
                sendErrorMessage(
                        HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                        "Internal server error!",
                        uuid.toString());
            }
        }
    }

    private void responseCommand(CommandResponse response) throws IOException {
        String payloadStringValue = JacksonHelper.toJson(response.getPayload());

        // Broker is not used for now
        if (response.isBroker() && !sessions.isEmpty()) {
            sessions.forEach(
                    session -> {
                        try {
                            this.sendMessage(payloadStringValue);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
        } else if (session != null) {
            this.sendMessage(payloadStringValue);
        }
    }

    private void sendMessage(
            CommandResponse response, boolean isBroker, boolean requiredSendingBroker)
            throws IOException {
        String payloadStringValue = JacksonHelper.toJson(response.getPayload());
        if (isBroker) {
            if (requiredSendingBroker) {
                this.sendAllSessions(response);
            }
            return;
        }
        session.sendMessage(new TextMessage(payloadStringValue));
    }

    private void sendMessage(CommandResponse response) throws IOException {
        String payloadStringValue = JacksonHelper.toJson(response.getPayload());
        session.sendMessage(new TextMessage(payloadStringValue));
    }

    private void sendMessage(String response) throws IOException {
        session.sendMessage(new TextMessage(response));
    }

    private void sendErrorMessage(String errorCode, String message, String traceId)
            throws IOException {
        responseCommand(
                CommandResponse.builder()
                        .isBroker(false)
                        .payload(
                                WebSocketExchange.builder()
                                        .event(CommandType.MESSAGE.getResponseCommand())
                                        .data(
                                                JacksonHelper.toJsonNode(
                                                        MessageResponse.builder()
                                                                .code(errorCode)
                                                                .message(message)
                                                                .traceId(traceId)
                                                                .build()))
                                        .build())
                        .build());
    }

    private void sendAllSessions(CommandResponse response) {
        sessions.forEach(
                s -> {
                    try {
                        s.sendMessage(new TextMessage(JacksonHelper.toJson(response.getPayload())));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
    }
}

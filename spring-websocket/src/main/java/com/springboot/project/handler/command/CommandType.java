package com.springboot.project.handler.command;

import com.springboot.project.handler.impl.JoinRoomCommand;
import com.springboot.project.handler.impl.MessageCommand;
import com.springboot.project.handler.impl.PingCommand;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CommandType {
    JOIN_ROOM("join", "joined", true, JoinRoomCommand.class),
    PING_PONG("ping", "pong", false, PingCommand.class),
    MESSAGE("", "message", false, MessageCommand.class),
    ;
    private final String requestCommand;
    private final String responseCommand;
    private final boolean isMessageBroker;
    private final Class<? extends Command> commandObject;

    public List<String> getAllCommands() {
        List<String> commands = new ArrayList<>();
        for (CommandType commandType : CommandType.values()) {
            commands.add(commandType.getRequestCommand());
        }
        return commands;
    }
}

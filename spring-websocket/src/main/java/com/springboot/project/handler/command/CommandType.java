package com.springboot.project.handler.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
public enum CommandType {
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

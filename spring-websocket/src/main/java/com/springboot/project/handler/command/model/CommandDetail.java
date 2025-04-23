package com.springboot.project.handler.command.model;

import com.springboot.project.handler.command.Command;
import com.springboot.project.handler.command.CommandType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CommandDetail {
    private CommandType commandType;
    private Command command;
}

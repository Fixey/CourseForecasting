package ru.liga;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;

/**
 * Объект консоль
 */
@Data
public class Console {
    @Getter
    @Setter
    public String commandName;

    @Getter
    @Setter
    public LinkedList<String> commandArgs = new LinkedList<>();

    public Console(String commandName, LinkedList<String> commandArgs) {
        this.commandName = commandName;
        this.commandArgs = commandArgs;
    }

    public void addArgOfCommand(String argsCommand) {
        this.commandArgs.add(argsCommand);
    }

    /**
     * Обработка команды
     */
    public void invokeCommand() {
        Command command = new CommandSelector().getCommand(commandName);
        command.invoke(commandArgs);
    }

}

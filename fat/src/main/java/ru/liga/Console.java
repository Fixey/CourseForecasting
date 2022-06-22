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
    public String command;

    @Getter
    @Setter
    public LinkedList<String> commandArgs = new LinkedList<>();

    public Console(String command, LinkedList<String> commandArgs) {
        this.command = command;
        this.commandArgs = commandArgs;
    }

    public void addArgOfCommand(String argsCommand) {
        this.commandArgs.add(argsCommand);
    }

    /**
     * Обработка команды
     */
    public void invokeCommand() {
        switch (command) {
            case ("help"):
                new CommandHelp().printHelp();
                break;
            case ("rate"):
                CommandRateValidation commandRateValidation = new CommandRateValidation();
                if (!commandRateValidation.isValidCommandRate(commandArgs)) { //Валидируем аргументы
                    break;
                }
                String currency = commandArgs.get(0).toUpperCase();
                String period = commandArgs.get(1);
                new CommandRate().rateEngine(currency, period);
                break;
            default:
                System.out.println("Unknown command");
        }
    }

}

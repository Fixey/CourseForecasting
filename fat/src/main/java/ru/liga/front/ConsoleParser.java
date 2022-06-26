package ru.liga.front;

import lombok.NonNull;
import ru.liga.exception.ConsoleArgsException;

import java.util.LinkedList;

public class ConsoleParser {
    /**
     * Распарсивает команду с консоли
     *
     * @param args команда
     * @return объект типа Console
     */
    public Console consoleParser(@NonNull String args) {
        try {
            String[] arrParams = args.trim().split("\\s+");
            String command = arrParams[0].toLowerCase();
            LinkedList<String> commandArgs = new LinkedList<>();
            for (int i = 1; i < arrParams.length; i++) {
                commandArgs.add(arrParams[i]);
            }
            return new Console(command, commandArgs);
        } catch (IndexOutOfBoundsException e) {
            throw new ConsoleArgsException();
        }
    }
}

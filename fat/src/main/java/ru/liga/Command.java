package ru.liga;

import java.util.LinkedList;

/**
 * Интрефейс Метода команды
 *
 * @param <T> аргументы команд
 */
interface Command{
    void invoke(LinkedList<String> listArgs);
}
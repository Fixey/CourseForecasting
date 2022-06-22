package ru.liga;

import lombok.NonNull;
import org.apache.commons.lang3.EnumUtils;
import ru.liga.enums.CurrencyType;
import ru.liga.enums.Period;

import java.util.LinkedList;

/**
 * Проверка на корректное написание команды Rate
 */
public class CommandRateValidation {
    /**
     * Проверка на корректное написание команды Rate
     *
     * @param listCommandArgs набор аргументов
     * @return True если все прошло успешно
     */
    public boolean isValidCommandRate(@NonNull LinkedList<String> listCommandArgs) {
        if (listCommandArgs.size() != 2) {
            System.out.println("Command 'rate' has to have 2 parameter currency and period");
            return false;
        }
        if (!EnumUtils.isValidEnumIgnoreCase(CurrencyType.class, listCommandArgs.get(0))) {
            System.out.println("Such Currency in command 'rate' is not exist!");
            return false;
        }
        if (!listCommandArgs.get(1).matches("(\\d{2})/(\\d{2})/(\\d{4})") &&
                !EnumUtils.isValidEnumIgnoreCase(Period.class, listCommandArgs.get(1))) {
            System.out.println("Such Period in command 'rate' is not exist!");
            return false;
        }
        return true;
    }
}

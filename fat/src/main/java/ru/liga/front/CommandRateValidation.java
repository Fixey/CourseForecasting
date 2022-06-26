package ru.liga.front;

import lombok.NonNull;
import org.apache.commons.lang3.EnumUtils;
import ru.liga.enums.CurrencyType;
import ru.liga.enums.Period;
import ru.liga.exception.ArgumentCommandException;
import ru.liga.exception.CurrencyRateException;
import ru.liga.exception.PeriodRateException;

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
    public void ValidCommandRate(@NonNull LinkedList<String> listCommandArgs) {
        if (listCommandArgs.size() < 2 || listCommandArgs.size() > 3) {
            throw new ArgumentCommandException();
        }
        if (!EnumUtils.isValidEnumIgnoreCase(CurrencyType.class, listCommandArgs.get(0))) {
            System.out.println("Such Currency in command 'rate' is not exist!");
            throw new CurrencyRateException();
        }
        if (!listCommandArgs.get(1).matches("(\\d{2})/(\\d{2})/(\\d{4})") &&
                !EnumUtils.isValidEnumIgnoreCase(Period.class, listCommandArgs.get(1))) {
            System.out.println("Such Period in command 'rate' is not exist!");
            throw new PeriodRateException();
        }
    }
}

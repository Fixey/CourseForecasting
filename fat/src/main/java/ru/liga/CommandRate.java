package ru.liga;

import ru.liga.exception.ArgumentCommandException;
import ru.liga.exception.CountDaysException;

import java.util.LinkedList;

/**
 * Класс отвечающий за действия по команде Rate
 */
public class CommandRate implements Command {
    /**
     * Запуск блока команд рассчитывающий курс на период
     *
     * @param listArgs список аргументов [Тип валюты, период]
     */
    public void invoke(LinkedList<String> listArgs) {
        if (listArgs.size() != 2) {
            throw new ArgumentCommandException();
        }
        new CommandRateValidation().ValidCommandRate(listArgs); //Проверим корректное написае команды

        String currency = listArgs.get(0).toUpperCase();
        String period = listArgs.get(1);
        Integer numDays = PeriodUtils.countDayForPeriod(period); //высчитываем кол-во дней за период
        if (numDays == null) {
            throw new CountDaysException();
        }

        //Формируем лист с предсказанными Курсами Валют
        LinkedList<ExchangeRates> listOfForecastingExchangeRates = new ForecastingRate()
                .getListOfForecastingExchangeRates(numDays, currency);
        listOfForecastingExchangeRates.forEach(x -> System.out.println(x.getDateAndRate()));
    }
}

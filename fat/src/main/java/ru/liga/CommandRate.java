package ru.liga;

import lombok.NonNull;
import ru.liga.exception.CountDaysException;

import java.util.LinkedList;

/**
 * Класс отвечающий за действия по команде Rate
 */
public class CommandRate {
    /**
     * Запуск блока команд рассчитывающий курс на период
     *
     * @param currencyType
     * @param period
     */

    public void rateEngine(@NonNull String currencyType, @NonNull String period) {
        Integer numDays = PeriodUtils.countDayForPeriod(period); //высчитываем кол-во дней за период
        if (numDays == null) {
            throw new CountDaysException();
        }
        //Формируем лист с предсказанными Курсами Валют

        LinkedList<ExchangeRates> listOfForecastingExchangeRates = new ForecastingRate()
                .getListOfForecastingExchangeRates(numDays, currencyType);
        listOfForecastingExchangeRates.forEach(x -> System.out.println(x.getDateAndRate()));


    }
}

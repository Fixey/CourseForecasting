package ru.liga;

import lombok.NonNull;
import ru.liga.enums.Period;

import java.time.LocalDate;
import java.util.LinkedList;

public class ForecastingRate {
    /**
     * Формирует лист Курса Валют на n дней
     *
     * @param currency Валюта
     * @param days      на сколько дней рассчитывать
     * @return LinkedList<ExchangeRates> лист Курса валют
     */
    public LinkedList<ExchangeRates> getListOfForecastingExchangeRates(int days, String currency) {

        LinkedList listRates = new CalculationAlgorithm().getListRatesOnAlgorithm(currency); //Получили список ставок по алгоритму
        LinkedList<ExchangeRates> listExchangeRates = new LinkedList<>();
        if (listRates.size() == 0) {
            return null;
        }
        Double rate = 0.0;
        AverageRate averageRate = new AverageRate();
        if (days == 0) {
            rate = averageRate.getAverageRate(listRates);
            listExchangeRates.add(new ExchangeRates(currency,rate, LocalDate.now()));
            return listExchangeRates;

        }
        for (int i = 1; i <= days; i++) {
            rate = averageRate.getAverageRate(listRates);
            listRates.pollLast();
            listRates.offerFirst(rate);
            listExchangeRates.add(new ExchangeRates(currency, rate, LocalDate.now().plusDays(i)));

        }
        return listExchangeRates;
    }
}

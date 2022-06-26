package ru.liga.util;

import lombok.NonNull;
import ru.liga.back.ExchangeRates;

import java.util.LinkedList;

/**
 * Рассчитывает стреднее арифмитическое
 */
public class AverageRate {
    /**
     * Рассчитывает стреднее арифмитическое значение из листа курса
     *
     * @param listRates лист из ставок курса
     * @return Double Среднее арифмитическое курса
     */
    public Double getAverageRate(@NonNull LinkedList<Double> listRates) {
        if (listRates.size() == 0) {
            return null;
        }
        return listRates.stream()
                .mapToDouble(Number::doubleValue)
                .average()
                .getAsDouble();
    }
    public Double getAverageRateForExchangeRates(@NonNull LinkedList<ExchangeRates> listRates) {
        if (listRates.size() == 0) {
            return null;
        }
        return listRates.stream().mapToDouble(ExchangeRates::getRate).average().getAsDouble();
    }
}

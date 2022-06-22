package ru.liga;

import lombok.NonNull;

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
}

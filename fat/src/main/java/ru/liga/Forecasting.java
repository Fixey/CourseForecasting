package ru.liga;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.LinkedList;

public class Forecasting {
    @Getter
    private ArrayList<Double> arrRates;

    public Forecasting() {
        this.arrRates = new ArrayList<Double>();
    }

    /**
     * Спрогнозировать курс на n дней вперед
     *
     * @param queueRates дата в формате dd/MM/yyyy
     * @param days       n дей вперед
     * @return LinkedList лист значений курса за n дней
     */
    public static LinkedList<Double> getListForecastingRates(@NotNull LimitQueue<Double> queueRates, int days) {

        if (queueRates.size() == 0) {
            return null;
        }
        LinkedList<Double> listRates = new LinkedList<>();
        Double rate;
        if (days == 0) {
            while (queueRates.size() > 1) //вытаскиваем последний элемент
            {
                queueRates.poll();
            }
            listRates.add(queueRates.poll());
        }
        for (int i = 0; i < days; i++) {
            rate = getForecastingRate(queueRates);
            queueRates.offer(rate);
            listRates.add(rate);
        }
        return listRates;
    }

    /**
     * Рассчитать среднее арифметическое значение из очереди
     *
     * @param queueRates очередь значений курса
     * @return Double среднее арифметическое значение
     */
    public static Double getForecastingRate(@NotNull LimitQueue<Double> queueRates) {
        if (queueRates.size() == 0) {
            return null;
        }
        return queueRates.stream()
                .mapToDouble(Number::doubleValue)
                .average()
                .getAsDouble();
    }
}

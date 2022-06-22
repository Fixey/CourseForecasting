package ru.liga;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class ForecastingRatesTest {
    @Test
    public void getForecastingRateWithoutExceptions() {
        ForecastingRate forecastingRate = new ForecastingRate();
        forecastingRate.getListOfForecastingExchangeRates(2, "USD");

    }
}

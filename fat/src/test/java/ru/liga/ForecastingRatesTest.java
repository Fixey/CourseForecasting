package ru.liga;


import org.junit.jupiter.api.Test;

public class ForecastingRatesTest {
    @Test
    public void getForecastingRateWithoutExceptions() {
        ForecastingRate forecastingRate = new ForecastingRate();
        forecastingRate.getListOfForecastingExchangeRates(2, "USD");


    }
}

package ru.liga;

import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.*;

public class ForecastingTest {
    private static LimitQueue<Double> getTestParameters(){
        LimitQueue<Double> testQueue = new LimitQueue<>(5);
        testQueue.add(1.11);
        testQueue.add(2.22);
        testQueue.add(3.33);
        testQueue.add(4.44);
        testQueue.add(5.55);
        return testQueue;
    }
    @Test
    public void getRateFromForecastingRateWithoutExceptions() {
        LimitQueue testQueue = getTestParameters();
        Forecasting testForecasting = new Forecasting();
        assertTrue(testForecasting.getForecastingRate(testQueue).equals(3.3300000000000005d));
    }
    @Test
    public void getNullForecastingRate() {
        LimitQueue<Double> testQueue = new LimitQueue<>(5);
        Forecasting testForecasting = new Forecasting();
        assertNull(testForecasting.getForecastingRate(testQueue));
    }

    @Test
    public void getQueueRateWithoutExceptions() {
        LimitQueue<Double> testQueue = getTestParameters();
        Forecasting testForecasting = new Forecasting();
        assertEquals(testForecasting.getListForecastingRates(testQueue,10).size(),10);
        testQueue = getTestParameters();
        assertTrue (testForecasting.getListForecastingRates(testQueue,10).get(0) == 3.3300000000000005d);
        testQueue = getTestParameters();
        assertTrue (testForecasting.getListForecastingRates(testQueue,0).get(0) == 5.55);
    }

    @Test
    public void getNullQueueRate() {
        LimitQueue<Double> testQueue = new LimitQueue<>(5);
        Forecasting testForecasting = new Forecasting();
        assertNull(testForecasting.getListForecastingRates(testQueue,2));
    }
    @Test
    public void wrf() {
        LimitQueue<Double> testQueue = new LimitQueue<>(7);
        testQueue.add(64.5699);
        testQueue.add(63.938);
        testQueue.add(62.0934);
        testQueue.add(60.9656);
        testQueue.add(59.2481);
        testQueue.add(59.1204);
        testQueue.add(58.9568);
        Forecasting testForecasting = new Forecasting();
//        (testForecasting.getListForecastingRates(testQueue,1));
    }
}

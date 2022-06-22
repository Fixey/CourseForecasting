package ru.liga;

import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Объект Курс Валют
 */
@Data
public class ExchangeRates {

    public LocalDate date = null;
    public Double rate = 0.0d;
    public String currency = "";

    ExchangeRates() {

    }

    ExchangeRates(Double rate) {
        this.currency = currency;
        this.rate = rate;
        this.date = LocalDate.now();
    }

    ExchangeRates(Double rate, LocalDate date) {
        this.rate = rate;
        this.date = date;
    }

    ExchangeRates(String currency, Double rate, LocalDate date) {
        this.currency = currency;
        this.rate = rate;
        this.date = date;
    }

    /**
     * Формирование даты и курса в виде "Вт 22.02.2022 - 75,45"
     *
     * @return String
     */
    public String getDateAndRate() {
        final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("EE dd.MM.yyyy");
        final String srtDate = dtf.format(date);

        String result = Character.toUpperCase(srtDate.charAt(0)) + srtDate.substring(1) + " - "
                + String.format("%.2f", rate);

        return result;
    }
}

package ru.liga;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.apache.commons.lang3.EnumUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

import static ru.liga.FileHandler.getQueueRates;

public class Main {
    /**
     * Командная оболчка
     *
     * @param args команда
     */
    static void Engine(String args) throws RateEngineExceptions {
        int i = 1;
        String[] arrParams = args.split(" ");
        switch (arrParams[0].toLowerCase()) {
            case ("help"):
                System.out.println("Options:");
                System.out.println("Rate <Currency> <Period>        Print Rate during period");
                System.out.println("help                            Print commands for this app");
                break;
            case ("rate"):
                if (arrParams.length != 3) {
                    System.out.println("Command 'rate' has to have 2 parameter currency and period");
                    break;
                }
                String currency = arrParams[1].toUpperCase();
                String period = arrParams[2];
                Integer numDays = PeriodUtils.countDayForPeriod(period);
                if (hasRateCommandErrors(currency, numDays)) break;
                rateEngine(currency, numDays);
                break;
            default:
                System.out.println("Unknown command");

        }
    }


    /**
     * Последовательный вызов команд для анализа курса и распечатывания его
     *
     * @param cur  Валюта
     * @param days Кол-во дней за какой надо рассчитать курс
     */
    static void rateEngine(String cur, Integer days) throws RateEngineExceptions {
        try (BufferedReader fileCurrency = new FileHandler().getFileHandler(CurrencyType.valueOf(cur))) {
            LimitQueue<Double> queueRates = getQueueRates(fileCurrency, 7); //Сделали Очередь из ставок из файла
            LinkedList listRatesForPeriod = Forecasting.getListForecastingRates(queueRates, days); //Список ставок за n дней в зависимости от периода
            LinkedList listDates = PeriodUtils.getListOfDatesForPeriod(days);
            for (int i = 0; i < listDates.size(); i++) {
                System.out.println(listDates.get(i) + String.format("%.2f", listRatesForPeriod.get(i)));
            }
        } catch (CsvValidationException | IOException e) {
            throw new RateEngineExceptions();
        }
    }

    /**
     * Проверки команды Rate на написание
     *
     * @param currency Валюта
     * @param numDays  Кол-во дней
     * @return True если упало с ошибкой
     */
    private static boolean hasRateCommandErrors(String currency, Integer numDays) {
        if (!EnumUtils.isValidEnum(CurrencyType.class, currency)) {
            System.out.println("Such Currency in command 'rate' is not exist!");
            return true;
        }
        if (numDays == null) {
            System.out.println("Such Period in command 'rate' is not exist!");
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws RateEngineExceptions {
        System.out.println("Enter your command:");
        Scanner scanner = new Scanner(System.in);
        String arg = scanner.nextLine();
        Engine(arg);
//        Engine("rate eur today");
    }
}


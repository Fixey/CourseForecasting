package ru.liga;

import ru.liga.exception.AverageIndexOutException;

import java.util.LinkedList;

public class LineParser {
    /**
     * Парсит лист из строк и формирует лист ставок за n дней
     *
     * @param listLines лист строк, каждая строка в формате  "nominal;data;curs;cdx"
     *                  "1;01.02.2022;” 86,5032”;Евро"
     * @param days      кол-во дней, на которое производится рассчет
     * @return LinkedList<Double> список ставок за n дней
     */
    public LinkedList<Double> getRatesFromList(LinkedList<String> listLines, int days) {
        try {
            LinkedList<Double> listRates = new LinkedList<>();

            for (int i = 1; i <= days && i < listLines.size(); i++) {
                String line = listLines.get(i);
                String rate = line.split(";")[2];
                double dRate = Double.parseDouble(rate.substring(2, rate.length() - 1).replace(",", "."));
                listRates.add(dRate);
                if (i == days) {
                    return listRates;
                }
            }
            return listRates;
        } catch (IndexOutOfBoundsException e) {
            throw new AverageIndexOutException();
        }
    }

    /**
     * Парсит лист строк и подсчитывает средний курс за n дней
     *
     * @param listLines
     * @param days
     * @return
     */
    public Double getAverageRate(LinkedList<String> listLines, int days) {
        try {
            Double average = 0.0d;
            int counterAverage = 0;
            for (int i = 1; i <= days && i < listLines.size(); i++) {
                String line = listLines.get(i);
                String rate = line.split(";")[2];
                double dRate = Double.parseDouble(rate.substring(2, rate.length() - 1).replace(",", "."));
                average += dRate;
                counterAverage++;
                if (counterAverage == days) {
                    return average / counterAverage;
                }
            }
            return average / counterAverage;
        } catch (IndexOutOfBoundsException e) {
            throw new AverageIndexOutException();
        }
    }
}

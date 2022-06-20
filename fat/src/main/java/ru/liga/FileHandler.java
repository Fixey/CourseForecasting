
package ru.liga;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import lombok.Getter;
import lombok.Setter;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class FileHandler {
    @Getter
    @Setter
    private String fileName;

    /**
     * Создание инстанс файла для будущей обработки без оглавления
     *
     * @param currency_type курс
     * @return CSVReader файл для чтения
     */

    public CSVReader getFileHandler(Currency_type currency_type) throws FileNotFoundException {
        this.fileName = ".\\fat\\src\\main\\csv\\" + currency_type + ".csv";
        final CSVReader READER = new CSVReader(new FileReader(fileName));
        try {
            READER.skip(1);
            return READER;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Создание очереди (Queue) значений курса
     *
     * @param READER Данные из запроса
     * @param days   Идентификатор организации
     * @return Очередь из значений курса
     */

    public static LimitQueue<Double> getQueueRates(CSVReader READER, Integer days) throws CsvValidationException, IOException {
        ArrayList<Double> arrRates = new ArrayList<>(7);
        String[] line = READER.readNext();
        LimitQueue<Double> queueRates = new LimitQueue<>(7);
        for (int i = 1; i <= days && line != null; i++) {
            String rate = line[0].split(";")[2];
            Double dRate = Double.valueOf(rate.substring(2, rate.length() - 1).replace(",", "."));
            arrRates.add(dRate);
            line = READER.readNext();
            if (line == null || i == days) {
                Collections.reverse(arrRates);
                queueRates.addAll(arrRates);
                return queueRates;
            }
        }
        return null;
    }

    /**
     * Рассчет среднеарифметическое значение курса за определенное кол-во дней из файла
     *
     * @param READER файл
     * @param days   кол-во дней
     * @return Double среднеарифметическое значение курса
     */
    public static Double getAverageOfPeriod(CSVReader READER, Integer days) throws CsvValidationException, IOException {
        String[] line = READER.readNext();
        double average = Double.MIN_VALUE;
        int counterAverage = 0;
        while (line != null) {
            String rate = line[0].split(";")[2];
            double dRate = Double.parseDouble(rate.substring(2, rate.length() - 1).replace(",", "."));
            average += dRate;
            line = READER.readNext();
            counterAverage++;
            if (line == null || counterAverage == days) {
                return average / counterAverage;
            }
        }
        return null;
    }

    /**
     * Рассчет среднеарифметическое значение курса за весь период указанный в файле
     *
     * @param READER файл
     * @return Double среднеарифметическое значение курса
     */
    public static Double getAverageTheWholePeriod(CSVReader READER) throws CsvValidationException, IOException {
        String[] line = READER.readNext();
        double average = Double.MIN_VALUE;
        int counterAverage = 0;
        while (line != null) {
            String rate = line[0].split(";")[2];
            double dRate = Double.parseDouble(rate.substring(2, rate.length() - 1).replace(",", "."));
            average += dRate;
            line = READER.readNext();
            counterAverage++;
            if (line == null) {
                return average / counterAverage;
            }
        }
        return null;
    }

    /**
     * Закрывает файл
     *
     * @param csvReader файл
     */
    public void closeFileHandler(CSVReader csvReader) throws IOException {
        csvReader.close();
    }
    public static void main(String[] args) throws IOException, CsvValidationException {
        CSVReader myFile = new FileHandler().getFileHandler(Currency_type.USD);
//        getQueueRates(myFile,7);
        System.out.println(getAverageOfPeriod(myFile, 2));
        myFile.close();
    }
}

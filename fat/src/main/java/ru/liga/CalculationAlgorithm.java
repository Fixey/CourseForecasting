package ru.liga;

import ru.liga.enums.Period;

import java.util.LinkedList;

public class CalculationAlgorithm {
    public LinkedList<Double> getListRatesOnAlgorithm(String currencyType) {
        FileReader fileReader = new FileReader();
        LinkedList<String> listLines = fileReader.getListLinesFromFile("/csv/" + currencyType + ".csv");
        //Формируем лист для рассчета алгоритма
        LineParser lineParser = new LineParser();
        LinkedList<Double> listRates = lineParser.getRatesFromList(listLines, Period.Week.getNumDays());
        return listRates;
    }
}

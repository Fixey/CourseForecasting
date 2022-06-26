package ru.liga.back;

import ru.liga.exception.UnknownAlgorithmException;

/**
 * Выбор алгоритма
 */
public class AlgorithmSelector {
    /**
     * Выбирает алгоритм
     *
     * @param algorithmName название алгоритма
     * @return RateAlgorithm Инстанс алгоритма
     */
    public RateAlgorithm getAlgorithm(String algorithmName) {
        if (algorithmName.isEmpty()) {
            return new AlgorithmForecastingOnAverageOfSomeDays();
        }
        throw new UnknownAlgorithmException();
    }
}

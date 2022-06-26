package ru.liga.exception;

public class ResultAlgorithmIsEmptyException extends RuntimeException {
    public ResultAlgorithmIsEmptyException() {
        super("Result of Algotithm is null!");
    }
}

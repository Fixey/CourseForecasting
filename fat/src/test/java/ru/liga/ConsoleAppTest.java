package ru.liga;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;

public class ConsoleAppTest {
    @Test
    public void getConsoleEngineWithoutExceptions() {
        ConsoleParser consoleParser = new ConsoleParser();
        consoleParser.consoleParser("rate usd today");
        consoleParser.consoleParser("rate usd week");
    }
}

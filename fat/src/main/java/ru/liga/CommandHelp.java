package ru.liga;

/**
 * Команда выполняющая действия по команде help
 */
public class CommandHelp {
    /**
     * Класс отвечающий за действия по команде Help
     */
    public void printHelp() {
        System.out.println("Options:");
        System.out.println("Rate <eur, usd, try> <today, tomorrow, week, month, dd/MM/yyyy>         Print Rate during period");
        System.out.println("help                                                                    Print commands for this app");
        System.out.println("exit                                                                    Exit from app");
    }
}

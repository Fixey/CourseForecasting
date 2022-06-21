package ru.liga;

import org.apache.commons.lang3.EnumUtils;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;
import java.util.Map;

public class PeriodUtils {

    /**
     * Рассчет кол-ва дней с текущего дня до определнное даты
     *
     * @param date дата в формате dd/MM/yyyy
     * @return Integer кол-во дней с сегодняшнего дня до определнное даты
     */
    public static Integer countDaysUntilDate(@NotNull String date) {
        if (date.matches("(\\d{2})/(\\d{2})/(\\d{4})")) {
            final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            final LocalDate date1 = LocalDate.now();
            final LocalDate date2 = LocalDate.parse(date, dtf);
            return (int) ChronoUnit.DAYS.between(date1, date2);
        }
        return null;
    }

    /**
     * Рассчет кол-ва дней с текущего дня до определнное даты
     *
     * @param date дата
     * @return Integer кол-во дней с сегодняшнего дня до определнное даты
     */
    public static Integer countDaysUntilDate(@NotNull LocalDate date) {
        final LocalDate today = LocalDate.now();

        return (int) ChronoUnit.DAYS.between(today, date);
    }

    /**
     * Возвращает лист дат за n дней относительно текущего дня
     *
     * @param days кол-во дней
     * @return Integer кол-во дней с сегодняшнего дня до определнное даты
     */
    public static LinkedList<String> getListOfDatesForPeriod(@NotNull Integer days) {
        String strData = "";
        final LocalDateTime today = LocalDateTime.now();
        LinkedList<String> listDates = new LinkedList<>();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("EE dd.MM.yyyy");

        if(days==0){ //Для текущего дня
            strData = dtf.format(today);
            listDates.add(Character.toUpperCase(strData.charAt(0)) + strData.substring(1) + " - ");
            return listDates;
        }
        for (int i = 1; i <= days; i++) {
            LocalDateTime nextDay = today.plusDays(i);

            strData = dtf.format(nextDay);
            listDates.add(Character.toUpperCase(strData.charAt(0)) + strData.substring(1) + " - ");
        }
        return listDates;
    }

    /**
     * Рассчет кол-ва дней за указанный период
     *
     * @param period период
     * @return Integer кол-во дней до периода
     */
    public static Integer countDayForPeriod(@NotNull String period) {
        if (period.matches("(\\d{2})/(\\d{2})/(\\d{4})")) {
            return countDaysUntilDate(period);
        } else if (EnumUtils.isValidEnumIgnoreCase(Period.class, period)) {
            Map<Period, Integer> dictPeriod = Map.of(
                    Period.Today, 0,
                    Period.Tomorrow, 1,
                    Period.Week, countDaysUntilDate(LocalDate.now().plusWeeks(1)),
                    Period.Month, countDaysUntilDate(LocalDate.now().plusMonths(1)),
                    Period.Year, countDaysUntilDate(LocalDate.now().plusYears(1))
            );
            return dictPeriod.get(EnumUtils.getEnumIgnoreCase(Period.class, period));
        }
        return null;
    }
}

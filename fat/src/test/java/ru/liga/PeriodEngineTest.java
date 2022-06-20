package ru.liga;

import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNull;

public class PeriodEngineTest {
    @Test
    public void countDaysUntilDateWithoutExceptions() {
        PeriodEngine periodEngine = new PeriodEngine();
        LocalDate date2 = LocalDate.now().plusDays(2);
        LocalDate date3 = LocalDate.now().minusDays(2);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        assertNotNull(periodEngine.countDaysUntilDate("21/06/2022"));
        assertTrue(periodEngine.countDaysUntilDate(date2.format(dtf)).equals(2));
        assertTrue(periodEngine.countDaysUntilDate(date3.format(dtf)).equals(-2));
        assertNull(periodEngine.countDaysUntilDate("21-06-2022"));
    }
    @Test
    public void getNullDaysUntilDateTest() {
        PeriodEngine periodEngine = new PeriodEngine();
        assertNull(periodEngine.countDaysUntilDate("21-06-2022"));
    }
    @Test
    public void countDayForPeriodWithoutExceptions() {
        PeriodEngine periodEngine = new PeriodEngine();
        LocalDate date2 = LocalDate.now().plusDays(2);
        LocalDate date3 = LocalDate.now().plusDays(10);
        LocalDate today = LocalDate.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        assertTrue(periodEngine.countDayForPeriod(date2.format(dtf)).equals(2));
        assertTrue(periodEngine.countDayForPeriod(date3.format(dtf)).equals(10));
        assertTrue(periodEngine.countDayForPeriod(today.format(dtf)).equals(0));
        assertTrue(periodEngine.countDayForPeriod("Today").equals(0));
        assertTrue(periodEngine.countDayForPeriod("Tomorrow").equals(1));
        assertTrue(periodEngine.countDayForPeriod("Week").equals(7));

    }
    @Test
    public void getNullCountDayForPeriod() {
        PeriodEngine periodEngine = new PeriodEngine();
        assertNull(periodEngine.countDayForPeriod("WTF"));
        assertNull(periodEngine.countDayForPeriod("22.05.2222"));
    }
    @Test
    public void getNullListOfDatesForPeriod() {
        PeriodEngine periodEngine = new PeriodEngine();
//        assertNull(periodEngine.getListOfDatesForPeriod(1));
//        assertNull(periodEngine.getListOfDatesForPeriod(0));
    }
    @Test
    public void getListOfDatesForPeriodWithoutExceptions() {
        PeriodEngine periodEngine = new PeriodEngine();
        assertTrue(periodEngine.getListOfDatesForPeriod(1).size()==1);
        assertTrue(periodEngine.getListOfDatesForPeriod(0).size()==1);
        assertTrue(periodEngine.getListOfDatesForPeriod(10).size()==10);
    }


}

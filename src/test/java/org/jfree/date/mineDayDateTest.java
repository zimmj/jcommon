package org.jfree.date;

import junit.framework.TestCase;

import java.util.GregorianCalendar;

import static org.jfree.date.DayDate.*;

public class mineDayDateTest extends TestCase {


    public void testStringToWeekdayCode() {
        assertEquals(-1, stringToWeekdayCode("hello"));

        assertEquals(Day.MONDAY.index, stringToWeekdayCode("Montag"));
        assertEquals(Day.MONDAY.index, stringToWeekdayCode("Mo"));
        assertEquals(Day.MONDAY.index, stringToWeekdayCode("montag"));
        assertEquals(Day.MONDAY.index, stringToWeekdayCode("MoNTaG"));
        assertEquals(Day.MONDAY.index, stringToWeekdayCode("mo"));

        assertEquals(Day.TUESDAY.index, stringToWeekdayCode("Dienstag"));
        assertEquals(Day.TUESDAY.index, stringToWeekdayCode("Di"));
        assertEquals(Day.TUESDAY.index, stringToWeekdayCode("dienstag"));

        assertEquals(Day.WEDNESDAY.index, stringToWeekdayCode("Mittwoch"));
        assertEquals(Day.WEDNESDAY.index, stringToWeekdayCode("Mi"));

        assertEquals(Day.THURSDAY.index, stringToWeekdayCode("Donnerstag"));
        assertEquals(Day.THURSDAY.index, stringToWeekdayCode("Do"));

        assertEquals(Day.FRIDAY.index, stringToWeekdayCode("Freitag"));
        assertEquals(Day.FRIDAY.index, stringToWeekdayCode("Fr"));

        assertEquals(Day.SATURDAY.index, stringToWeekdayCode("Samstag"));
        assertEquals(Day.SATURDAY.index, stringToWeekdayCode("Sa"));

        assertEquals(Day.SUNDAY.index, stringToWeekdayCode("Sonntag"));
        assertEquals(Day.SUNDAY.index, stringToWeekdayCode("So"));
    }

    public void testweekdayCodeToString() {

        assertEquals("Montag", weekdayCodeToString(Day.MONDAY));
        assertEquals("Dienstag", weekdayCodeToString(Day.TUESDAY));
        assertEquals("Mittwoch", weekdayCodeToString(Day.WEDNESDAY));
        assertEquals("Donnerstag", weekdayCodeToString(Day.THURSDAY));
        assertEquals("Freitag", weekdayCodeToString(Day.FRIDAY));
        assertEquals("Samstag", weekdayCodeToString(Day.SATURDAY));
        assertEquals("Sonntag", weekdayCodeToString(Day.SUNDAY));
    }


    public void testMonthCodeToString(){
        assertEquals("Januar", monthCodeToString(Month.make(1)));
        assertEquals("Februar", monthCodeToString(Month.make(2)));
        assertEquals("März", monthCodeToString(Month.make(3)));
        assertEquals("April", monthCodeToString(Month.APRIL));
        assertEquals("Mai", monthCodeToString(Month.MAY));
        assertEquals("Juni", monthCodeToString(Month.JUNE));
        assertEquals("Juli", monthCodeToString(Month.JULY));
        assertEquals("August", monthCodeToString(Month.AUGUST));
        assertEquals("September", monthCodeToString(Month.SEPTEMBER));


        assertEquals("Jan", monthCodeToString(Month.make(1), true));
        assertEquals("Feb", monthCodeToString(Month.make(2), true));
        assertEquals("Mär", monthCodeToString(Month.make(3), true));
        assertEquals("Apr", monthCodeToString(Month.make(4), true));
        assertEquals("Mai", monthCodeToString(Month.MAY, true));
        assertEquals("Jun", monthCodeToString(Month.JUNE, true));
        assertEquals("Jul", monthCodeToString(Month.JULY, true));
        assertEquals("Aug", monthCodeToString(Month.AUGUST, true));


        try {
            monthCodeToString(Month.make(-1));
            fail("Invalid Month Code should throw exception");
        }catch (IllegalArgumentException e){

        }
    }

    public void testStringToMonthCode(){
        final String[] shortMonthNames = DATE_FORMAT_SYMBOLS.getShortMonths();
        int month = 1;
        for (String shortMonth : shortMonthNames){
            assertEquals(month,stringToMonthCode(shortMonth));
            month++;
        }

        final String[] monthNames = DATE_FORMAT_SYMBOLS.getMonths();
        month = 1;
        for (String monthName: monthNames){
            assertEquals(month, stringToMonthCode(monthName));
            month++;
        }

        assertEquals(-1, stringToMonthCode("0"));
        assertEquals(-1,stringToMonthCode("14"));

        assertEquals(Month.JANUARY.index, stringToMonthCode("JAN"));
        assertEquals(Month.JANUARY.index, stringToMonthCode("jan"));
        assertEquals(Month.JANUARY.index, stringToMonthCode("januar"));
        assertEquals(Month.JANUARY.index, stringToMonthCode("JANUAR"));
    }

    public void testIsValidWeekInMonthCode(){

        for (int week=0; week<=4; week++){
            assertTrue(isValidWeekInMonthCode(week));
        }
        assertFalse(isValidWeekInMonthCode(-1));
        assertFalse(isValidWeekInMonthCode(5));
    }

    public void testIsLeapYear(){
        assertFalse(isLeapYear(1900));
        assertFalse(isLeapYear(1901));
        assertFalse(isLeapYear(1902));
        assertFalse(isLeapYear(1903));
        assertTrue(isLeapYear(1904));
        assertTrue(isLeapYear(1908));
        assertFalse(isLeapYear(1955));
        assertTrue(isLeapYear(1964));
        assertTrue(isLeapYear(2000));
        assertFalse(isLeapYear(2001));
        assertFalse(isLeapYear(2100));
        assertTrue(isLeapYear(2024));
    }

    public void testLeapYearCount(){
        assertEquals(0, leapYearCount(1900));
        assertEquals(24, leapYearCount(1999));
        assertEquals(25,leapYearCount(2000));
        assertEquals(25, leapYearCount(2001));
        assertEquals(73, leapYearCount(2200));
    }

    public void testLastDayOfMonth(){
        assertEquals(31,lastDayOfMonth(Month.JANUARY, 1901));
        assertEquals(28, lastDayOfMonth(Month.FEBRUARY, 1900));
        assertEquals(29, lastDayOfMonth(Month.FEBRUARY, 2000));
        assertEquals(30, lastDayOfMonth(Month.SEPTEMBER, 2000));
        assertEquals(30, lastDayOfMonth(Month.APRIL, 2000));
    }

    public void testAddDays(){
        DayDate newYears = d(1, Month.JANUARY.index, 1900);
        assertEquals(d(2,1,1900), addDays(1,newYears));
        assertEquals(d(1,2,1900), addDays(31, newYears));
        assertEquals(d(1, 1,1901), addDays(365, newYears));
        assertEquals(d(31,12, 1904), addDays(5*365, newYears));
    }

    private static SpreadsheetDate d (int day, int month, int year){
        return new SpreadsheetDate(day, month, year);
    }

    public void testAddMonth(){
        DayDate newYear = d(1, 1, 2000);
        assertEquals(d(1,2,2000), addMonths(1,newYear));
        assertEquals(d(1,3,2000), addMonths(2,newYear));
        assertEquals(d(28,2,1900), addMonths(1,d(31,1,1900)));
        assertEquals(d(28,2,1900), addMonths(1,d(30,1,1900)));
        assertEquals(d(28,2,1900), addMonths(1,d(29,1,1900)));
        assertEquals(d(27,2,1900), addMonths(1,d(27,1,1900)));

        assertEquals(d(29,2,2000), addMonths(1, d(29,1,2000)));

    }

    public void testAddYear(){
        assertEquals(d(1, 1, 1901), addYears(1, d(1,1,1900)));
        assertEquals(d(28,2,2001), addYears(1, d(29,2,2000)));
    }

    public void testGetPriviouseDayOfWeeek() {

        try {
            getPreviousDayOfWeek(Day.make(-1), d(25, 10, 2018));
            fail("Invalid day of the week, should throw Exception");
        }catch (IllegalArgumentException e){
        }

        assertEquals(d(24,10,2018), getPreviousDayOfWeek(Day.WEDNESDAY, d(25,10,2018)));
        assertEquals(d(31,10,2018), getPreviousDayOfWeek(Day.WEDNESDAY, d(1,11,2018)));
        assertEquals(d(19,10,2018), getPreviousDayOfWeek(Day.FRIDAY, d(25,10,2018)));
    }

    public void testGetFollowingDayOfWeek(){
        assertEquals(d(1,1,2005), getFollowingDayOfWeek(Day.SATURDAY, d(25,12, 2004)));
        assertEquals(d(1,1,2005), getFollowingDayOfWeek(Day.SATURDAY, d(26,12,2004)));
        assertEquals(d(3,3,2004), getFollowingDayOfWeek(Day.WEDNESDAY, d(26,2,2004)));

        try {
            getFollowingDayOfWeek(Day.make(-1),d(1,1,2006));
            fail("Invalid day of week code should throw exception");
        }catch (IllegalArgumentException e){

        }
    }

    public void testGetNearestDayOfWekk() {
        assertEquals(d(16, 4, 2006), getNearestDayOfWeek(Day.SUNDAY, d(16, 4, 2006)));
        assertEquals(d(16, 4, 2006), getNearestDayOfWeek(Day.SUNDAY, d(17, 4, 2006)));
        assertEquals(d(16, 4, 2006), getNearestDayOfWeek(Day.SUNDAY, d(18, 4, 2006)));
        assertEquals(d(16, 4, 2006), getNearestDayOfWeek(Day.SUNDAY, d(19, 4, 2006)));
        assertEquals(d(23, 4, 2006), getNearestDayOfWeek(Day.SUNDAY, d(20, 4, 2006)));
        assertEquals(d(23, 4, 2006), getNearestDayOfWeek(Day.SUNDAY, d(21, 4, 2006)));
        assertEquals(d(23, 4, 2006), getNearestDayOfWeek(Day.SUNDAY, d(22, 4, 2006)));

        assertEquals(d(17, 4, 2006), getNearestDayOfWeek(Day.MONDAY, d(16, 4, 2006)));
        assertEquals(d(17, 4, 2006), getNearestDayOfWeek(Day.MONDAY, d(17, 4, 2006)));
        assertEquals(d(17, 4, 2006), getNearestDayOfWeek(Day.MONDAY, d(18, 4, 2006)));
        assertEquals(d(17, 4, 2006), getNearestDayOfWeek(Day.MONDAY, d(19, 4, 2006)));
        assertEquals(d(17, 4, 2006), getNearestDayOfWeek(Day.MONDAY, d(20, 4, 2006)));
        assertEquals(d(24, 4, 2006), getNearestDayOfWeek(Day.MONDAY, d(21, 4, 2006)));
        assertEquals(d(24, 4, 2006), getNearestDayOfWeek(Day.MONDAY, d(22, 4, 2006)));

        assertEquals(d(18, 4, 2006), getNearestDayOfWeek(Day.TUESDAY, d(16, 4, 2006)));
        assertEquals(d(18, 4, 2006), getNearestDayOfWeek(Day.TUESDAY, d(17, 4, 2006)));
        assertEquals(d(18, 4, 2006), getNearestDayOfWeek(Day.TUESDAY, d(18, 4, 2006)));
        assertEquals(d(18, 4, 2006), getNearestDayOfWeek(Day.TUESDAY, d(19, 4, 2006)));
        assertEquals(d(18, 4, 2006), getNearestDayOfWeek(Day.TUESDAY, d(20, 4, 2006)));
        assertEquals(d(18, 4, 2006), getNearestDayOfWeek(Day.TUESDAY, d(21, 4, 2006)));
        assertEquals(d(25, 4, 2006), getNearestDayOfWeek(Day.TUESDAY, d(22, 4, 2006)));

        assertEquals(d(19, 4, 2006), getNearestDayOfWeek(Day.WEDNESDAY, d(16, 4, 2006)));
        assertEquals(d(19, 4, 2006), getNearestDayOfWeek(Day.WEDNESDAY, d(17, 4, 2006)));
        assertEquals(d(19, 4, 2006), getNearestDayOfWeek(Day.WEDNESDAY, d(18, 4, 2006)));
        assertEquals(d(19, 4, 2006), getNearestDayOfWeek(Day.WEDNESDAY, d(19, 4, 2006)));
        assertEquals(d(19, 4, 2006), getNearestDayOfWeek(Day.WEDNESDAY, d(20, 4, 2006)));
        assertEquals(d(19, 4, 2006), getNearestDayOfWeek(Day.WEDNESDAY, d(21, 4, 2006)));
        assertEquals(d(19, 4, 2006), getNearestDayOfWeek(Day.WEDNESDAY, d(22, 4, 2006)));

        assertEquals(d(13, 4, 2006), getNearestDayOfWeek(Day.THURSDAY, d(16, 4, 2006)));
        assertEquals(d(20, 4, 2006), getNearestDayOfWeek(Day.THURSDAY, d(17, 4, 2006)));
        assertEquals(d(20, 4, 2006), getNearestDayOfWeek(Day.THURSDAY, d(18, 4, 2006)));
        assertEquals(d(20, 4, 2006), getNearestDayOfWeek(Day.THURSDAY, d(19, 4, 2006)));
        assertEquals(d(20, 4, 2006), getNearestDayOfWeek(Day.THURSDAY, d(20, 4, 2006)));
        assertEquals(d(20, 4, 2006), getNearestDayOfWeek(Day.THURSDAY, d(21, 4, 2006)));
        assertEquals(d(20, 4, 2006), getNearestDayOfWeek(Day.THURSDAY, d(22, 4, 2006)));

        assertEquals(d(14, 4, 2006), getNearestDayOfWeek(Day.FRIDAY, d(16, 4, 2006)));
        assertEquals(d(14, 4, 2006), getNearestDayOfWeek(Day.FRIDAY, d(17, 4, 2006)));
        assertEquals(d(21, 4, 2006), getNearestDayOfWeek(Day.FRIDAY, d(18, 4, 2006)));
        assertEquals(d(21, 4, 2006), getNearestDayOfWeek(Day.FRIDAY, d(19, 4, 2006)));
        assertEquals(d(21, 4, 2006), getNearestDayOfWeek(Day.FRIDAY, d(20, 4, 2006)));
        assertEquals(d(21, 4, 2006), getNearestDayOfWeek(Day.FRIDAY, d(21, 4, 2006)));
        assertEquals(d(21, 4, 2006), getNearestDayOfWeek(Day.FRIDAY, d(22, 4, 2006)));

        assertEquals(d(15, 4, 2006), getNearestDayOfWeek(Day.SATURDAY, d(16, 4, 2006)));
        assertEquals(d(15, 4, 2006), getNearestDayOfWeek(Day.SATURDAY, d(17, 4, 2006)));
        assertEquals(d(15, 4, 2006), getNearestDayOfWeek(Day.SATURDAY, d(18, 4, 2006)));
        assertEquals(d(22, 4, 2006), getNearestDayOfWeek(Day.SATURDAY, d(19, 4, 2006)));
        assertEquals(d(22, 4, 2006), getNearestDayOfWeek(Day.SATURDAY, d(20, 4, 2006)));
        assertEquals(d(22, 4, 2006), getNearestDayOfWeek(Day.SATURDAY, d(21, 4, 2006)));
        assertEquals(d(22, 4, 2006), getNearestDayOfWeek(Day.SATURDAY, d(22, 4, 2006)));

        try {
            getNearestDayOfWeek(Day.make(-1),d(1,1,1900));
            fail("Invalid day of week code should throw exception");
        }catch (IllegalArgumentException e) {

        }
    }

    public void testGetEndOfCurrentMonth(){
        DayDate d = DayDate.createInstance(2);
        assertEquals(d(31,1,2006), d.getEndOfCurrentMonth(d(1, 1, 2006)));
        assertEquals(d(31,3,2006), d.getEndOfCurrentMonth(d(1, 3, 2006)));
        assertEquals(d(31,5,2006), d.getEndOfCurrentMonth(d(1, 5, 2006)));
        assertEquals(d(31,7,2006), d.getEndOfCurrentMonth(d(1, 7, 2006)));
        assertEquals(d(31,8,2006), d.getEndOfCurrentMonth(d(1, 8, 2006)));
        assertEquals(d(31,10,2006), d.getEndOfCurrentMonth(d(1, 10, 2006)));
        assertEquals(d(31,12,2006), d.getEndOfCurrentMonth(d(1, 12, 2006)));

        assertEquals(d(28,2,2006), d.getEndOfCurrentMonth(d(1, 2, 2006)));
        assertEquals(d(29,2,2004), d.getEndOfCurrentMonth(d(1, 2, 2004)));
        assertEquals(d(30,4,2006), d.getEndOfCurrentMonth(d(1, 4, 2006)));
        assertEquals(d(30,6,2006), d.getEndOfCurrentMonth(d(1, 6, 2006)));
        assertEquals(d(30,9,2006), d.getEndOfCurrentMonth(d(1, 9, 2006)));
        assertEquals(d(30,11,2006), d.getEndOfCurrentMonth(d(1, 11, 2006)));
    }

    public void testWeekinMonthToString(){
        assertEquals("First", weekInMonthToString(1));
        assertEquals("Second", weekInMonthToString(2));
        assertEquals("Third", weekInMonthToString(3));
        assertEquals("Fourth", weekInMonthToString(4));
        assertEquals("Last", weekInMonthToString(0));

        try{
            weekInMonthToString(-1);
            fail("Invalid week code should throw exception");
        }catch (IllegalArgumentException e){

        }
    }

    public void testRelativeString(){
        assertEquals("Preceding", relativeToString(-1));
        assertEquals("Nearest", relativeToString(0));
        assertEquals("Following" , relativeToString(1));

        try {
            relativeToString(-2);
            fail("Invalid relative, code should throw exception");
        }catch (IllegalArgumentException e){

        }
    }

    public void testCreateInstanceFromDDMMYYYY(){
        DayDate date = createInstance(1, 1, 1900);
        assertEquals(1 , date.getDayOfMonth());
        assertEquals(1 , date.getMonth());
        assertEquals(1900, date.getYYYY());
        assertEquals(2, date.toSerial());
    }

    public void testCreateInstanceFromSerial(){
        assertEquals(d(1,1,1900), createInstance(2));
        assertEquals(d(1,1,1901), createInstance(367));
    }

    public void testCreateInstanceFromJavaDate() {
        assertEquals(d(1,1,1900), createInstance(new GregorianCalendar(1900,0,1).getTime()));

    }
}




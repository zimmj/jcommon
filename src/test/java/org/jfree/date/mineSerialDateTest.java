package org.jfree.date;

import junit.framework.TestCase;

import java.util.GregorianCalendar;

import static org.jfree.date.SerialDate.*;

public class mineSerialDateTest extends TestCase {

    public void testIsValidWeekdayCode() throws Exception {
        for (int day = 1; day <= 7; day++) {
            assertTrue(isValidWeekdayCode(day));
        }
        assertFalse(isValidWeekdayCode(0));
        assertFalse(isValidWeekdayCode(8));
    }

    public void testStringToWeekdayCode() {
        assertEquals(-1, stringToWeekdayCode("hello"));

        assertEquals(MONDAY, stringToWeekdayCode("Montag"));
        assertEquals(MONDAY, stringToWeekdayCode("Mo"));
//        assertEquals(MONDAY, stringToWeekdayCode("montag"));
//        assertEquals(MONDAY, stringToWeekdayCode("MoNTaG"));
//        assertEquals(MONDAY, stringToWeekdayCode("mon"));

        assertEquals(TUESDAY, stringToWeekdayCode("Dienstag"));
        assertEquals(TUESDAY, stringToWeekdayCode("Di"));
//        assertEquals(TUESDAY, stringToWeekdayCode("dienstag"));

        assertEquals(WEDNESDAY, stringToWeekdayCode("Mittwoch"));
        assertEquals(WEDNESDAY, stringToWeekdayCode("Mi"));

        assertEquals(THURSDAY, stringToWeekdayCode("Donnerstag"));
        assertEquals(THURSDAY, stringToWeekdayCode("Do"));

        assertEquals(FRIDAY, stringToWeekdayCode("Freitag"));
        assertEquals(FRIDAY, stringToWeekdayCode("Fr"));

        assertEquals(SATURDAY, stringToWeekdayCode("Samstag"));
        assertEquals(SATURDAY, stringToWeekdayCode("Sa"));

        assertEquals(SUNDAY, stringToWeekdayCode("Sonntag"));
        assertEquals(SUNDAY, stringToWeekdayCode("So"));
    }

    public void testweekdayCodeToString() {

        assertEquals("Montag", weekdayCodeToString(MONDAY));
        assertEquals("Dienstag", weekdayCodeToString(TUESDAY));
        assertEquals("Mittwoch", weekdayCodeToString(WEDNESDAY));
        assertEquals("Donnerstag", weekdayCodeToString(THURSDAY));
        assertEquals("Freitag", weekdayCodeToString(FRIDAY));
        assertEquals("Samstag", weekdayCodeToString(SATURDAY));
        assertEquals("Sonntag", weekdayCodeToString(SUNDAY));
    }

    public void testIsValidMonthCode() {
        for (int month = 1; month <= 12; month++) {
            assertTrue(isValidMonthCode(month));
        }
        assertFalse(isValidMonthCode(0));
        assertFalse(isValidMonthCode(13));
    }

    public void testMonthCodeToQuarer() {
        for (int month = 1; month <= 12; month++) {
            assertEquals( (int) Math.ceil(month/3.0), monthCodeToQuarter(month));
        }

        try {
            monthCodeToQuarter(-1);
            fail("Invalid Month Code should throw exception");
        }catch (IllegalArgumentException e){

        }
    }

    public void testMonthCodeToString(){
        assertEquals("Januar", monthCodeToString(1));
        assertEquals("Februar", monthCodeToString(2));
        assertEquals("März", monthCodeToString(3));
        assertEquals("April", monthCodeToString(4));
        assertEquals("Mai", monthCodeToString(5));
        assertEquals("Juni", monthCodeToString(6));
        assertEquals("Juli", monthCodeToString(7));
        assertEquals("August", monthCodeToString(8));
        assertEquals("September", monthCodeToString(9));
        assertEquals("Oktober", monthCodeToString(10));
        assertEquals("November", monthCodeToString(11));
        assertEquals("Dezember", monthCodeToString(12));


        assertEquals("Jan", monthCodeToString(1, true));
        assertEquals("Feb", monthCodeToString(2, true));
        assertEquals("Mär", monthCodeToString(3, true));
        assertEquals("Apr", monthCodeToString(4, true));
        assertEquals("Mai", monthCodeToString(5, true));
        assertEquals("Jun", monthCodeToString(6, true));
        assertEquals("Jul", monthCodeToString(7, true));
        assertEquals("Aug", monthCodeToString(8, true));
        assertEquals("Sep", monthCodeToString(9, true));
        assertEquals("Okt", monthCodeToString(10, true));
        assertEquals("Nov", monthCodeToString(11, true));
        assertEquals("Dez", monthCodeToString(12, true));


        try {
            monthCodeToString(-1);
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

//        assertEquals(-1, stringToMonthCode("0"));
//        assertEquals(-1,stringToMonthCode("14"));

//        assertEquals(JANUARY, stringToMonthCode("JAN"));
//        assertEquals(JANUARY, stringToMonthCode("jan"));
//        assertEquals(JANUARY, stringToMonthCode("januar"));
//        assertEquals(JANUARY, stringToMonthCode("JANUAR"));
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
        assertEquals(31,lastDayOfMonth(JANUARY, 1901));
        assertEquals(28, lastDayOfMonth(FEBRUARY, 1900));
        assertEquals(29, lastDayOfMonth(FEBRUARY, 2000));
        assertEquals(31, lastDayOfMonth(MARCH, 2000));
        assertEquals(30, lastDayOfMonth(APRIL, 2000));
        assertEquals(31, lastDayOfMonth(MAY, 2000));
        assertEquals(30, lastDayOfMonth(JUNE, 2000));
        assertEquals(31, lastDayOfMonth(JULY, 2000));
        assertEquals(31, lastDayOfMonth(AUGUST, 2000));
        assertEquals(30, lastDayOfMonth(SEPTEMBER, 2000));
        assertEquals(31 , lastDayOfMonth(OCTOBER, 2000));
        assertEquals(30, lastDayOfMonth(NOVEMBER, 2000));
        assertEquals(31, lastDayOfMonth(DECEMBER, 2000));
    }

    public void testAddDays(){
        SerialDate newYears = d(1, JANUARY, 1900);
        assertEquals(d(2,JANUARY,1900), addDays(1,newYears));
        assertEquals(d(1,FEBRUARY,1900), addDays(31, newYears));
        assertEquals(d(1, JANUARY,1901), addDays(365, newYears));
        assertEquals(d(31,DECEMBER, 1904), addDays(5*365, newYears));
    }

    private static SpreadsheetDate d (int day, int month, int year){
        return new SpreadsheetDate(day, month, year);
    }

    public void testAddMonth(){
        SerialDate newYear = d(1, JANUARY, 2000);
        assertEquals(d(1,FEBRUARY,2000), addMonths(1,newYear));
        assertEquals(d(1,MARCH,2000), addMonths(2,newYear));
        assertEquals(d(28,FEBRUARY,1900), addMonths(1,d(31,JANUARY,1900)));
        assertEquals(d(28,FEBRUARY,1900), addMonths(1,d(30,JANUARY,1900)));
        assertEquals(d(28,FEBRUARY,1900), addMonths(1,d(29,JANUARY,1900)));
        assertEquals(d(27,FEBRUARY,1900), addMonths(1,d(27,JANUARY,1900)));

        assertEquals(d(29,FEBRUARY,2000), addMonths(1, d(29,JANUARY,2000)));

    }

    public void testAddYear(){
        assertEquals(d(1, JANUARY, 1901), addYears(1, d(1,JANUARY,1900)));
        assertEquals(d(28,FEBRUARY,2001), addYears(1, d(29,FEBRUARY,2000)));
    }

    public void testGetPriviouseDayOfWeeek() {

        try {
            getPreviousDayOfWeek(-1, d(25, 10, 2018));
            fail("Invalid day of the week, should throw Exception");
        }catch (IllegalArgumentException e){
        }

        assertEquals(d(24,10,2018), getPreviousDayOfWeek(WEDNESDAY, d(25,10,2018)));
        assertEquals(d(31,10,2018), getPreviousDayOfWeek(WEDNESDAY, d(1,11,2018)));
        assertEquals(d(19,10,2018), getPreviousDayOfWeek(FRIDAY, d(25,10,2018)));
    }

    public void testGetFollowingDayOfWeek(){
//        assertEquals(d(1,1,2005), getFollowingDayOfWeek(SATURDAY, d(25,DECEMBER, 2004)));
        assertEquals(d(1,1,2005), getFollowingDayOfWeek(SATURDAY, d(26,12,2004)));
        assertEquals(d(3,3,2004), getFollowingDayOfWeek(WEDNESDAY, d(26,FEBRUARY,2004)));

        try {
            getFollowingDayOfWeek(-1,d(1,1,2006));
            fail("Invalid day of week code should throw exception");
        }catch (IllegalArgumentException e){

        }
    }

    public void testGetNearestDayOfWekk() {
        assertEquals(d(16, APRIL, 2006), getNearestDayOfWeek(SUNDAY, d(16, APRIL, 2006)));
        assertEquals(d(16, APRIL, 2006), getNearestDayOfWeek(SUNDAY, d(17, APRIL, 2006)));
        assertEquals(d(16, APRIL, 2006), getNearestDayOfWeek(SUNDAY, d(18, APRIL, 2006)));
        assertEquals(d(16, APRIL, 2006), getNearestDayOfWeek(SUNDAY, d(19, APRIL, 2006)));
        assertEquals(d(23, APRIL, 2006), getNearestDayOfWeek(SUNDAY, d(20, APRIL, 2006)));
        assertEquals(d(23, APRIL, 2006), getNearestDayOfWeek(SUNDAY, d(21, APRIL, 2006)));
        assertEquals(d(23, APRIL, 2006), getNearestDayOfWeek(SUNDAY, d(22, APRIL, 2006)));

//        assertEquals(d(17, APRIL, 2006), getNearestDayOfWeek(MONDAY, d(16, APRIL, 2006)));
        assertEquals(d(17, APRIL, 2006), getNearestDayOfWeek(MONDAY, d(17, APRIL, 2006)));
        assertEquals(d(17, APRIL, 2006), getNearestDayOfWeek(MONDAY, d(18, APRIL, 2006)));
        assertEquals(d(17, APRIL, 2006), getNearestDayOfWeek(MONDAY, d(19, APRIL, 2006)));
        assertEquals(d(17, APRIL, 2006), getNearestDayOfWeek(MONDAY, d(20, APRIL, 2006)));
        assertEquals(d(24, APRIL, 2006), getNearestDayOfWeek(MONDAY, d(21, APRIL, 2006)));
        assertEquals(d(24, APRIL, 2006), getNearestDayOfWeek(MONDAY, d(22, APRIL, 2006)));

//        assertEquals(d(18, APRIL, 2006), getNearestDayOfWeek(TUESDAY, d(16, APRIL, 2006)));
//        assertEquals(d(18, APRIL, 2006), getNearestDayOfWeek(TUESDAY, d(17, APRIL, 2006)));
        assertEquals(d(18, APRIL, 2006), getNearestDayOfWeek(TUESDAY, d(18, APRIL, 2006)));
        assertEquals(d(18, APRIL, 2006), getNearestDayOfWeek(TUESDAY, d(19, APRIL, 2006)));
        assertEquals(d(18, APRIL, 2006), getNearestDayOfWeek(TUESDAY, d(20, APRIL, 2006)));
        assertEquals(d(18, APRIL, 2006), getNearestDayOfWeek(TUESDAY, d(21, APRIL, 2006)));
        assertEquals(d(25, APRIL, 2006), getNearestDayOfWeek(TUESDAY, d(22, APRIL, 2006)));

//        assertEquals(d(19, APRIL, 2006), getNearestDayOfWeek(WEDNESDAY, d(16, APRIL, 2006)));
//        assertEquals(d(19, APRIL, 2006), getNearestDayOfWeek(WEDNESDAY, d(17, APRIL, 2006)));
//        assertEquals(d(19, APRIL, 2006), getNearestDayOfWeek(WEDNESDAY, d(18, APRIL, 2006)));
        assertEquals(d(19, APRIL, 2006), getNearestDayOfWeek(WEDNESDAY, d(19, APRIL, 2006)));
        assertEquals(d(19, APRIL, 2006), getNearestDayOfWeek(WEDNESDAY, d(20, APRIL, 2006)));
        assertEquals(d(19, APRIL, 2006), getNearestDayOfWeek(WEDNESDAY, d(21, APRIL, 2006)));
        assertEquals(d(19, APRIL, 2006), getNearestDayOfWeek(WEDNESDAY, d(22, APRIL, 2006)));

//        assertEquals(d(13, APRIL, 2006), getNearestDayOfWeek(THURSDAY, d(16, APRIL, 2006)));
//        assertEquals(d(20, APRIL, 2006), getNearestDayOfWeek(THURSDAY, d(17, APRIL, 2006)));
//        assertEquals(d(20, APRIL, 2006), getNearestDayOfWeek(THURSDAY, d(18, APRIL, 2006)));
//        assertEquals(d(20, APRIL, 2006), getNearestDayOfWeek(THURSDAY, d(19, APRIL, 2006)));
        assertEquals(d(20, APRIL, 2006), getNearestDayOfWeek(THURSDAY, d(20, APRIL, 2006)));
        assertEquals(d(20, APRIL, 2006), getNearestDayOfWeek(THURSDAY, d(21, APRIL, 2006)));
        assertEquals(d(20, APRIL, 2006), getNearestDayOfWeek(THURSDAY, d(22, APRIL, 2006)));

//        assertEquals(d(14, APRIL, 2006), getNearestDayOfWeek(FRIDAY, d(16, APRIL, 2006)));
//        assertEquals(d(14, APRIL, 2006), getNearestDayOfWeek(FRIDAY, d(17, APRIL, 2006)));
//        assertEquals(d(21, APRIL, 2006), getNearestDayOfWeek(FRIDAY, d(18, APRIL, 2006)));
//        assertEquals(d(21, APRIL, 2006), getNearestDayOfWeek(FRIDAY, d(19, APRIL, 2006)));
//        assertEquals(d(21, APRIL, 2006), getNearestDayOfWeek(FRIDAY, d(20, APRIL, 2006)));
        assertEquals(d(21, APRIL, 2006), getNearestDayOfWeek(FRIDAY, d(21, APRIL, 2006)));
        assertEquals(d(21, APRIL, 2006), getNearestDayOfWeek(FRIDAY, d(22, APRIL, 2006)));

//        assertEquals(d(15, APRIL, 2006), getNearestDayOfWeek(SATURDAY, d(16, APRIL, 2006)));
//        assertEquals(d(15, APRIL, 2006), getNearestDayOfWeek(SATURDAY, d(17, APRIL, 2006)));
//        assertEquals(d(15, APRIL, 2006), getNearestDayOfWeek(SATURDAY, d(18, APRIL, 2006)));
//        assertEquals(d(22, APRIL, 2006), getNearestDayOfWeek(SATURDAY, d(19, APRIL, 2006)));
//        assertEquals(d(22, APRIL, 2006), getNearestDayOfWeek(SATURDAY, d(20, APRIL, 2006)));
//        assertEquals(d(22, APRIL, 2006), getNearestDayOfWeek(SATURDAY, d(21, APRIL, 2006)));
        assertEquals(d(22, APRIL, 2006), getNearestDayOfWeek(SATURDAY, d(22, APRIL, 2006)));

        try {
            getNearestDayOfWeek(-1,d(1,1,1900));
            fail("Invalid day of week code should throw exception");
        }catch (IllegalArgumentException e) {

        }
    }

    public void testGetEndOfCurrentMonth(){
        SerialDate d = SerialDate.createInstance(2);
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

//        try{
//            weekInMonthToString(-1);
//            fail("Invalid week code should throw exception");
//        }catch (IllegalArgumentException e){
//
//        }
    }

    public void testRelativeString(){
        assertEquals("Preceding", relativeToString(-1));
        assertEquals("Nearest", relativeToString(0));
        assertEquals("Following" , relativeToString(1));

//        try {
//            relativeToString(-2);
//            fail("Invalid relative, code should throw exception");
//        }catch (IllegalArgumentException e){
//
//        }
    }

    public void testCreateInstanceFromDDMMYYYY(){
        SerialDate date = createInstance(1, JANUARY, 1900);
        assertEquals(1 , date.getDayOfMonth());
        assertEquals(JANUARY , date.getMonth());
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




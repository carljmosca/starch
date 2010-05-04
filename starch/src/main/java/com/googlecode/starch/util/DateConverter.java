/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.googlecode.starch.util;

import java.sql.Time;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.logging.Logger;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author moscac
 */
public class DateConverter {
    public DateConverter() {
    }
    /**
     *
     * @param date
     * @return
     */
    private static Logger logger = Logger.getLogger(DateConverter.class.getName());

    public static XMLGregorianCalendar dateToXML(Time time) {
        Date date = new Date(time.getTime());
        return dateToXML(time.getTime());
    }

    public static XMLGregorianCalendar dateToXML(Date date) {
        return dateToXML(date.getTime());
    }

    public static XMLGregorianCalendar dateToXML(long time) {
//        String sTimeZone = System.getProperty("user.timezone");
//        if ((sTimeZone == null) || (sTimeZone.trim().length() == 0)) {
//            sTimeZone = "America/New_York";
//        }
//        TimeZone tz = TimeZone.getTimeZone(sTimeZone);
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(time);
        return dateToXML(calendar);
    }

    public static XMLGregorianCalendar dateToXML(String value) {
        if (value.trim().length() != 8) {
            return dateValueRepresentingNull();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        sdf.setTimeZone(getTimeZone());
        try {
            Date dValue = sdf.parse(value.trim(), new ParsePosition(0));
            Integer year = new Integer(value.substring(0, 4));
            Integer month = new Integer(value.substring(4, 6));
            Integer day = new Integer(value.substring(6, 8));
            GregorianCalendar cal = new GregorianCalendar();
            cal.set(GregorianCalendar.YEAR, year.intValue());
            cal.set(GregorianCalendar.MONTH, month.intValue() - 1);
            cal.set(GregorianCalendar.DAY_OF_MONTH, day.intValue());
            cal.set(GregorianCalendar.MINUTE, 1);
            return dateToXML(cal);
        } catch (NullPointerException npe) {
            return dateValueRepresentingNull();
        }
    }

    public static XMLGregorianCalendar dateToXML(GregorianCalendar calendar) {
        javax.xml.datatype.XMLGregorianCalendar xCalendar = null;

        try {
            javax.xml.datatype.DatatypeFactory factory = javax.xml.datatype.DatatypeFactory.newInstance();
            xCalendar = factory.newXMLGregorianCalendar(calendar);
        } catch (DatatypeConfigurationException dte) {
            logger.fine("Exception creating XMLGregorianCalendar: " + dte.getMessage());
        }
        return xCalendar;
    }

    public static Calendar calendarValueRepresentingNull() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, DATE_REPRESENTING_NULL_YEAR);
        calendar.set(Calendar.MONTH, DATE_REPRESENTING_NULL_MONTH);
        calendar.set(Calendar.DATE, DATE_REPRESENTING_NULL_DATE);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
//        calendar.set(Calendar.ZONE_OFFSET, (calendar.getTimeZone().getRawOffset() +
//                calendar.getTimeZone().getDSTSavings()) / 60000);
        return calendar;
    }

    public static XMLGregorianCalendar dateValueRepresentingNull() {
        XMLGregorianCalendar xCalendar = null;
        try {
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.set(GregorianCalendar.YEAR, DATE_REPRESENTING_NULL_YEAR);
            calendar.set(GregorianCalendar.MONTH, DATE_REPRESENTING_NULL_MONTH);
            calendar.set(GregorianCalendar.DATE, DATE_REPRESENTING_NULL_DATE);
            calendar.set(GregorianCalendar.HOUR, 0);
            calendar.set(GregorianCalendar.MINUTE, 0);
            calendar.set(GregorianCalendar.SECOND, 0);
            calendar.set(GregorianCalendar.MILLISECOND, 0);
            //calendar.set(GregorianCalendar.ZONE_OFFSET, (calendar.getTimeZone().getRawOffset() + calendar.getTimeZone().getDSTSavings()) / 60000);
            javax.xml.datatype.DatatypeFactory factory = javax.xml.datatype.DatatypeFactory.newInstance();

            xCalendar = factory.newXMLGregorianCalendar(calendar);
        } catch (DatatypeConfigurationException dte) {
            logger.fine("Exception creating XMLGregorianCalendar: " + dte.getMessage());
        }
        return xCalendar;
    }

    public static boolean isValueRepresentingNull(XMLGregorianCalendar calendar) {

        return (calendar == null) ||
                ((calendar.getYear() == DATE_REPRESENTING_NULL_YEAR) &&
                //(calendar.getMonth() == DATE_REPRESENTING_NULL_MONTH) &&
                (calendar.getDay() == DATE_REPRESENTING_NULL_DATE));
    }

    public static boolean isValueRepresentingNull(Calendar calendar) {

        return (calendar == null) ||
                ((calendar.get(Calendar.YEAR) == DATE_REPRESENTING_NULL_YEAR) &&
                //(calendar.getMonth() == DATE_REPRESENTING_NULL_MONTH) &&
                (calendar.get(Calendar.DAY_OF_MONTH) == DATE_REPRESENTING_NULL_DATE));
    }

    public static String getDateAsString(XMLGregorianCalendar value) {
        return getDateAsString(value, "MM/dd/yyyy");
    }

    public static String getDateAsString(Calendar value) {
        return getDateAsString(value, "MM/dd/yyyy");
    }

    public static String getDateAsString(Calendar value, String dateFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        sdf.setTimeZone(getTimeZone());
        return sdf.format(value.getTime());
    }

    public static String getDateAsString(XMLGregorianCalendar value, String dateFormat) {
        if (isValueRepresentingNull(value)) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        sdf.setTimeZone(getTimeZone());
        return sdf.format(value.toGregorianCalendar().getTimeInMillis());
    }

    public static TimeZone getTimeZone() {
        String sTimeZone = System.getProperty("user.timezone");
        if ((sTimeZone == null) || (sTimeZone.trim().length() == 0) || (sTimeZone.equalsIgnoreCase("GMT"))) {
            sTimeZone = "America/New_York";
        }
        return TimeZone.getTimeZone(sTimeZone);
    }

    public static int getTimeZoneOffset() {
        long ro = getTimeZone().getRawOffset() / 60000;
        long ds = getTimeZone().getDSTSavings() / 60000;
        return ((int)ro) + ((int)ds); 
    }

    private static int DATE_REPRESENTING_NULL_YEAR = 1899;
    private static int DATE_REPRESENTING_NULL_MONTH = Calendar.JANUARY;
    private static int DATE_REPRESENTING_NULL_DATE = 1;
    public static final long MILLISECS_PER_MINUTE = 60 * 1000;
    public static final long MILLISECS_PER_HOUR = 60 * MILLISECS_PER_MINUTE;
    public static final long MILLISECS_PER_DAY = 24 * MILLISECS_PER_HOUR;
}

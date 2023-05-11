package com.spring.development.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.chrono.ThaiBuddhistChronology;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateUtils {

    public static final String THAI_DATETIME_RANGE_FORMAT = "%s - %s";
    public static final String YYYYMMDD = "yyyyMMdd";
    public static final String YYYY_MM_DD = "YYYY-MM-dd";
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final Locale thaiLocal = new Locale("th", "TH");

    ZoneId zoneId = ZoneId.systemDefault();

    public static LocalDateTime now() {
        return LocalDateTime.now();
    }

    public static String asString(LocalDateTime localDateTime, String pattern) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(pattern);
        return localDateTime.format(df);
    }

    public static String asString(LocalDate localDate, String pattern) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(pattern);
        return localDate.format(df);
    }

    public static String asString(LocalTime localTime, String pattern) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(pattern);
        return localTime.format(df);
    }

    public static LocalDateTime asLocalDateTime(String localDateTimeStr, String pattern) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.parse(localDateTimeStr, df);
    }

    public static LocalDate asLocalDate(String localDateStr, String pattern) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(pattern);
        return LocalDate.parse(localDateStr, df);
    }

    public static LocalTime asLocalTime(String localTimeStr, String pattern) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(pattern);
        return LocalTime.parse(localTimeStr, df);
    }

    public static String convertPattern(String dateTimeStr, String fromPattern, String toPattern) {
        DateTimeFormatter fromDf = DateTimeFormatter.ofPattern(fromPattern);
        LocalDateTime localDateTime = LocalDateTime.parse(dateTimeStr, fromDf);
        DateTimeFormatter toDf = DateTimeFormatter.ofPattern(toPattern);
        return localDateTime.format(toDf);
    }

    public static String formatThaiDate(LocalDateTime localDateTime, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern)
                .withChronology(ThaiBuddhistChronology.INSTANCE)
                .localizedBy(thaiLocal);
        return localDateTime.format(formatter);
    }

    public static String formatRangeThaiDate(LocalDate localDate1, String pattern1, LocalDate localDate2, String pattern2) {
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern(pattern1)
                .withChronology(ThaiBuddhistChronology.INSTANCE)
                .localizedBy(thaiLocal);
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern(pattern2)
                .withChronology(ThaiBuddhistChronology.INSTANCE)
                .localizedBy(thaiLocal);
        return String.format(THAI_DATETIME_RANGE_FORMAT, localDate1.format(formatter1), localDate2.format(formatter2));
    }

    public static Boolean isBeforeOrEqual(LocalDateTime dt1, LocalDateTime dt2) {
        return dt1.isBefore(dt2) || dt1.isEqual(dt2);
    }

    public static Boolean isAfterOrEqual(LocalDateTime dt1, LocalDateTime dt2) {
        return dt1.isAfter(dt2) || dt1.isEqual(dt2);
    }

    public static Boolean isBeforeOrEqual(LocalDate dt1, LocalDate dt2) {
        return dt1.isBefore(dt2) || dt1.isEqual(dt2);
    }

    public static Boolean isAfterOrEqual(LocalDate dt1, LocalDate dt2) {
        return dt1.isAfter(dt2) || dt1.isEqual(dt2);
    }

    public static Boolean isBeforeOrEqual(LocalTime dt1, LocalTime dt2) {
        return !dt1.isAfter(dt2);
    }

    public static Boolean isAfterOrEqual(LocalTime dt1, LocalTime dt2) {
        return !dt1.isBefore(dt2);
    }

    public static Boolean isBetween(LocalTime start, LocalTime end, LocalTime time) {
        return isAfterOrEqual(time, start) && isBeforeOrEqual(time, end);
    }
}

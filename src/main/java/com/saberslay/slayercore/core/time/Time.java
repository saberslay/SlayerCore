package com.saberslay.slayercore.core.time;

/*
 * SlayerCore
 * Copyright (c) 2026 saberslay
 * Licensed under the MIT License.
 */

import java.time.*;
import java.time.format.DateTimeFormatter;

public class Time {

    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("dd-MMM HH:mm:ss");

    private static final DateTimeFormatter TIME_DATE_YEAR_FORMATTER =
            DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss"); // fixed yyyy

    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd-MM-yyyy");

    private static final DateTimeFormatter TIME_FORMATTER =
            DateTimeFormatter.ofPattern("HH:mm:ss");

    // ------------------------------------------------------------
    // CURRENT TIME HELPERS
    // ------------------------------------------------------------

    public static String getCurrentDateTime() {
        return LocalDateTime.now().format(DATE_TIME_FORMATTER);
    }

    public static String getCurrentDate() {
        return LocalDateTime.now().format(DATE_FORMATTER);
    }

    public static String getCurrentTime() {
        return LocalDateTime.now().format(TIME_FORMATTER);
    }

    public static String getCurrentDateAndyear() {
        return LocalDateTime.now().format(TIME_DATE_YEAR_FORMATTER);
    }

    public static LocalDate getCurrentLocalDate() {
        return LocalDateTime.now().toLocalDate();
    }

    public static LocalTime getCurrentLocalTime() {
        return LocalDateTime.now().toLocalTime();
    }

    // ------------------------------------------------------------
    // NEW: UNIVERSAL TIMESTAMP FORMATTER
    // ------------------------------------------------------------

    public static String format(long millis, String pattern) {
        LocalDateTime dt = LocalDateTime.ofInstant(
                Instant.ofEpochMilli(millis),
                ZoneId.systemDefault()
        );
        return dt.format(DateTimeFormatter.ofPattern(pattern));
    }

    // ------------------------------------------------------------
    // NEW: ENTRY FORMATTER (SlayerTally default)
    // ------------------------------------------------------------

    public static String formatEntry(long millis) {
        return format(millis, "dd MMM yyyy  HH:mm");
    }
}
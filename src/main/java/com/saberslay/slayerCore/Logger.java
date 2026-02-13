package com.saberslay.slayerCore;

/*
 * SlayerCore
 * Copyright (c) 2026 saberslay
 * Licensed under the MIT License.
 */

public class Logger {

    public static void log(Level level, String msg) {
        log(level, msg, (byte) 0); // default value
    }

    // Simple log levels
    public enum Level {
        INFO, WARNING, ERROR
    }

    // Log a message with level
    public static void log(Level level, String msg, byte datum) {
        String time = Time.getCurrentDateTime(); // your time method
        switch (level) {
            case INFO:
                System.out.println(time + " " + ConsoleColors.CYAN + "[INFO] " + ConsoleColors.WHITE + msg + ConsoleColors.RESET);
                break;
            case WARNING:
                System.out.println(time + " " + ConsoleColors.YELLOW + "[WARNING] " + ConsoleColors.WHITE + msg + ConsoleColors.RESET);
                break;
            case ERROR:
                System.out.println(time + " " + ConsoleColors.RED + "[ERROR] " + ConsoleColors.WHITE + msg + ConsoleColors.RESET);
                break;
        }
    }

}
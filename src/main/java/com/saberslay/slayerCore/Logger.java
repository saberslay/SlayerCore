package com.saberslay.slayerCore;

/*
 * SlayerCore
 * Copyright (c) 2026 saberslay
 * Licensed under the MIT License.
 */

public class Logger {

    // Simple log levels
    public enum Level {
        INFO, WARNING, ERROR
    }

    // Log a message with level
    public static void log(Level level, String msg) {
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

    // Convenience methods
    public static void info(String msg) {
        log(Level.INFO, msg);
    }

    public static void warn(String msg) {
        log(Level.WARNING, msg);
    }

    public static void error(String msg) {
        log(Level.ERROR, msg);
    }

    // Example usage
    public static void main(String[] args) {
        Logger.info("This is an info message.");
        Logger.warn("This is a warning message.");
        Logger.error("This is an error message.");
    }
}
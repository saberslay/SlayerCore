package com.saberslay.slayerCore;

/*
 * SlayerCore
 * Copyright (c) 2026 saberslay
 * Licensed under the MIT License.
 */

public final class Log {

    // Logs a critical message
    public static void Critical(String msg) {
        System.out.println(
                ConsoleColors.applyColor(ConsoleColors.WHITE, Time.getCurrentDateTime()) + " " +
                        ConsoleColors.applyColor(ConsoleColors.RED, "[Critical]") + " " +
                        msg
        );
    }

    // Logs a warning message
    public static void Warning(String msg) {
        System.out.println(
                ConsoleColors.applyColor(ConsoleColors.WHITE, Time.getCurrentDateTime()) + " " +
                        ConsoleColors.applyColor(ConsoleColors.YELLOW, "[Warning]") + " " +
                        msg
        );
    }

    // Logs an info message
    public static void Info(String msg) {
        System.out.println(
                ConsoleColors.applyColor(ConsoleColors.WHITE, Time.getCurrentDateTime()) + " " +
                        ConsoleColors.applyColor(ConsoleColors.WHITE, "[Info]") + " " +
                        msg
        );
    }

    // Logs a critical exception
    public static void Critical(Exception e) {
        System.out.println(
                ConsoleColors.applyColor(ConsoleColors.WHITE, Time.getCurrentDateTime()) + " " +
                        ConsoleColors.applyColor(ConsoleColors.RED, "[Critical]") + " " +
                        e.getMessage()
        );
        e.printStackTrace(System.out); // prints full stack trace
    }

    // Logs a warning exception
    public static void Warning(Exception e) {
        System.out.println(
                ConsoleColors.applyColor(ConsoleColors.WHITE, Time.getCurrentDateTime()) + " " +
                        ConsoleColors.applyColor(ConsoleColors.YELLOW, "[Warning]") + " " +
                        e.getMessage()
        );
        e.printStackTrace(System.out);
    }

    // Logs an info exception
    public static void Info(Exception e) {
        System.out.println(
                ConsoleColors.applyColor(ConsoleColors.WHITE, Time.getCurrentDateTime()) + " " +
                        ConsoleColors.applyColor(ConsoleColors.WHITE, "[Info]") + " " +
                        e.getMessage()
        );
        e.printStackTrace(System.out);
    }
}
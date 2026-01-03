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
                ConsoleColors.applyColor("\033[1;32m", Time.getCurrentDateTime()) + " " +
                        ConsoleColors.applyColor("\033[1;31m", "[Critical]") + " " +
                        msg
        );
    }

    // Logs a warning message
    public static void Warning(String msg) {
        System.out.println(
                ConsoleColors.applyColor("\033[1;32m", Time.getCurrentDateTime()) + " " +
                        ConsoleColors.applyColor("\033[1;33m", "[Warning]") + " " +
                        msg
        );
    }

    // Logs an info message
    public static void Info(String msg) {
        System.out.println(
                ConsoleColors.applyColor("\033[1;32m", Time.getCurrentDateTime()) + " " +
                        ConsoleColors.applyColor("\033[1;36m", "[Info]") + " " +
                        msg
        );
    }

    // Logs a critical exception
    public static void Critical(Exception e) {
        System.out.println(
                ConsoleColors.applyColor("\033[1;32m", Time.getCurrentDateTime()) + " " +
                        ConsoleColors.applyColor("\033[1;31m", "[Critical]") + " " +
                        e.getMessage()
        );
        e.printStackTrace(System.out); // prints full stack trace
    }

    // Logs a warning exception
    public static void Warning(Exception e) {
        System.out.println(
                ConsoleColors.applyColor("\033[1;32m", Time.getCurrentDateTime()) + " " +
                        ConsoleColors.applyColor("\033[1;33m", "[Warning]") + " " +
                        e.getMessage()
        );
        e.printStackTrace(System.out);
    }

    // Logs an info exception
    public static void Info(Exception e) {
        System.out.println(
                ConsoleColors.applyColor("\033[1;32m", Time.getCurrentDateTime()) + " " +
                        ConsoleColors.applyColor("\033[1;36m", "[Info]") + " " +
                        e.getMessage()
        );
        e.printStackTrace(System.out);
    }
}
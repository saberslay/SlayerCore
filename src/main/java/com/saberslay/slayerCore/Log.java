package com.saberslay.slayerCore;

/*
 * SlayerCore
 * Copyright (c) 2026 saberslay
 * Licensed under the MIT License.
 */

public final class Log {

    private Log() {
        // Utility class
    }

    // ===== INFO =====
    public static void Info(String msg) {
        System.out.println(
                ConsoleColors.applyColor(ConsoleColors.WHITE, Time.getCurrentDateTime()) + " " +
                        ConsoleColors.applyColor(ConsoleColors.CYAN, "[Info]") + " " +
                        ConsoleColors.applyColor(ConsoleColors.WHITE, msg) +
                        ConsoleColors.RESET
        );
    }

    // ===== WARNING =====
    public static void Warning(String msg) {
        System.out.println(
                ConsoleColors.applyColor(ConsoleColors.WHITE, Time.getCurrentDateTime()) + " " +
                        ConsoleColors.applyColor(ConsoleColors.YELLOW, "[Warning]") + " " +
                        ConsoleColors.applyColor(ConsoleColors.WHITE, msg) +
                        ConsoleColors.RESET
        );
    }

    // ===== CRITICAL =====
    public static void Critical(String msg) {
        System.out.println(
                ConsoleColors.applyColor(ConsoleColors.WHITE, Time.getCurrentDateTime()) + " " +
                        ConsoleColors.applyColor(ConsoleColors.RED, "[Critical]") + " " +
                        ConsoleColors.applyColor(ConsoleColors.WHITE, msg) +
                        ConsoleColors.RESET
        );
    }

    // ===== INFO EXCEPTION =====
    public static void Info(Exception e) {
        Info(e.getMessage());
        e.printStackTrace(System.out);
    }

    // ===== WARNING EXCEPTION =====
    public static void Warning(Exception e) {
        Warning(e.getMessage());
        e.printStackTrace(System.out);
    }

    // ===== CRITICAL EXCEPTION =====
    public static void Critical(Exception e) {
        Critical(e.getMessage());
        e.printStackTrace(System.out);
    }
}
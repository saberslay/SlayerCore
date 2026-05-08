package com.saberslay.slayercore.core.logging;

/*
 * SlayerCore
 * Copyright (c) 2026 saberslay
 * Licensed under the MIT License.
 */

import com.saberslay.slayercore.core.time.Time;

public class Logger {

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
}
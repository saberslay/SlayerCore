package com.saberslay.slayerCore;

public class ConsoleColors {

    public static final String RESET = "\033[0m";
    public static final String BLACK = "\033[0;30m";
    public static final String RED = "\033[0;31m";
    public static final String GREEN = "\033[0;32m";
    public static final String YELLOW = "\033[0;33m";
    public static final String BLUE = "\033[0;34m";
    public static final String MAGENTA = "\033[0;35m";
    public static final String CYAN = "\033[0;36m";
    public static final String WHITE = "\033[0;37m";

    public static final String BOLD_RED = "\033[1;31m";
    public static final String BOLD_GREEN = "\033[1;32m";
    public static final String BOLD_YELLOW = "\033[1;33m";
    public static final String BOLD_BLUE = "\033[1;34m";

    // Optional: method to wrap text in color
    public static String colorText(String color, String text) {
        return color + text + RESET;
    }

    // Convenient print methods
    public static void println(String color, String text) {
        System.out.println(color + text + RESET);
    }

    public static void print(String color, String text) {
        System.out.print(color + text + RESET);
    }
}
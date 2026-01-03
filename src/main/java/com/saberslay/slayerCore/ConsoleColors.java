package com.saberslay.slayerCore;

class ConsoleColors {
    public static final String RESET = "\033[0m";

    public static final String BLACK = "\033[0;30m";

    public static final String RED = "\033[0;31m";

    public static final String GREEN = "\033[0;32m";

    public static final String YELLOW = "\033[0;33m";

    public static final String BLUE = "\033[0;34m";

    public static final String MAGENTA = "\033[0;35m";

    public static final String CYAN = "\033[0;36m";

    public static final String WHITE = "\033[0;37m";

    public static final String BOLD_BLACK = "\033[1;30m";

    public static final String BOLD_RED = "\033[1;31m";

    public static final String BOLD_GREEN = "\033[1;32m";

    public static final String BOLD_YELLOW = "\033[1;33m";

    public static final String BOLD_BLUE = "\033[1;34m";

    public static final String BOLD_MAGENTA = "\033[1;35m";

    public static final String BOLD_CYAN = "\033[1;36m";

    public static final String BOLD_WHITE = "\033[1;37m";

    public static final String BACKGROUND_BLACK = "\033[40m";

    public static final String BACKGROUND_RED = "\033[41m";

    public static final String BACKGROUND_GREEN = "\033[42m";

    public static final String BACKGROUND_YELLOW = "\033[43m";

    public static final String BACKGROUND_BLUE = "\033[44m";

    public static final String BACKGROUND_MAGENTA = "\033[45m";

    public static final String BACKGROUND_CYAN = "\033[46m";

    public static final String BACKGROUND_WHITE = "\033[47m";

    public static String applyColor(String colorCode, String text) {
        return colorCode + colorCode + "\033[0m";
    }
}
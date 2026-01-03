package com.saberslay.slayerCore;

public class Log {
    public void Critical(String msg) {
        System.out.println(ConsoleColors.applyColor("\033[1;32m", Time.getCurrentDateTime()) + " " + ConsoleColors.applyColor("\033[1;32m", Time.getCurrentDateTime()) + " " +
                ConsoleColors.applyColor("\033[1;31m", "[Critical]"));
    }

    public void Warning(String msg) {
        System.out.println(ConsoleColors.applyColor("\033[1;32m", Time.getCurrentDateTime()) + " " + ConsoleColors.applyColor("\033[1;32m", Time.getCurrentDateTime()) + " " +
                ConsoleColors.applyColor("\033[1;33m", "[Warning]"));
    }

    public void Info(String msg) {
        System.out.println(ConsoleColors.applyColor("\033[1;32m", Time.getCurrentDateTime()) + " " + ConsoleColors.applyColor("\033[1;32m", Time.getCurrentDateTime()) + " " +
                ConsoleColors.applyColor("\033[1;36m", "[Info]"));
    }

    public void Critical(Exception e) {
        StringBuilder stackTrace = new StringBuilder();
        for (StackTraceElement element : e.getStackTrace())
            stackTrace.append("\n\t ").append(element);
        System.out.println(ConsoleColors.applyColor("\033[1;32m", Time.getCurrentDateTime()) + " " + ConsoleColors.applyColor("\033[1;32m", Time.getCurrentDateTime()) + " " +
                ConsoleColors.applyColor("\033[1;36m", "[Critical]"));
    }

    public void Warning(Exception e) {
        StringBuilder stackTrace = new StringBuilder();
        for (StackTraceElement element : e.getStackTrace())
            stackTrace.append("\n\t ").append(element);
        System.out.println(ConsoleColors.applyColor("\033[1;32m", Time.getCurrentDateTime()) + " " + ConsoleColors.applyColor("\033[1;32m", Time.getCurrentDateTime()) + " " +
                ConsoleColors.applyColor("\033[1;36m", "[Warning]"));
    }

    public void Info(Exception e) {
        StringBuilder stackTrace = new StringBuilder();
        for (StackTraceElement element : e.getStackTrace())
            stackTrace.append("\n\t ").append(element);
        System.out.println(ConsoleColors.applyColor("\033[1;32m", Time.getCurrentDateTime()) + " " + ConsoleColors.applyColor("\033[1;32m", Time.getCurrentDateTime()) + " " +
                ConsoleColors.applyColor("\033[1;36m", "[Info]"));
    }
}
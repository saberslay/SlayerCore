package com.saberslay.slayerCore;

/*
 * SlayerCore
 * Copyright (c) 2026 saberslay
 * Licensed under the MIT License.
 */

public class NumberFormatter {

    private static final String[] ABBREVIATIONS = {
            " K",  // Thousand
            " M",  // Million
            " B",  // Billion
            " T",  // Trillion
            " Qa", // Quadrillion
            " Qi", // Quintillion
            " Sx", // Sextillion
            " Sp", // Septillion
            " O",  // Octillion
            " N",  // Nonillion
            " D",  // Decillion
            " Ud", // Undecillion
            " Dd", // Duodecillion
            " Td", // Tredecillion
            " Qad",// Quattuordecillion
            " Qid",// Quindecillion
            " Sxd",// Sexdecillion
            " Spd",// Septendecillion
            " Od", // Octodecillion
            " Nd", // Novemdecillion
            " Vd"  // Vigintillion
    };

    private static final double[] POWERS;

    static {
        POWERS = new double[ABBREVIATIONS.length];
        for (int i = 0; i < ABBREVIATIONS.length; i++) {
            POWERS[i] = Math.pow(1000, i + 1);
        }
    }

    // Format number with abbreviation
    public static String formatWithAbbreviation(double number) {
        if (Double.isNaN(number) || Double.isInfinite(number)) {
            return "0";
        }

        double absolute = Math.abs(number);

        // If below 1000, return integer without decimals
        if (absolute < 1000) {
            return String.valueOf((int)number);
        }

        int power = (int) Math.min(Math.floor(Math.log10(absolute) / 3), ABBREVIATIONS.length - 1);
        double num = absolute / POWERS[power - 1];

        // If the result is very small (like 0.x), avoid "0.00"
        if (num < 1) {
            return "1" + ABBREVIATIONS[power - 1];  // e.g. 999,999 → 1.00 M
        }

        // Remove trailing ".00" when possible
        String text = String.format("%.2f", num * Math.signum(number));

        if (text.endsWith(".00")) {
            text = text.substring(0, text.length() - 3); // remove .00
        }

        return text + ABBREVIATIONS[power - 1];
    }
}
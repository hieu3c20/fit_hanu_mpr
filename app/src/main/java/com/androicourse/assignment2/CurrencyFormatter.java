package com.androicourse.assignment2;

import java.text.NumberFormat;
import java.util.Locale;

public class CurrencyFormatter {
    public static String format(Long num) {

        String COUNTRY = "VN";
        String LANGUAGE = "vi";

        return NumberFormat.getCurrencyInstance(new Locale(LANGUAGE, COUNTRY)).format(num);
    }
}

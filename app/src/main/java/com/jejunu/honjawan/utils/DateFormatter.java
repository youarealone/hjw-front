package com.jejunu.honjawan.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateFormatter {
    public static String format(Date date, String myFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.KOREA);
        return sdf.format(date);
    }
}

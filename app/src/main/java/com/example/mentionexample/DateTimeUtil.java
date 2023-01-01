package com.example.mentionexample;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

class DateTimeUtil {


    public String convertDateToString(Date date, String format){
        format = "HH:mm dd/MM/yyyy";
        if (date == null){
            return null;
        }
        Locale locale = Locale.getDefault();
        SimpleDateFormat dateFormat = new SimpleDateFormat(format, locale);
        return getDateTimeString(date, dateFormat);
    }

    private String getDateTimeString(Date date, SimpleDateFormat dateFormat) {
        return dateFormat.format(date);
    }


}

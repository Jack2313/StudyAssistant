package com.komnenos.studentassistant.StaticClass;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateParser {
    private static DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static Date stringToDate(String s){
        Date res=new Date();
        try {
            res=format1.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static String dateToString(Date date){
        return format1.format(date);
    }

    public static Date getCurrentDate(){
        Date currentTime = new Date();

        String dateString = format1.format(currentTime);
        Date res=new Date();
        try {
            res=format1.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static Date previous(Date date, int days){
        long t=(long)days*24 * 60 * 60 * 1000;
        return new Date(date.getTime()-t);
    }

    public static Date post(Date date, int days){
        long t=(long)days*24 * 60 * 60 * 1000;
        return new Date(date.getTime()+t);
    }

    public static Date postHours(Date date, int hours){
        long t=(long)hours* 60 * 60 * 1000;
        return new Date(date.getTime()+t);
    }
}

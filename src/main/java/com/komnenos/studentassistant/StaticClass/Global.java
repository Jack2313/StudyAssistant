package com.komnenos.studentassistant.StaticClass;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Random;

public class Global {
    public static String message;

    public static DecimalFormat df = new DecimalFormat("#.00");

    public static HashMap<Integer, Integer> bondUser = new HashMap<Integer, Integer>();

    public static String getRandomString(int length){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    public static double NormalDistribution(double u,double v) {
        java.util.Random random = new java.util.Random();
        return v * random.nextGaussian() + u;
    }
}

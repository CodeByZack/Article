package com.zack.article.Util;

import java.util.Random;

public class Utils {
    public static int randomNum(int min,int max){
        Random random = new Random();
        int s = random.nextInt(max)%(max-min+1) + min;
        return s;
    }
}

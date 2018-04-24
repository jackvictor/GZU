package com.gzu.su.manager.common.utils;

import java.util.Random;

/**
 * @author: jack.ye
 * @create: 2018/04/21 22:18
 * @description: 随机生成16位字符串
 **/
public class RoodomUtils {
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

//可以指定字符串的某个位置是什么范围的值。

    public static String getRandomStringone(int length){

        Random random=new Random();

        StringBuffer sb=new StringBuffer();

        for(int i=0;i<length;i++){

            int number=random.nextInt(3);

            long result=0;

            switch(number){

                case 0:

                    result=Math.round(Math.random()*25+65);

                    sb.append(String.valueOf((char)result));

                    break;

                case 1:

                    result=Math.round(Math.random()*25+97);

                    sb.append(String.valueOf((char)result));

                    break;

                case 2:

                    sb.append(String.valueOf(new Random().nextInt(10)));

                    break;

            }

        }

        return sb.toString();

    }
}

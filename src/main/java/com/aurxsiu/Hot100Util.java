package com.aurxsiu;

import java.util.Scanner;

public class Hot100Util {
    //todo 自动生成对象
    public static void showArray(){
        Scanner scanner = new Scanner(System.in);
        System.out.println(replaceBrace(scanner.nextLine()));
    }

    private static String replaceBrace(String s){
        return s.replace("[", "{").replace("]", "}");
    }

    private static String replaceQuota(String s){
        return s.replace("\"","'");
    }

    //todo 自动生成对象
    public static void showCharArray(){
        Scanner scanner = new Scanner(System.in);
        System.out.println(replaceBrace(replaceQuota(scanner.nextLine())));
    }
}

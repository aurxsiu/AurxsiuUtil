package com.aurxsiu.util.scanner;

import com.aurxsiu.util.scanner.sub.ScanCompleteAction;
import com.aurxsiu.util.scanner.sub.ScanCompleteCondition;

import java.util.Scanner;

public class ScannerUtil {
    private static class Single{
        private static final Scanner scanner = new Scanner(System.in);
    }
    /**
     * {@link ScanCompleteAction#act(String)}控制获取到字符串后的处理
     * {@link ScanCompleteCondition#judge(String)}控制是否停止,默认是收到"exit"后停止
     * */
    public static void scanLoop(ScanCompleteAction action, ScanCompleteCondition condition){
        Scanner scan = Single.scanner;

        while (true){
            String s = scan.nextLine();
            action.act(s);
            if(condition.judge(s)){
                return;
            }
        }
    }
    /**
     * 自己琢磨,我从网上抄的
     * @param pattern 前面的图案 such as "=============="
     * @param code 颜色代号：背景颜色代号(41-46)；前景色代号(31-36)
     * @param n 数字+m：1加粗；3斜体；4下划线
     * @param content 要打印的内容
     */
    public static void printSingleColor(String pattern,int code,int n,String content){
        System.out.format("%s\33[%d;%dm%s\33[0m %n", pattern, code, n, content);
    }

    /**
     * 打印红色,比上面的简单,功能少
     * */
    public static void printlnRed(String content){
        // ANSI escape code constants for text colors
        String RESET = "\u001B[0m";
        String RED = "\u001B[31m";
        String GREEN = "\u001B[32m";
        String YELLOW = "\u001B[33m";

//        System.out.println(GREEN + "This text is green." + RESET);
//        System.out.println(YELLOW + "This text is yellow." + RESET);
//        System.out.println(RED + "This text is red." + RESET);

        System.out.println(RED + content + RESET);
    }
}

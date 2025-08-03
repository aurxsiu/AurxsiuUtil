package com.aurxsiu.scanner;

import com.aurxsiu.scanner.sub.ScanCompleteAction;
import com.aurxsiu.scanner.sub.ScanCompleteCondition;

import java.util.Scanner;

public class ScannerUtil {
    private static class Single{
        private static final Scanner scanner = new Scanner(System.in);
    }
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
}

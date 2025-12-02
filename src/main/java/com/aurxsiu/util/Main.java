package com.aurxsiu.util;

import com.aurxsiu.util.file.FileHelper;
import com.aurxsiu.util.scanner.ScannerUtil;
import com.aurxsiu.util.scanner.sub.ScanCompleteAction;
import com.aurxsiu.util.scanner.sub.ScanCompleteCondition;

public class Main {
//    todo:重构1.8
    public static void main(String[] args) {
        fileTest();
    }

    private static void scanTest(){
        ScannerUtil.scanLoop(new ScanCompleteAction() {
            @Override
            public void act(String s) {
                System.out.println(s);
            }
        }, new ScanCompleteCondition() {
            @Override
            public boolean judge(String s) {
                return ScanCompleteCondition.super.judge(s);
            }
        });
    }

    private static void fileTest(){
        System.out.println(FileHelper.getRootPath());
    }
}
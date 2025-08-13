package com.aurxsiu;

import com.aurxsiu.file.FileHelper;
import com.aurxsiu.scanner.ScannerUtil;
import com.aurxsiu.scanner.sub.ScanCompleteAction;
import com.aurxsiu.scanner.sub.ScanCompleteCondition;

import java.time.Instant;
import java.util.Date;

public class Main {
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
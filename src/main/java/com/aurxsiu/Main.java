package com.aurxsiu;

import com.aurxsiu.scanner.ScannerUtil;
import com.aurxsiu.scanner.sub.ScanCompleteAction;
import com.aurxsiu.scanner.sub.ScanCompleteCondition;

import java.time.Instant;
import java.util.Date;

public class Main {
    public static void main(String[] args) {

        System.out.println("hello");

        System.out.println(Date.from(Instant.now()).toString());
        System.out.println(Date.from(Instant.parse(Date.from(Instant.now()).toString())));

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
}
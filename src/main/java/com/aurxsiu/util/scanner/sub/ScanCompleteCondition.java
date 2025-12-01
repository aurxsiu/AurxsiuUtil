package com.aurxsiu.util.scanner.sub;

public interface ScanCompleteCondition {
    default boolean judge(String s){
        return s.equals("exit");
    };
}

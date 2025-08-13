package com.aurxsiu.system;

import java.io.IOException;

public class SystemUtil {
    /**
     * 5s后让电脑休眠
     * 新建线程是为了能够先处理其他的事情
     * */
    public static void shutdown(){
        new Thread(()->{
            try {
                Thread.sleep(5000);
                Runtime.getRuntime().exec("rundll32 powrprof.dll,SetSuspendState Hibernate");
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}

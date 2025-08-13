package com.aurxsiu.network.netty.handler.action;

import com.aurxsiu.network.netty.handler.ManagerInHandler;

public class DelaySendAction extends Action {
    @Override
    public void readAction(ManagerInHandler input) {
        long start_time = input.getBuf().readLong();
        long end_time = System.currentTimeMillis();
        long cap = end_time - start_time;

        System.out.println("delay: " + cap);
    }
}

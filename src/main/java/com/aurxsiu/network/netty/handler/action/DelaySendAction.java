package com.aurxsiu.network.netty.handler.action;

import com.aurxsiu.network.netty.handler.ManagerInHandler;
import com.aurxsiu.share.action.ActionWithInput;

public class DelaySendAction implements ActionWithInput<ManagerInHandler> {
    @Override
    public void act(ManagerInHandler input) {
        long start_time = input.getBuf().readLong();
        long end_time = System.currentTimeMillis();
        long cap = end_time - start_time;

        System.out.println("delay: " + cap);
    }
}

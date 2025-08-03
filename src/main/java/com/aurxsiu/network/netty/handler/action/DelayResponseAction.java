package com.aurxsiu.network.netty.handler.action;

import com.aurxsiu.network.netty.handler.ManagerInHandler;
import com.aurxsiu.share.action.ActionWithInput;
import io.netty.buffer.ByteBuf;

import static com.aurxsiu.network.netty.share.Code.DELAY_RESPONSE_LAYER;
import static com.aurxsiu.network.netty.share.Code.DELAY_SEND_LAYER;

public class DelayResponseAction implements ActionWithInput<ManagerInHandler> {
    @Override
    public void act(ManagerInHandler input) {
        ByteBuf buf = input.getBuf();
        ByteBuf toMsg = input.getToMsg();

        /*TODO:*/
        System.out.println(DELAY_RESPONSE_LAYER.ordinal());

        toMsg.writeInt(DELAY_SEND_LAYER.ordinal());
        toMsg.writeLong(buf.readLong());
    }
}

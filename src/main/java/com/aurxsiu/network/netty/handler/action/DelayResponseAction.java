package com.aurxsiu.network.netty.handler.action;

import com.aurxsiu.network.netty.handler.ManagerInHandler;
import io.netty.buffer.ByteBuf;

import static com.aurxsiu.network.netty.share.Code.DELAY_RESPONSE_LAYER;
import static com.aurxsiu.network.netty.share.Code.DELAY_SEND_LAYER;

public class DelayResponseAction extends Action {
    @Override
    public void readAction(ManagerInHandler input) {
        ByteBuf buf = input.getBuf();
        ByteBuf toMsg = input.getToMsg();


        toMsg.writeInt(DELAY_SEND_LAYER.ordinal());
        toMsg.writeLong(buf.readLong());
    }
}

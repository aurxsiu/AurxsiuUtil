package com.aurxsiu.util.netty.handler;

import com.aurxsiu.util.netty.share.Code;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

public class OutCountIndexHandler extends ChannelOutboundHandlerAdapter {
    private long index = 0;
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        ByteBuf buf = (ByteBuf)msg;
        buf.writeInt(Code.COUNT_INDEX_LAYER.ordinal());
        buf.writeLong(index);
        index++;
        if(index<0){
            throw new RuntimeException("netty计数Handler溢出!");
        }
    }
}

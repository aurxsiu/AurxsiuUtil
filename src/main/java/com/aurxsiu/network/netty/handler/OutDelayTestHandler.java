package com.aurxsiu.network.netty.handler;

import com.aurxsiu.network.netty.share.Code;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

/**
 * 示范:
 * @see com.aurxsiu.network.netty.example.DelayExample
 * 一般直接加在client端上就行,当然,ManagerInHandler两端都得用
 * 效果是客户端将打印delay
 * */
public class OutDelayTestHandler extends ChannelOutboundHandlerAdapter {
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        ByteBuf buf = (ByteBuf)msg;
        buf.writeInt(Code.DELAY_RESPONSE_LAYER.ordinal());
        buf.writeLong(System.currentTimeMillis());
        ctx.writeAndFlush(msg);
    }
}

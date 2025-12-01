package com.aurxsiu.util.netty.handler;

import com.aurxsiu.util.netty.share.Code;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

import java.util.Random;

public class UdpCountIndexSimulatorHandler extends ChannelOutboundHandlerAdapter {
    private final Random random = new Random(2023211080);
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        buf.writeInt(Code.COUNT_INDEX_LAYER.ordinal());
        buf.writeLong(random.nextLong());
        ctx.writeAndFlush(buf);
    }
}

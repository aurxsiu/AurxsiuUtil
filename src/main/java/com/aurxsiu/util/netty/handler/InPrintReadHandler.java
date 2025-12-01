package com.aurxsiu.util.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.StandardCharsets;

/**
 * 直接打印内容,传递给下一个handler
 * 嗯,因为改造,现在InHandler基本没啥用了....都是通过Action结合ManagerInHandler来写,所以这玩意估计也没啥用了
 */
public class InPrintReadHandler extends ChannelInboundHandlerAdapter {
    private final String header;

    public InPrintReadHandler(String header) {
        this.header = header;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        System.out.println(
                header + " " +
                        buf.toString(StandardCharsets.UTF_8)
        );
        ctx.fireChannelRead(msg);
    }
}

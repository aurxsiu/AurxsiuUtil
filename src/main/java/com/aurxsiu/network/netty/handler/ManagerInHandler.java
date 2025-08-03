package com.aurxsiu.network.netty.handler;

import com.aurxsiu.network.netty.share.Code;
import com.aurxsiu.share.action.ActionWithInput;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 咱们的协议就是多层模型,以Code(int)开头,然后识别{@link Code}并交给匹配的任务处理器(action)处理完就能读到下一个Code,如此循环直到处理完
 * 权限最大,决定是否回复,放在最后,不会传递给下一个InHandler
 * 如果toMsg有内容,就回复
 * TODO:增加"能够添加外部使用者的Code"的功能,虽然对我没必要
 */
public class ManagerInHandler extends ChannelInboundHandlerAdapter {
    private final ActionWithInput<ManagerInHandler> messageAction;

    public ManagerInHandler(ActionWithInput<ManagerInHandler> messageAction) {
        this.messageAction = messageAction;
    }

    private ByteBuf buf;


    private ByteBuf toMsg;

    public ByteBuf getBuf() {
        return buf;
    }

    public ByteBuf getToMsg() {
        return toMsg;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        buf = (ByteBuf) msg;
        toMsg = Unpooled.buffer();

        while (buf.isReadable()) {
            int code_index = buf.readInt();
            if (code_index >= 0) {
                Code code = Code.values()[code_index];
                code.getAction().act(this);
            } else {
                messageAction.act(this);
            }

        }
        if (toMsg.writerIndex() - toMsg.readerIndex() != 0) {
            ctx.writeAndFlush(toMsg);
        }
    }

}

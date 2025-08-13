package com.aurxsiu.network.netty.handler;

import com.aurxsiu.network.netty.share.Code;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 咱们的协议就是多层模型,以Code(int)开头,然后识别{@link Code}并交给匹配的任务处理器(action)处理完就能读到下一个Code,如此循环直到处理完
 * 权限最大,决定是否回复,放在最后,不会传递给下一个InHandler
 * 如果toMsg有内容,就回复
 * 假设收到的byteBuf中没有-1的Code,那么不会调用{@link ActionWithContent},所以在自动响应的时候,比如{@link com.aurxsiu.network.netty.example.DelayExample#main(String[])}中,客户端收到服务端关于delay功能而自动产生的的响应,就不会调用ActionWithContent
 * TODO:缺陷是必须将自己这部分应该读的数据读完,否则肯定异常,需要增加放弃的接口或者在加上managerHandler对负载长度的解析功能,当然,很鸡肋,你凭什么不继续处理!
 * TODO:增加"能够添加外部使用者的Code"的功能,虽然对我没必要
 */
public class ManagerInHandler extends ChannelInboundHandlerAdapter {
    public static interface ActionWithContent{
        public void act(ManagerInHandler managerInHandler);
    }
    private final ActionWithContent messageAction;

    public ManagerInHandler(ActionWithContent messageAction) {
        this.messageAction = messageAction;
    }

    /**
     * 接收的内容
     * */
    private ByteBuf buf;

    /**
     * Action写入后将会由ManagerInHandler通过write发送给另一端
     * */
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
                code.getAction().readAction(this);
            } else {
                messageAction.act(this);
            }

        }

        if (toMsg.writerIndex() - toMsg.readerIndex() != 0) {
            ctx.writeAndFlush(toMsg);
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        for (Code value : Code.values()) {
            value.getAction().destroy(this);
        }
    }
}

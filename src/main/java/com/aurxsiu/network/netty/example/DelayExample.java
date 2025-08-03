package com.aurxsiu.network.netty.example;

import com.aurxsiu.network.netty.client.ClientStarter;
import com.aurxsiu.network.netty.handler.ManagerInHandler;
import com.aurxsiu.network.netty.handler.OutDelayTestHandler;
import com.aurxsiu.network.netty.server.ServerStarter;
import com.aurxsiu.scanner.ScannerUtil;
import com.aurxsiu.scanner.sub.ScanCompleteAction;
import com.aurxsiu.scanner.sub.ScanCompleteCondition;
import com.aurxsiu.share.action.ActionWithInput;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;

import java.nio.charset.StandardCharsets;

/**
 * 延迟的一个示范,简单来说就是添加一个{@link OutDelayTestHandler}就行了
 * 可以copy来作为其它测试的基本框架
 * */
public class DelayExample {
    public static void main(String[] args) {
        new ServerStarter().start0(8080,new ManagerInHandler(new ActionWithInput<ManagerInHandler>() {
            @Override
            public void act(ManagerInHandler managerInHandler) {
                ByteBuf buf = managerInHandler.getBuf();
                System.out.println("服务端>>>"+buf.readString(buf.readInt(), StandardCharsets.UTF_8));
            }
        }));

        Channel channel = new ClientStarter().start0(8080, "127.0.0.1",new OutDelayTestHandler(),new ManagerInHandler(new ActionWithInput<ManagerInHandler>() {
            @Override
            public void act(ManagerInHandler managerInHandler) {
                ByteBuf buf = managerInHandler.getBuf();
                System.out.println("客户端>>>"+buf.readString(buf.readInt(),StandardCharsets.UTF_8));
            }
        }));



        ScannerUtil.scanLoop(new ScanCompleteAction() {
            @Override
            public void act(String s) {
                ByteBuf byteBuf = Unpooled.buffer().writeInt(-1).writeInt(s.getBytes(StandardCharsets.UTF_8).length);

                byteBuf.writeCharSequence(s,StandardCharsets.UTF_8);
                channel.writeAndFlush(byteBuf);
            }
        }, new ScanCompleteCondition() {
        });
    }
}

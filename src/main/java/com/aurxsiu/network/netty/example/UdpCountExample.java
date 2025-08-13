package com.aurxsiu.network.netty.example;

import com.aurxsiu.network.netty.client.ClientUdpStarter;
import com.aurxsiu.network.netty.handler.ManagerInHandler;
import com.aurxsiu.network.netty.handler.UdpCountIndexSimulatorHandler;
import com.aurxsiu.network.netty.server.ServerUdpStarter;
import com.aurxsiu.scanner.ScannerUtil;
import com.aurxsiu.scanner.sub.ScanCompleteAction;
import com.aurxsiu.scanner.sub.ScanCompleteCondition;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;

import java.nio.charset.StandardCharsets;

/**
 * 模拟udp乱序的场景
 * */
public class UdpCountExample {
    public static void main(String[] args) {
        new ServerUdpStarter().start0(3272,new ManagerInHandler(new ManagerInHandler.ActionWithContent() {
            @Override
            public void act(ManagerInHandler managerInHandler) {
//                ByteBuf buf = managerInHandler.getBuf();
//                System.out.println("服务端>>>"+buf.readString(buf.readInt(),StandardCharsets.UTF_8));
                System.out.println("服务端");
            }
        }));

        Channel channel = new ClientUdpStarter().start0(3272, "127.0.0.1", new UdpCountIndexSimulatorHandler(), new ManagerInHandler(new ManagerInHandler.ActionWithContent() {
            @Override
            public void act(ManagerInHandler managerInHandler) {
                /*ByteBuf buf = managerInHandler.getBuf();
                System.out.println("客户端>>>"+buf.readString(buf.readInt(),StandardCharsets.UTF_8));*/
                System.out.println("客户端");
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

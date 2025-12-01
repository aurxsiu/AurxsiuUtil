package com.aurxsiu.util.netty.example;

import com.aurxsiu.util.netty.client.ClientUdpStarter;
import com.aurxsiu.util.netty.handler.ManagerInHandler;
import com.aurxsiu.util.netty.handler.UdpCountIndexSimulatorHandler;
import com.aurxsiu.util.netty.server.ServerUdpStarter;
import com.aurxsiu.util.scanner.ScannerUtil;
import com.aurxsiu.util.scanner.sub.ScanCompleteCondition;
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
                ByteBuf buf = managerInHandler.getBuf();
                System.out.println("服务端>>>"+buf.readString(buf.readInt(),StandardCharsets.UTF_8));
            }
        }));

        Channel channel = new ClientUdpStarter().start0(3272, "127.0.0.1", new UdpCountIndexSimulatorHandler(), new ManagerInHandler(new ManagerInHandler.ActionWithContent() {
            @Override
            public void act(ManagerInHandler managerInHandler) {
                ByteBuf buf = managerInHandler.getBuf();
                System.out.println("客户端>>>"+buf.readString(buf.readInt(),StandardCharsets.UTF_8));
            }
        }));


        ScannerUtil.scanLoop(s -> {
            ByteBuf byteBuf = Unpooled.buffer().writeInt(-1).writeInt(s.getBytes(StandardCharsets.UTF_8).length);

            byteBuf.writeCharSequence(s,StandardCharsets.UTF_8);
            channel.writeAndFlush(byteBuf);
        }, new ScanCompleteCondition() {
        });
    }
}

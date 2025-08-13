package com.aurxsiu.network.netty.client;

import com.aurxsiu.network.netty.handler.InUdpConvertHandler;
import com.aurxsiu.network.netty.handler.OutCountIndexHandler;
import com.aurxsiu.normal.ArrayUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioIoHandler;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioDatagramChannel;

public class ClientUdpStarter {
    public Channel start0(int port, String ip, ChannelHandler... handlers){
        try{
            return udp(handlers).connect(ip, port).sync().channel();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static Bootstrap udp(ChannelHandler... handlers) {
        EventLoopGroup bossGroup = new MultiThreadIoEventLoopGroup(NioIoHandler.newFactory());
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(bossGroup).channel(NioDatagramChannel.class).handler(
                new ChannelInitializer<NioDatagramChannel>() {
                    @Override
                    protected void initChannel(NioDatagramChannel nioDatagramChannel) throws Exception {
                        nioDatagramChannel.pipeline().addLast(ArrayUtil.prepend(new InUdpConvertHandler(),handlers));
                    }
                }
        );
        return bootstrap;
    }
}

package com.aurxsiu.network.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioIoHandler;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class ClientStarter {
    public Channel start0(int port, String ip, ChannelHandler... handlers){
        try{
            EventLoopGroup bossGroup = new MultiThreadIoEventLoopGroup(NioIoHandler.newFactory());
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(bossGroup).channel(NioSocketChannel.class).handler(
                    new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(handlers);
                        }
                    }
            );

            return bootstrap.connect(ip, port).sync().channel();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

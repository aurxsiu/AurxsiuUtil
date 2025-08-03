package com.aurxsiu.network.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioIoHandler;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class ServerStarter {

    /*bossGroup也是可关闭的,需要考虑try-with-resource情况中自动关闭导致bootstrap关闭的情况*/
    public void start0(int port,ChannelHandler... handlers){
        try{
            EventLoopGroup bossGroup = new MultiThreadIoEventLoopGroup(NioIoHandler.newFactory());EventLoopGroup workerGroup = new MultiThreadIoEventLoopGroup(NioIoHandler.newFactory());
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup,workerGroup).channel(NioServerSocketChannel.class).childHandler(
                    new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(handlers);
                        }
                    }
            );

            bootstrap.bind(port);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}

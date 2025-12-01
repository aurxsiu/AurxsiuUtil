package com.aurxsiu.util.netty.server;

import com.aurxsiu.util.netty.client.ClientUdpStarter;
import io.netty.channel.*;

public class ServerUdpStarter {
    public void start0(int port, ChannelHandler... handlers) {
        ClientUdpStarter.udp(handlers).bind(port);
    }
}

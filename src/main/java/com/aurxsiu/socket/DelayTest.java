package com.aurxsiu.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
/**
 * 用netty吧,多依赖点无所谓...
 * */
@Deprecated
public class DelayTest {
    protected void print(String s) {
        System.out.println(s);
    }

    public void buildServer(int port) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            try (Socket accept = serverSocket.accept()) {
                long connectEnd = System.nanoTime();

                // 写入一个 HTTP 请求头（你也可以写任意内容，对方会回应）
                OutputStream out = accept.getOutputStream();
                InputStream in = accept.getInputStream();

                String message = "HEAD / HTTP/1.1\r\nHost: \r\n\r\n";
                byte[] requestBytes = message.getBytes(StandardCharsets.UTF_8);

                long sendTime = System.nanoTime();
                out.write(requestBytes);
                out.flush();

                // 读取回应（只要有数据即可，不必读完整）
                byte[] buffer = new byte[1024];
                int readBytes = in.read(buffer); // 阻塞直到有数据返回或超时
                long receiveTime = System.nanoTime();

                if (readBytes != -1) {
                    long rtt = receiveTime - sendTime;
                    System.out.println("RTT (ms): " + rtt / 1_000_000.0);
                } else {
                    System.out.println("No data received.");
                }
            }
        }
    }

    public void buildClient(int port, String address) throws IOException {
        try (Socket socket = new Socket(address, port)) {
            InputStream inputStream = socket.getInputStream();
            inputStream.read();
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write("hello".getBytes());
        }
    }
}

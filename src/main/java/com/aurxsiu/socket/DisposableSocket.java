package com.aurxsiu.socket;

import java.io.*;
import java.net.Socket;

/**
 * 我觉得还是自己写比较好,这个流的管理太多了,情况太复杂了
 * */
@Deprecated
public class DisposableSocket {
    //todo 升级nio
    public static Socket sendFile(String ip, int port, File file) throws IOException {
        Socket socket = new Socket(ip, port);
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            OutputStream outputStream = socket.getOutputStream();
            byte[] bytes = new byte[1024];
            while(true){
                int read = fileInputStream.read(bytes);
                if(read<0){
                    break;
                }
                outputStream.write(bytes,0,read);
            }
        }
        return socket;
    }

    public static void sendFileJust(String ip, int port, File file) throws IOException {
        sendFile(ip,port,file).close();
    }
}

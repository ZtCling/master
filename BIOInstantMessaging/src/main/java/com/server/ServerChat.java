package com.server;

import com.util.Constants;
import jdk.net.Sockets;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ServerChat {
    //定义一个集合存放所有在线下的socket
    //在线集合只需要一个：储存socket时还需要知道这个socket客户端的名称
    public static Map<Socket,String> onLineSockets = new HashMap();
    public static void main(String[] args) throws IOException {
        //注册端口
        ServerSocket serverSocket = new ServerSocket(Constants.PORT);
        //循环等待所有可能的用户连接
        while(true){
            Socket socket = serverSocket.accept();
            new ServerReader(socket).start();
        }
    }
}

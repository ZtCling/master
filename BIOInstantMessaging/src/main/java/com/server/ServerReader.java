package com.server;



import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Set;

public class ServerReader extends  Thread {
    private Socket socket;
    public  ServerReader(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        DataInputStream dis = null;
        try {
            //从socket管道中得到一个字节数据数据输入流，转变成特殊数据输入流，作用就是等待客户端的消息
            dis = new DataInputStream(socket.getInputStream());
            //1.循环一直等待客户端消息
            while(true){
                //2.读取当前消息类型：登录，群发，私聊，@消息
                int flag = dis.readInt();
                if(flag == 1){
                    //先将当前登录等待客户端socket存入到在线人数的socket集合中
                    String name = dis.readUTF();
                    System.out.println(name + "----->" + socket.getRemoteSocketAddress());
                    ServerChat.onLineSockets.put(socket,name);
                }
                 writeMsg(flag,dis);
            }
        } catch (Exception e) {
            System.out.println("--有人下线了--");
            //从在线人数中将当前Socket移出去
            ServerChat.onLineSockets.remove(socket);
            try {
                writeMsg(1,dis);
            } catch (Exception e1) {
                e1.printStackTrace();
            }

        }
    }
   private void writeMsg(int flag,DataInputStream dis) throws IOException {

        //定义一个变量的最终消息形式
        String  msg = null;
        if (flag==1){
            //读取所有在线人数发给所有客户端去更新自己的在线人数列表
            StringBuilder rs = new StringBuilder();
            Collection<String> onlineNames = ServerChat.onLineSockets.values();
            //判断是否存在在线人数
            if(onlineNames != null && onlineNames.size() > 0){
                for (String name : onlineNames){
                    rs.append(name + com.util.Constants.SPILIT);
                }
                //去掉最后一个分隔符
                msg = rs.substring(0,rs.lastIndexOf(com.util.Constants.SPILIT));
                //将消息发送给所有客户端
                sendMsgToAll(flag,msg);
            }
        }else if(flag == 2 || flag == 3){
            //读到消息， 群发 或者 @消息
            String newMsg = dis.readUTF();//消息
            //得到发件人
            String sendName = ServerChat.onLineSockets.get(socket);
            //内容
            StringBuilder msgFinal = new StringBuilder();
            //时间
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss EEE");
            if (flag == 2){
                msgFinal.append(sendName).append(" ").append(sdf.format(System.currentTimeMillis()*2)).append("\r\n");
                msgFinal.append(" ").append(newMsg).append("\r\n");
                sendMsgToAll(flag,msgFinal.toString());
            }else if (flag == 3){
                msgFinal.append(sendName).append(" ").append(sdf.format(System.currentTimeMillis()*2)).append("对您私发\r\n");
                msgFinal.append(" ").append(newMsg).append("\r\n");
                //私发
                //得到会给谁私发
                String destName = dis.readUTF();
                sendMsgToOne(destName,msgFinal.toString());
            }
        }
   }

   //传入flag和msg,msg是当前所有登陆成功的用户
   private void sendMsgToAll(int flag,String msg) throws IOException {
        //拿到所有的在线socket管道，给这些管道写出消息
       Set<Socket> allOnLineSockets = ServerChat.onLineSockets.keySet();
       for (Socket sk : allOnLineSockets){
           //得到一个字节输出流
           DataOutputStream dos = new DataOutputStream(sk.getOutputStream());
           dos.writeInt(flag);//消息类型
           dos.writeUTF(msg);
           dos.flush();
       }
   }
   //destName 对谁私发，msg 发送消息的内容
   private void  sendMsgToOne(String destName,String msg) throws IOException {
        //拿到所有的在线Socket管道，给这些管道写出消息
       Set<Socket> allOnLineSockets = ServerChat.onLineSockets.keySet();
       for (Socket sk : allOnLineSockets){
           //地得到当前需要私发的socket
           //只对这个名字对应的socket发送消息
           if(ServerChat.onLineSockets.get(sk).trim().equals(destName)){
               DataOutputStream dos = new DataOutputStream(sk.getOutputStream());
               dos.writeInt(2);//消息类型
               dos.writeUTF(msg);
               dos.flush();
           }
       }
   }
}

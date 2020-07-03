package server.java.com.linglett.sendandreceive;

import message.Message;
import message.MessageFriend;
import message.MessageUser;
import server.java.com.linglett.pojo.Friend;
import server.java.com.linglett.pojo.User;
import server.java.com.linglett.utils.GenerateAccount;
import server.java.com.linglett.utils.ImageConversion;
import server.java.com.linglett.utils.MessageDispose;
import server.java.com.linglett.utils.ServerSxUtils;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Server {
    public static CopyOnWriteArrayList<Channal> ALLCHANNAL =new CopyOnWriteArrayList<Channal>();
    public static void main(String[] args) {
        System.out.println("服务器即将启动");
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(9999);
            while(true){
                Socket client =serverSocket.accept();
                System.out.println("一个客户端建立了连接");
                Channal channal =new Channal(client);
                new Thread(channal).start();
            }
        } catch (IOException e) {
            System.out.println("服务器创建失败");
            e.printStackTrace();
        }
    }
    public static class Channal implements Runnable{
        private boolean isRun;
        private User user=new User();
        private Socket client;
        public Channal (Socket client){
            this.client=client;
            isRun =true;
        }
        public void run() {
            while(isRun){
                ObjectOutputStream objectOutputStream=null;
                ObjectInputStream objectInputStream=null;
                try {
                    objectOutputStream =new ObjectOutputStream(client.getOutputStream());
                    objectInputStream =new ObjectInputStream(client.getInputStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Object message =null;
                try{
                    message =receive(objectInputStream);
                } catch (Exception e) {
                    release();
                }
                if (message!=null){
                    try {
                        messageClassification((Message) message,objectOutputStream);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        public void messageClassification(Message message,ObjectOutputStream objectOutputStream) throws FileNotFoundException {
            //消息类型为0为系统消息，01为系统消息里的用户登陆验证请求
            if(message.getMessageFormat().equals("01")){
                this.user.setAccount(message.getSender());
                ALLCHANNAL.add(this);
                User user = MessageDispose.loginMessage(message);

                System.out.println("messageClassification方法");
                if( user!=null){
                    System.out.println("登陆成功的用户头像"+user.getHeadPortrait());
                    ALLCHANNAL.remove(this);
                    this.user=user;
                    ALLCHANNAL.add(this);
                    MessageUser messageUser = new MessageUser("01","Yes&"+user.getAccount()+"&"+user.getPassword()+"&"+user.getUserName()+"&"+user.getSex()+"&"+user.getBirthday()+"&"
                    ,"Server",message.getSender());
                    messageUser.setHeadPortrait(user.getHeadPortrait());
                    sendClass(messageUser,objectOutputStream);
                }else{
                    message.setMessageTextBody("No&");
                    message.setReceiver(message.getSender());
                    message.setSender("Server");
                    sendClass(message,objectOutputStream);
                    System.out.println("登陆失败");
                    release();
                }

            }else if(message.getMessageFormat().equals("02")){
                //成功
                String account = MessageDispose.registerMessage(message);
                this.user.setAccount(message.getSender()+account);
                ALLCHANNAL.add(this);
                Message messageRegister =new Message("02","Yes&"+account+"&"
                        ,"Server",message.getSender()+account);
                sendClass(messageRegister,objectOutputStream);
                release();
            }else if(message.getMessageFormat().equals("03")){
                List<Friend> list = MessageDispose.friendMessage(message);
                Message messageFriend= new MessageFriend("03","Yes&","Server",message.getSender(),list);

                sendClass(messageFriend,objectOutputStream);
            }

        }

        public void sendClass(Message message,ObjectOutputStream objectOutputStream) {
            if (message.getMessageFormat().equals("01")) {
                for (Channal channal : ALLCHANNAL) {
                    if (channal.user.getAccount().equals(message.getReceiver())) {
                        channal.send(message,objectOutputStream);
                        System.out.println("发送01系统登陆回复成功");
                        if (message.getMessageTextBody().equals("No&")) {
                            System.out.println("sendclass" + (message).toString());
                            ALLCHANNAL.remove(channal);
                            System.out.println("登陆失败，移除连接" + channal.getUser().getAccount());
                        }
                        return;
                    }
                }
            } else if (message.getMessageFormat().equals("02")) {
                for (Channal channal : ALLCHANNAL) {
                    if (channal.user.getAccount().equals (message.getReceiver())) {
                        channal.send(message,objectOutputStream);
                        System.out.println("发送02系统登陆回复成功");
                        System.out.println("sendclass" + (message).toString());
                        ALLCHANNAL.remove(channal);
                        System.out.println("注册成功，移除连接" + channal.getUser().getAccount());
                        return;
                    }

                }
            }else if(message.getMessageFormat().equals("03")){
                for (Channal channal : ALLCHANNAL) {
                    if (channal.user.getAccount().equals(message.getReceiver())){
                        channal.send(message,objectOutputStream);
                        System.out.println("发送03系统登陆回复成功");
                        return;
                    }
                }
            }
        }
        public void send(Message message,ObjectOutputStream objectOutputStream){
            try {
                System.out.println("发送给"+this.user.getAccount());
                objectOutputStream.writeObject(message);
                objectOutputStream.flush();
            } catch (IOException e) {
                System.out.println("服务器send方法出错");
                e.printStackTrace();
            }

        }
        public Object receive(ObjectInputStream objectInputStream){
            Object message = null;
            try {
                message =objectInputStream.readObject();
            } catch (IOException e) {
                release();
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                release();
            }
            return message;
        }
        private void release() {
            this.isRun = false;
            // sendOthers(this.name + "离开了聊天室", true);//会发送两次
            ALLCHANNAL.remove(this);
        }
        public User getUser() {
            return user;
        }
    }

}

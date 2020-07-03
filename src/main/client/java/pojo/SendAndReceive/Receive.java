package client.java.pojo.SendAndReceive;

import client.java.stage.ChatStage;
import client.java.stage.LoginStage;
import client.java.stage.RegisterSucceedStage;
import client.java.utils.ImageConversion;
import javafx.application.Platform;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import message.Message;
import client.java.utils.SxUtils;
import message.MessageFriend;
import server.java.com.linglett.pojo.Friend;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.List;

import static client.java.controller.ControllerChatCopy.FRIENDLIST;
import static client.java.controller.ControllerLogin.CONTROLLERLOGIN;
import static client.java.controller.ControllerRegister.CONTROLLERREGISTER;

public class Receive  implements Runnable {

    private boolean isRun=true;
    private Socket clientConnection;
    public Receive(Socket clientConnection) {
        this.clientConnection = clientConnection;
    }
    public void run() {
        Message message =null;
        while(isRun){
            ObjectInputStream objectInputStream=null;
            try {
                objectInputStream=new ObjectInputStream(clientConnection.getInputStream());
            } catch (IOException e) {
                release();
                e.printStackTrace();
            }
            try {
                message = receive(objectInputStream);
            } catch (IOException e) {
                System.out.println("客户端接收消息错误");
                e.printStackTrace();
            }
            if(message!=null){
                messageClassification(message);
        }
        }
    }
    public Message receive(ObjectInputStream objectInputStream) throws IOException {
        Message message = null;
        try {
            message=(Message)objectInputStream.readObject();
            System.out.println("收到"+message);
        } catch (IOException e) {
            release();
            System.out.println("客户端接收消息错误");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            release();
            System.out.println("客户端接收消息错误");
            e.printStackTrace();
        }
        return message;
    }
    public void release(){
        this.isRun=false;
    }

     public void messageClassification(Message message){
        //消息类型为0为系统消息，01为系统消息里的用户登陆验证请求
        if(message.getMessageFormat().equals("01")){
            String[] strings = message.getMessageTextBody().split("&");
            if(strings[0].equals("Yes")){
                CONTROLLERLOGIN.loginSucceed(message);
            }else if(strings[0].equals("No")){
                CONTROLLERLOGIN.loginDefeat();
            }else{
                System.out.println("receive类中messageClassification方法异常"+strings[0]+message);
            }

        }else if(message.getMessageFormat().equals("02")){
            String[] strings = message.getMessageTextBody().split("&");
            if(strings[0].equals("Yes")){
                try {
                    CONTROLLERREGISTER.registerSucceed(strings[1]);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }else if(message.getMessageFormat().equals("03")){
            MessageFriend messageFriend = (MessageFriend)message;
            System.out.println("messageFriend"+messageFriend);
            List<Friend> list =messageFriend.getList();
            String URL="C:\\Users\\12508\\IdeaProjects\\ChatProject\\src\\main\\client\\java\\friendimage\\";
            for (Friend friend : list) {
                ImageConversion.byteToImage(friend.getFriendHead(),URL,friend.getFriendAccount()+".jpg");
                client.java.pojo.Friend friendClient= new client.java.pojo.Friend(friend.getFriendAccount(),URL+friend.getFriendAccount()+".jpg",friend.getFriendNote(),friend.getFriendFriendName(),friend.getFriendSex(),friend.getFriend_birthday());
                FRIENDLIST.add(friendClient);
            }
            //开启chatyemian
            Platform.runLater(new Runnable() {
                public void run() {
                    LoginStage.LOGINSTAGE.close();
                    ChatStage chatStage = new ChatStage();
                    try {
                        chatStage.start(new Stage());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });


        }

    }
}

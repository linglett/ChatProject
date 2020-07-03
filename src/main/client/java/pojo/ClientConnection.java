package client.java.pojo;

import client.java.pojo.SendAndReceive.Receive;
import client.java.pojo.SendAndReceive.Send;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.net.Socket;

public class ClientConnection implements Runnable{
    public static Send SEND;
    public static Receive RECEIVE;
    private String account;
    public ClientConnection(String account) {
        this.account = account;
    }
    public void run() {
        try {
            Socket client =new Socket("localhost",9999);
            SEND = new Send(client);
            RECEIVE=new Receive(client);
            new Thread(RECEIVE).start();
            System.out.println("RECEIVE启动监听");
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.titleProperty().set("AlwaysOnline");
            alert.headerTextProperty().set("服务器连接失败！");
            alert.showAndWait();
        }
    }
}

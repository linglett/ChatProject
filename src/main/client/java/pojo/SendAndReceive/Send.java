package client.java.pojo.SendAndReceive;

import message.Message;
import client.java.utils.SxUtils;


import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Send {
    private Socket clientConnection;
    ObjectOutputStream objectOutputStream;
    public void setClientConnection(Socket clientConnection) {
        this.clientConnection = clientConnection;
    }

    public Send(Socket clientConnection) {
        this.clientConnection = clientConnection;
    }

    public void send(Message message) throws IOException {
        objectOutputStream= new ObjectOutputStream(clientConnection.getOutputStream());
        try {
            objectOutputStream.writeObject(message);
            objectOutputStream.flush();
            System.out.println("发送成功");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("send方法故障");
            release();
        }
    }
    public void release(){
        SxUtils.close(objectOutputStream,clientConnection);
    }

}

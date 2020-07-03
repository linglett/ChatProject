package client.java.controller;

import client.java.stage.RegisterStage;
import client.java.utils.ImageConversion;
import client.java.utils.SxUtils;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import message.Message;
import client.java.pojo.ClientConnection;
import client.java.pojo.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import message.MessageUser;
import server.java.com.linglett.mapper.UserMapper;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static client.java.pojo.ClientConnection.RECEIVE;
import static client.java.pojo.ClientConnection.SEND;
import static client.java.stage.RegisterStage.REGISTERSTAGE;


public class ControllerLogin implements Initializable {
    public static ControllerLogin CONTROLLERLOGIN;
    public static User USER_DADA;
    @FXML
    private Button btlogin;
    @FXML
    private Button register;
    @FXML
    private TextField account;
    @FXML
    private TextField password;
    @FXML
    private HBox accountHbox;
    @FXML
    private HBox passwordHbox;
    @FXML
    private Label loginDefeatedLabel;
    public static ClientConnection CLIENTCONNECTION=null;
    //login 点击事件
    public void loginButtonAction(ActionEvent event) throws Exception {
        String acc= "";
        String pass="";
        acc=getTextFieldText(account);
        pass=getTextFieldText(password);
        //输入账号密码事件
        if(acc.equals("")||pass.equals(""))
        {
            if(acc.equals("")) {
                account.setStyle("-fx-border-color: #fc001f; -fx-border-radius: 5px; -fx-background-radius: 5px;");
            }
            if(pass.equals("")) {
                password.setStyle("-fx-border-color: #fc001f; -fx-border-radius: 5px; -fx-background-radius: 5px;");
            }
        }
        else
        {
            Message loginMessage =new Message("01",acc+"&"+pass+"&",acc,"Server");
                CLIENTCONNECTION = new ClientConnection(acc);
                CLIENTCONNECTION.run();
                SEND.send(loginMessage);
        }
    }
    public void loginSucceed(Message message){
        MessageUser messageUser = (MessageUser)message;
        String[] strings = messageUser.getMessageTextBody().split("&");
        User user = new User(strings[1],strings[2],strings[3],strings[4],strings[5],messageUser.getHeadPortrait());
        USER_DADA = user;
        ImageConversion.byteToImage(user.getHeadPortrait(),"C:\\Users\\12508\\IdeaProjects\\ChatProject\\src\\main\\client\\java\\headimage","head.jpg");
        USER_DADA.setHeadUrl("file:///C:/Users/12508/IdeaProjects/ChatProject/src/main/client/java/headimage/head.jpg");
        System.out.println(messageUser.getHeadPortrait());
        System.out.println("登陆成功");

        Message friendMessage =new Message("03",ControllerLogin.USER_DADA.getAccount()+"&",ControllerLogin.USER_DADA.getAccount(),"Server");
        try {
            SEND.send(friendMessage);
        } catch (IOException e) {
            System.out.println("获取朋友信息错误");
            e.printStackTrace();
        }
    }
    public void loginDefeat(){
        loginDefeatedLabel.setVisible(true);
        System.out.println("登陆失败");
        CLIENTCONNECTION=null;
        RECEIVE.release();
        SEND.release();
    }

    public void registerButtonAction() throws Exception {
        RegisterStage registerStage=new RegisterStage();
        Stage stage =new Stage();
        REGISTERSTAGE =stage;
        registerStage.start(stage);
    }


    //获取textfiled  Account中输入的文字
    public String getTextFieldText(TextField textField)
    {
        loginDefeatedLabel.setVisible(false);

        System.out.println("得到"+ textField.getText());
        return textField.getText();
    }

    public void initialize(URL location, ResourceBundle resources) {
        CONTROLLERLOGIN=this;
    }
    public void recoverTextField(){
        account.setStyle("-fx-border-color: #BEBFC1; -fx-border-radius: 5px; -fx-background-radius: 5px;");
        password.setStyle("-fx-border-color: #BEBFC1; -fx-border-radius: 5px; -fx-background-radius: 5px;");
    }

}


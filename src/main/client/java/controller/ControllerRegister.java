package client.java.controller;

import client.java.pojo.ClientConnection;
import client.java.stage.RegisterSucceedStage;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import message.Message;
import server.java.com.linglett.utils.RegexMatches;

import java.io.*;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.ResourceBundle;

import static client.java.pojo.ClientConnection.RECEIVE;
import static client.java.pojo.ClientConnection.SEND;
import static client.java.stage.RegisterStage.REGISTERSTAGE;

public class ControllerRegister implements Initializable {

    private ResultSet rs=null;
    //昵称输入框
    @FXML
    private TextField registerNickName;
    //密码输入框
    @FXML
    private TextField registerPassword;
    //邮箱输入框
    @FXML
    private TextField registerEmail;
    //验证码输入框
    @FXML
    private TextField registerVerification;
    public static ClientConnection CLIENTCONNECTION=null;
    public static ControllerRegister CONTROLLERREGISTER =null;
    private String mail;
    private String code;
    private String nickName ="";
    private String passWord ="";
    //静态变量ACCIOUNT传给注册界面
    private int new_code = 1;//new Random().nextInt(9000) + 1000;
    public static String ACCOUNT;
    public void initialize(URL location, ResourceBundle resources) {
        CONTROLLERREGISTER = this;
    }

    public void registerSucceed(String account) throws Exception {
        CLIENTCONNECTION=null;
        System.out.println("注册成功");
        RECEIVE.release();
        SEND.release();
        ACCOUNT = account;
        Platform.runLater(new Runnable() {
            public void run() {
                RegisterSucceedStage registerSucceedStage = new RegisterSucceedStage();
                try {
                    registerSucceedStage.start(new Stage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
       REGISTERSTAGE.close();
    }

    public void registerButtonAction() throws SQLException, IOException {
        boolean isRegister=isRegistering();
        if(isRegister)
        {
            //todo 如果符合标准
            this.nickName=textfiledregisternickname();
            this.passWord=textfiledregisterpassword();
            //根据时间戳创建账号
            this.code=textfiledregisterverification();
            this.mail=textfiledregisteremail();
            Message registerMessage =new Message("02",nickName+"&"+passWord+"&"+mail+"&","Register","Server");
            CLIENTCONNECTION = new ClientConnection("Register");
            CLIENTCONNECTION.run();
            SEND.send(registerMessage);
        }

    }
    public void change_nickname(String C1,String account) throws SQLException {

    }
    public void change_birthday(String C1,String account) throws SQLException {

    }
    public void change_sex(String C1,String account) throws SQLException {

    }
    public void change_head(File C1, String account) throws SQLException {

    }
    public void getCode() throws Exception {
        String mail_regex = "[1-9][0-9]{5,9}@qq.com";//QQ邮箱正则表达式
        mail =textfiledregisteremail();
        //匹配合法，发送验证码
        if (new RegexMatches(mail_regex, mail).match())
        {
            //Todo
             //new SendMail(Mail, "Verification Code",
                //   "Your registration verification code is " + new_code ).sendMessage();
        }
        else//匹配不合法，告知用户
        {
            registerEmail.setStyle("-fx-border-color: #BEBFC1; -fx-border-radius: 5px; -fx-background-radius: 5px;");
        }
    }
    //获取昵称信息
    public String textfiledregisternickname() {
        registerNickName.setStyle("-fx-border-color: #BEBFC1; -fx-border-radius: 5px; -fx-background-radius: 5px;");
        String ss= registerNickName.getText();
        return ss;

    }
    //获取密码信息
    public String textfiledregisterpassword() {
        registerPassword.setStyle("-fx-border-color: #BEBFC1; -fx-border-radius: 5px; -fx-background-radius: 5px;");
        return registerPassword.getText();
    }
    //获取电子邮件信息
    public String textfiledregisteremail() {
        registerEmail.setStyle("-fx-border-color: #BEBFC1; -fx-border-radius: 5px; -fx-background-radius: 5px;");
        return registerEmail.getText();
    }
    //获取验证码信息
    public String textfiledregisterverification() {
        registerVerification.setStyle("-fx-border-color: #BEBFC1; -fx-border-radius: 5px; -fx-background-radius: 5px;");
        return registerVerification.getText();
    }


    /**
     * 验证是否让其通过验证
     * TODO email验证
     * TODO 验证码验证
     *
     * @return
     */
    public boolean isRegistering()
    {
        //判断是否输入符合标准，并且返回boolean值
        boolean isregister=true;
        String Password="";
        Password=textfiledregisterpassword();
        String Nickname="";
        Nickname=textfiledregisternickname();
        String Mail="";
        Mail=textfiledregisteremail();
        String Code="";
        Code =textfiledregisterverification();
        if(Nickname.equals("")||Password.equals("")||Mail.equals("")||Code.equals(""))
        {
            if(Nickname.equals(""))
            {
                registerNickName.setStyle("-fx-border-color: #fc001f; -fx-border-radius: 5px; -fx-background-radius: 5px;");
            }
            if(Password.equals(""))
            {
                registerPassword.setStyle("-fx-border-color: #fc001f; -fx-border-radius: 5px; -fx-background-radius: 5px;");
            }
            if(Mail.equals(""))
            {
                registerEmail.setStyle("-fx-border-color: #fc001f; -fx-border-radius: 5px; -fx-background-radius: 5px;");
            }
            if(Code.equals(""))
            {
                registerVerification.setStyle("-fx-border-color: #fc001f; -fx-border-radius: 5px; " +
                        "-fx-background-radius: 5px;");
            }
            isregister=false;
        }
        if(Code!=null)
        {
            if(new_code!=Integer.parseInt(Code))
            {
                isregister=false;
                registerVerification.setStyle("-fx-border-color: #fc001f; -fx-border-radius: 5px; " +
                        "-fx-background-radius: 5px;");
            }

        }
        else isregister=false;
        return isregister;
    }
}

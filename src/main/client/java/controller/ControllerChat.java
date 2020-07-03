package client.java.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.*;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class ControllerChat  {
    /*public static ControllerChat CHATWINDOW;
    public static int page = 0;//0为blank,1为userdata,2为修改userdata界面，3为聊天界面 4.好友添加页面 5.为创建群聊页面 6.群聊邀请页面
    @FXML
    private TextArea msg;
    @FXML
    private ListView msgList;
    @FXML
    private ListView friendList;
    @FXML
    private ImageView head_chat;
    @FXML
    private VBox page_blank;
    @FXML
    private VBox page_userdata;
    @FXML
    private ImageView userdata_head;
    @FXML
    private Label userdata_nickname;
    @FXML
    private Label userdata_account;
    @FXML
    private VBox page_userdataedition;
    @FXML
    private TextField textfield_userdataeditornickname;
    @FXML
    private TextField textfield_userdataeditorsex;
    @FXML
    private TextField textfield_userdataeditorbirthday;
    @FXML
    private VBox page_chat;
    @FXML
    private VBox page_addfriend;
    @FXML
    private Button button_addfriend;
    @FXML
    private TextField textfield_addfriend;
    private friend friend_pitch = null;
    private ObservableList<message> obslist = FXCollections.observableArrayList();//消息记录
    private ObservableList<friend> friendobslist = FXCollections.observableArrayList();//好友列表
    public void initialize(URL location, ResourceBundle resources) {
        CHATWINDOW = this;
        head_chat.setImage(new Image(Controller_LoginHandle.USER_DADA.getheadimage()));
        System.out.println("asdasdasdasdasdsdasdasdasdasdas"+Controller_LoginHandle.USER_DADA.getheadimage());
        try {
            friendobslist = new User_getfriendlist().getfriendlist(Controller_LoginHandle.USER_DADA.getaccount(), 1);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //完善消息列表
        msgList.setCellFactory(new Callback<ListView, ListCell>() {
            @Override
            public ListCell<message> call(ListView param) {
                ListCell<message> listcell = new ListCell<message>() {
                    protected void updateItem(message item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty == false) {
                            HBox hbox = new HBox(20);//水平布局
                            VBox vbox = new VBox(1);//垂直布局
                            //显示消息时间和内容

                            ImageView image = new ImageView();//图片
                            ImageView iv = new ImageView(new Image("file:///C:/Users/12508/IdeaProjects/" +
                                    "GroupChat/src/FriendImage/" + item.man_send.getaccount() +
                                    ".jpg", 40, 40, false, false));

                            Circle circle1 = new Circle();
                            circle1.setRadius(20);
                            circle1.setCenterX(20);
                            circle1.setCenterY(20);
                            iv.setClip(circle1);

                            if (item.msg_format == 1 || item.msg_format == 3) {
                                Label label = new Label(item.man_send.user_username + "  " + item.time + " \n" + item.wordMessage);
                                label.setFont(new Font(16));
                                label.setWrapText(true);
                                vbox.getChildren().addAll(label, image);
                            } else if (item.msg_format == 2 || item.msg_format == 23) {
                                Label label = new Label(item.man_send.user_username + "  " + item.time + " \n");
                                Image image1 = new Image(item.wordMessage);
                                ImageView image_msg = new ImageView();
                                image_msg.setFitWidth(200);
                                image_msg.setFitHeight(200);
                                image_msg.setImage(image1);
                                vbox.getChildren().addAll(label, image_msg, image);
                            }
                            //
                            else if (item.msg_format == 4 || item.msg_format == 24) {
                                Label label = new Label(item.man_send.user_username + "  " + item.time + " \n" + item.file_image.getName());
                                label.setFont(new Font(20));
                                label.setUnderline(true);
                                //字体颜色改变
                                label.setWrapText(true);
                                vbox.getChildren().addAll(label, image);//想一下怎么在fx上点击listview里的listcell直接打开文件
                            }
                            if (item.man_send.getaccount().equals(Controller_LoginHandle.USER_DADA.getaccount())) {
                                hbox.getChildren().addAll(vbox, iv);
                                hbox.setAlignment(Pos.CENTER_RIGHT);
                            } else {
                                hbox.getChildren().addAll(iv, vbox);
                                hbox.setAlignment(Pos.CENTER_LEFT);
                            }
                            this.setGraphic(hbox);
                        } else {
                            setText(null);
                            setGraphic(null);
                        }
                    }
                };
                listcell.setStyle("-fx-background-color: #f5f0f0; -fx-border-radius: 5px; -fx-background-radius: 5px;");
                return listcell;
            }

        });
        msgList.setItems(obslist);
        //完善好友列表
        friendList.setCellFactory(new Callback<ListView, ListCell>() {
            @Override
            public ListCell<friend> call(ListView param) {
                ListCell<friend> listcell = new ListCell<friend>() {
                    protected void updateItem(friend item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty == false) {
                            HBox hbox = new HBox(50);
                            Label bu1 = new Label();
                            bu1.setText(item.friend_note);
                            Image image = new Image("file:///C:/Users/12508/IdeaProjects/GroupChat/src/FriendImage/" + item.friend_account + ".jpg");
                            ImageView iv = new ImageView(image);
                            iv.setFitWidth(50);
                            iv.setFitHeight(50);
                            hbox.getChildren().addAll(iv, bu1);
                            setGraphic(hbox);
                        } else if (empty) {
                            setText(null);
                            setGraphic(null);
                        }
                    }
                };
                return listcell;
            }
        });
        friendList.setItems(friendobslist);
    }

    public void Page_buildgroup_flush() {
        textfield_buildgroup.clear();
        button_groupbuild.setText("创建");
    }

    @FXML
    private TextField textfield_buildgroup;
    @FXML
    private Button button_groupbuild;
    @FXML
    private VBox page_buildgroup;

    public void button_groupbuild() throws SQLException {
        if (button_groupbuild.getText().equals("创建")) {
            if (!(textfield_buildgroup.getText().equals(""))) {
                RedBorder(textfield_buildgroup);
                PreparedStatement NewUser = MySQLDrive.GetPreparedStatement("insert into user_list values(?,?,?,?,?,?)");
                //根据时间戳创建账号
                String NewAccount;
                NewAccount = (new GenerateAccount()).CreateAccount(1);
                //向MySQL增加账号
                NewUser.setString(1, NewAccount);
                NewUser.setString(3, textfield_buildgroup.getText());
                NewUser.setString(2, Controller_LoginHandle.USER_DADA.getaccount());
                NewUser.setString(4, "群");
                NewUser.setString(5, null);
                InputStream input = null;
                try {
                    input = new FileInputStream(new Building_Head().DefaultHead(2));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                NewUser.setBinaryStream(6, input, (int) new Building_Head().DefaultHead(2).length());
                NewUser.executeUpdate();
                friend group = null;
                try {
                    group = Find.find(NewAccount, 1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (group != null) {
                    button_groupbuild.setText("已创建群聊");
                    Add.AddFriend(group);

                } else System.out.println("Add.AdFriend错误");
            } else {
                WhiteBorder(textfield_buildgroup);
            }
        }
    }

    *
     * 界面群聊消息按钮触动

    public void OpenBuildGroup() {

        OpenOrOffPage_text(page, 5);
        page = 5;
    }

    *
     * 界面消息按钮触动
     * 关闭当前页面打开消息默认页面

    public void button_massage_action() throws IOException, SQLException {

        OpenOrOffPage_text(page, 0);
        page = 0;
        friendobslist.clear();
        friendobslist = new User_getfriendlist().getfriendlist(Controller_LoginHandle.USER_DADA.getaccount(), 1);
        friendList.setItems(friendobslist);
    }

    *
     * 聊天界面_文字发送
     *
     * @throws IOException

    public void msgsend() throws IOException {
        String time = System_GetTime.getStringDate();
        message msg_send;
        System.out.println(friend_pitch.friend_account.length());
        msg_send = new message(1, Controller_LoginHandle.USER_DADA,
                friend_pitch, msg.getText(), time);
        obslist.add(msg_send);
        if (friend_pitch.friend_account.length() == 6) {
            msg_send = new message(3, Controller_LoginHandle.USER_DADA,
                    friend_pitch, msg.getText(), time);
        }
        new Controller_LoginHandle().client_login.getsend().send(msg_send);
        msg.clear();
    }

    *
     * 接收消息
     *
     * @param massage

    public void msreceive(message massage) throws IOException, SQLException {
        String C1 = massage.man_send.getaccount();
        if (massage.msg_format == 1) {
            if (friend_pitch != null && friend_pitch.friend_account.equals(massage.man_send.getaccount())) {
            }
        } else if (massage.msg_format == 2) {
            massage.image = new BytetoImage().Byte_Change(massage.file_image.getContent(), massage.file_image.getName());//获取图片路径
        } else if (massage.msg_format == 3) {
            massage.man_send = massage.man_group_send;
            massage.man_group_send = null;
        } else if (massage.msg_format == 23 || massage.msg_format == 24) {
            massage.image = new BytetoImage().Byte_Change(massage.file_image.getContent(), massage.file_image.getName());//获取图片,获取文件
            massage.man_send = massage.man_group_send;
            massage.man_group_send = null;
        }
        if (friend_pitch.friend_account.equals(C1) && friend_pitch != null) {
            obslist.add(massage);
        }
    }

    *
     *

    public void button_group_action() throws IOException, SQLException {
        friendobslist.clear();
        friendobslist = new User_getfriendlist().getfriendlist(Controller_LoginHandle.USER_DADA.getaccount(), 2);
        friendList.setItems(friendobslist);
    }

    *
     * 判断当前页面是群还是好友

    @FXML
    private ImageView upper_right;

    public void judge_upperright() {
        if (friend_pitch.friend_account.length() != 6) {
            Image C1 = new Image("file:///C:/Users/12508/IdeaProjects/GroupChat/src/image/联系人.png");
            upper_right.setImage(C1);
        } else {
            Image C1 = new Image("file:///C:/Users/12508/IdeaProjects/GroupChat/src/image/群聊.png");
            upper_right.setImage(C1);
        }
    }



    @FXML
    private Button button_userdata_editor;
    @FXML
    private ImageView group_head;
    @FXML
    private Label chat_setting_name;
    @FXML
    private Label chat_setting_account;
    @FXML
    private Button page_groupsetting_myname;
    @FXML
    private ImageView button_editor_image;
    @FXML
    private Button button_userdata_password;
    public void Friend_Data() {
        if (friend_pitch.friend_account.length() != 6) {
            Image C1 = new Image(friend_pitch.friend_head);
            userdata_head.setImage(C1);
            userdata_nickname.setText(friend_pitch.friend_friendname);
            userdata_account.setText(friend_pitch.friend_account);
            page_chat.setVisible(false);
            button_userdata_editor.setText("删除好友");
            button_editor_image.setImage(new Image("file:///C:/Users/12508/IdeaProjects/GroupChat/src/image/删除好友.png"));
            page_userdata.setVisible(true);
            change_password.setVisible(false);
            button_userdata_password.setVisible(false);
            page = 1;
        } else {
            Image C1 = new Image(friend_pitch.friend_head);
            group_head.setImage(C1);
            Circle circle1 = new Circle();
            circle1.setRadius(40);
            circle1.setCenterX(40);
            circle1.setCenterY(40);
            group_head.setClip(circle1);
            chat_setting_name.setText(friend_pitch.friend_friendname);
            chat_setting_account.setText(friend_pitch.friend_account);
            page_groupsetting_myname.setText(Controller_LoginHandle.USER_DADA.getnickname());
            OpenOrOffPage_text(page, 6);
            button_userdata_password.setVisible(false);
            page = 6;
        }

    }

    *
     * 群聊创建

    public void page_groupsetting_back() {
        OpenOrOffPage_text(page, 3);
        page = 3;
    }

    *
     * 群聊拉人

    @FXML
    private VBox page_groupsetting;
    @FXML
    private AnchorPane Pane_selectfriend;
    @FXML
    private AnchorPane Pane_selectfriend1;
    @FXML
    private ListView selectfriend_list;

    @FXML
    private ListView selectfriend_list1;

    *
     *

    public void getFriengList() throws IOException, SQLException {
        ObservableList<friend> C1 = FXCollections.observableArrayList();
        C1 = new User_getfriendlist().getfriendlist(Controller_LoginHandle.USER_DADA.getaccount(), 1);
    }


    *
     * 点击邀请好友
     *
     * @throws IOException
     * @throws SQLException

    public void page_groupsetting_add() throws IOException, SQLException {
        Pane_selectfriend.setVisible(true);
        ObservableList<friend> C1 = FXCollections.observableArrayList();
        ObservableList<friend> C2 = FXCollections.observableArrayList();
        ObservableList<friend> C3 = FXCollections.observableArrayList();
        C2 = new User_getfriendlist().getfriendlist(friend_pitch.friend_account, 1);
        C3 = new User_getfriendlist().getfriendlist(Controller_LoginHandle.USER_DADA.getaccount(), 1);
        for (int i = 0; i < C3.size(); i++) {
            int flag = 0;
            for (int j = 0; j < C2.size(); j++) {
                if (C2.get(j).friend_account.equals(C3.get(i).friend_account))
                    flag = 1;
            }
            if (flag == 0 && !(C3.get(i).friend_account.equals(Controller_LoginHandle.USER_DADA.getaccount())))
                C1.add(C3.get(i));
        }

        selectfriend_list.setCellFactory(new Callback<ListView, ListCell>() {
            @Override
            public ListCell<friend> call(ListView param) {
                ListCell<friend> listcell = new ListCell<friend>() {
                    protected void updateItem(friend item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty == false) {
                            HBox hbox = new HBox(50);
                            Label bu1 = new Label();
                            bu1.setText(item.friend_note);
                            Image image = new Image("file:///C:/Users/12508/IdeaProjects/GroupChat/src/FriendImage/" + item.friend_account + ".jpg");
                            ImageView iv = new ImageView(image);
                            iv.setFitWidth(50);
                            iv.setFitHeight(50);
                            Circle circle1 = new Circle();
                            circle1.setRadius(20);
                            circle1.setCenterX(20);
                            circle1.setCenterY(20);
                            iv.setClip(circle1);
                            hbox.getChildren().addAll(iv, bu1);
                            setGraphic(hbox);
                        } else if (empty) {
                            setText(null);
                            setGraphic(null);
                        }
                    }
                };
                listcell.setStyle("-fx-background-color: #f5f0f0; -fx-border-radius: 5px; -fx-background-radius: 5px;");
                return listcell;
            }
        });
        selectfriend_list.setItems(C1);
    }

    public void page_groupsetting_look() throws IOException, SQLException {
        Pane_selectfriend1.setVisible(true);
        ObservableList<friend> C1 = FXCollections.observableArrayList();
        C1 = new User_getfriendlist().getfriendlist(friend_pitch.friend_account, 1);
        selectfriend_list1.setCellFactory(new Callback<ListView, ListCell>() {
            @Override
            public ListCell<friend> call(ListView param) {
                ListCell<friend> listcell = new ListCell<friend>() {
                    protected void updateItem(friend item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty == false) {
                            HBox hbox = new HBox(50);
                            Label bu1 = new Label();
                            bu1.setText(item.friend_note);
                            Image image = new Image("file:///C:/Users/12508/IdeaProjects/GroupChat/src/FriendImage/" + item.friend_account + ".jpg");
                            ImageView iv = new ImageView(image);
                            iv.setFitWidth(50);
                            iv.setFitHeight(50);
                            Circle circle1 = new Circle();
                            circle1.setRadius(20);
                            circle1.setCenterX(20);
                            circle1.setCenterY(20);
                            iv.setClip(circle1);
                            hbox.getChildren().addAll(iv, bu1);
                            setGraphic(hbox);
                        } else if (empty) {
                            setText(null);
                            setGraphic(null);
                        }
                    }
                };
                listcell.setStyle("-fx-background-color: #f5f0f0; -fx-border-radius: 5px; -fx-background-radius: 5px;");
                return listcell;
            }
        });

        selectfriend_list1.setItems(C1);
    }


    @FXML
    public void SelectFriendListClick(MouseEvent event) throws IOException, SQLException {
        if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 1) {
            friend C1 = null;
            ObservableList<friend> C2 = FXCollections.observableArrayList();
            C2 = new User_getfriendlist().getfriendlist(Controller_LoginHandle.USER_DADA.getaccount(), 1);
            C1 = GetFriend(selectfriend_list, C2);
            Add.AddGroup(C1, friend_pitch);
            Pane_selectfriend.setVisible(false);
        }

    }

    public void selectfriend_back() {
        Pane_selectfriend.setVisible(false);
    }

    public void selectfriend_back1() {
        Pane_selectfriend1.setVisible(false);
    }

    *
     * 打开表情页面

    @FXML
    private GridPane Emoji;
    private int emoji = 0;

    public void Chat_Emoji() {
        if (emoji == 0) {
            Emoji.setVisible(true);
            emoji = 1;
        } else {
            Emoji.setVisible(false);
            emoji = 0;
        }
    }

    *
     * 以下为点击表情事件

    public void Emoji_1() {
        Emoji.setVisible(false);
        emoji = 0;
        msg.setText(msg.getText() + "\uD83D\uDE02");
    }

    public void Emoji_2() {
        Emoji.setVisible(false);
        emoji = 0;
        msg.setText(msg.getText() + "☺");
    }

    public void Emoji_3() {
        Emoji.setVisible(false);
        emoji = 0;
        msg.setText(msg.getText() + "\uD83C\uDF48");
    }

    public void Emoji_4() {
        Emoji.setVisible(false);
        emoji = 0;
        msg.setText(msg.getText() + "\uD83D\uDCA9");
    }

    public void Emoji_5() {
        Emoji.setVisible(false);
        emoji = 0;
        msg.setText(msg.getText() + "\uD83D\uDE49");
    }

    public void Emoji_6() {
        Emoji.setVisible(false);
        emoji = 0;
        msg.setText(msg.getText() + "\uD83D\uDCA3");
    }

    public void Emoji_7() {
        Emoji.setVisible(false);
        emoji = 0;
        msg.setText(msg.getText() + "\uD83D\uDE0D");
    }

    public void Emoji_8() {
        Emoji.setVisible(false);
        emoji = 0;
        msg.setText(msg.getText() + "\uD83D\uDE31");
    }

    public void Emoji_9() {
        Emoji.setVisible(false);
        emoji = 0;
        msg.setText(msg.getText() + "\uD83D\uDE08");
    }

    public void Emoji_10() {
        Emoji.setVisible(false);
        emoji = 0;
        msg.setText(msg.getText() + "\uD83D\uDE4F");
    }

    public void Emoji_11() {
        Emoji.setVisible(false);
        emoji = 0;
        msg.setText(msg.getText() + "\uD83D\uDC4E");
    }

    public void Emoji_12() {
        Emoji.setVisible(false);
        emoji = 0;
        msg.setText(msg.getText() + "\uD83D\uDC97");
    }

    *
     * 图片选择窗口打开

    public void PhotoSelect() {
        //选择文件
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("选择需要的打开的图片");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Photo Files"
                , "*.jpg", "*.png", "*.bmp", "*.jpeg"));
        fileChooser.setInitialDirectory(new File("."));

        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            //转换文件
            String time = System_GetTime.getStringDate();
            try {
                FileInputStream fis = new FileInputStream(file);
                byte[] buf = new byte[fis.available()];
                fis.read(buf);//图片转为二进制文件存储在字节数组中
                fis.close();
                message image_msg = new message(2, Controller_LoginHandle.USER_DADA,
                        friend_pitch, file.getAbsolutePath(), time, file.getName(), null);
                obslist.add(image_msg);
                if (friend_pitch.friend_account.length() == 6) {
                    image_msg.msg_format = 23;
                }
                image_msg.file_image = new message_Image(file.getName(), buf);
                new Controller_LoginHandle().client_login.getsend().send(image_msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    *
     * 文件窗口打开

    public void FileSelect() {
        //选择文件
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("选择需要的打开的文件");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("所有文件"
                , "*"));
        fileChooser.setInitialDirectory(new File("."));

        File file = fileChooser.showOpenDialog(new Stage());
        //转换文件
        if (file != null) {
            String time = System_GetTime.getStringDate();
            try {
                FileInputStream fis = new FileInputStream(file);
                byte[] buf = new byte[fis.available()];
                fis.read(buf);//文件转为二进制文件存储在字节数组中
                fis.close();
                message file_msg = new message(4, Controller_LoginHandle.USER_DADA,
                        friend_pitch, file.getAbsolutePath(), time, file.getName(), null);
                obslist.add(file_msg);
                //Todo
                if (friend_pitch.friend_account.length() == 6) {
                    file_msg.msg_format = 24;
                }
                file_msg.file_image = new message_Image(file.getName(), buf);
                new Controller_LoginHandle().client_login.getsend().send(file_msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    *
     * 常用语发送

    public void say1() {
        msg.setText("大家好");
    }

    public void say2() {
        msg.setText("什么？");
    }

    public void say3() {
        msg.setText("？？？？");
    }

    public void say4() {
        msg.setText("......");
    }

    *
     * 页面转换

    public void OpenOrOffPage_text(int a, int b) {
        if (a == 0) {
            page_blank.setVisible(false);
        } else if (a == 1) {
            page_userdata_flush();
            page_userdata.setVisible(false);
        } else if (a == 2) {
            Page_userdataedition_flush();
            page_userdataedition.setVisible(false);
        } else if (a == 3) {
            page_chat.setVisible(false);
        } else if (a == 4) {
            Page_addfriend_flush();
            page_addfriend.setVisible(false);
        } else if (a == 5) {
            Page_buildgroup_flush();
            page_buildgroup.setVisible(false);
        } else if (a == 6) {
            page_groupsetting.setVisible(false);
        }
        if (b == 0) {
            page_blank.setVisible(true);
        } else if (b == 1) {
            page_userdata_flush();
            page_userdata.setVisible(true);
        } else if (b == 2) {
            page_userdata_flush();
            page_userdataedition.setVisible(true);
        } else if (b == 3) {
            judge_upperright();
            page_chat.setVisible(true);
        } else if (b == 4) {
            Page_addfriend_flush();
            page_addfriend.setVisible(true);
        } else if (b == 5) {
            Page_buildgroup_flush();
            page_buildgroup.setVisible(true);
        } else if (b == 6) {
            page_groupsetting.setVisible(true);
        }
    }


    public void RedBorder(TextField C1)//红框转白框
    {
        C1.setStyle("-fx-border-color: #BEBFC1; -fx-border-radius: 5px; -fx-background-radius: 5px;");
    }

    public void WhiteBorder(TextField C1)//白框转红框
    {
        C1.setStyle("-fx-border-color: #fc001f; -fx-border-radius: 5px; -fx-background-radius: 5px;");
    }

    *
     * 点击button_userdata 并且加载资料信息

    public void page_userdata_flush() {
        Image C1 = new Image(Controller_LoginHandle.USER_DADA.getheadimage());
        userdata_head.setImage(C1);
        userdata_nickname.setText(Controller_LoginHandle.USER_DADA.getnickname());
        userdata_account.setText(Controller_LoginHandle.USER_DADA.getaccount());
        button_userdata_editor.setText("编辑资料");
        button_userdata_password.setVisible(true);
        button_editor_image.setImage(new Image("file:///C:/Users/12508/IdeaProjects/GroupChat/src/image/修改.png"));

    }
    *
     * 清空输入的消息

    public void Page_userdataedition_flush() {
        textfield_userdataeditornickname.clear();
        textfield_userdataeditorsex.clear();
        textfield_userdataeditorbirthday.clear();
        button_userdataeditor.setText("保存");

    }

    //点击用户头像事件
    public void button_userdata_action() {
        OpenOrOffPage_text(page, 1);
        page = 1;
    }

    //点击资料页面返回事件
    public void button_userdata_back_action() {
        OpenOrOffPage_text(1, 0);
        page = 0;
    }

    //点击修改资料页面
    public void button_userdata_editor_action() {
        if (button_userdata_editor.getText().equals("编辑资料")) {
            Page_userdataedition_flush();
            OpenOrOffPage_text(1, 2);
            page = 2;
        } else {
            try {
                DeleteFriend(friend_pitch.friend_account);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void DeleteFriend(String FriendAccount) throws SQLException {
        PreparedStatement NewUser = MySQLDrive.GetPreparedStatement("delete from friend_list where owner = ? and friend=?");
        NewUser.setString(2, FriendAccount);
        NewUser.setString(1, Controller_LoginHandle.USER_DADA.getaccount());
        NewUser.executeUpdate();
        PreparedStatement NewUser1 = MySQLDrive.GetPreparedStatement("delete from friend_list where owner = ? and friend=?");
        NewUser1.setString(1, FriendAccount);
        NewUser1.setString(2, Controller_LoginHandle.USER_DADA.getaccount());
        NewUser1.executeUpdate();
        OpenOrOffPage_text(page,0);

    }

    //点击修改资料返回页面
    public void button_userdataeditor_back_action() {
        page_userdata_flush();
        OpenOrOffPage_text(2, 1);
        page = 1;
    }

    @FXML
    private Button button_userdataeditor;

    //点击修改资料保存页面
    public void button_userdataeditor_save() {
        if (iseditoring()) {
            String nickname = "";
            String sex = "";
            String birthday = "";
            nickname = textfilededitornickname();
            sex = textfiledregistersex();
            birthday = textfiledregisterbirthday();
            User_changedata C1 = new User_changedata();
            try {
                C1.changedata(nickname, sex, birthday, Controller_LoginHandle.USER_DADA.getaccount());
            } catch (SQLException e) {
                System.out.println("--button_userdataeditor_save()");
                e.printStackTrace();
            }
            try {
                new Controller_RegisterHandle().change_nickname(nickname, Controller_LoginHandle.USER_DADA.getaccount());
                new Controller_RegisterHandle().change_birthday(birthday, Controller_LoginHandle.USER_DADA.getaccount());
                new Controller_RegisterHandle().change_sex(sex, Controller_LoginHandle.USER_DADA.getaccount());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Controller_LoginHandle.USER_DADA.user_username = nickname;
            Controller_LoginHandle.USER_DADA.user_sex = sex;
            Controller_LoginHandle.USER_DADA.user_birthday = birthday;
            button_userdataeditor.setText("已保存");

        }


    }

    public String textfilededitornickname() {
        RedBorder(textfield_userdataeditornickname);
        String ss = textfield_userdataeditornickname.getText();
        return ss;

    }

    public String textfiledregistersex() {
        RedBorder(textfield_userdataeditorsex);
        String ss = textfield_userdataeditorsex.getText();
        return ss;

    }

    public String textfiledregisterbirthday() {
        RedBorder(textfield_userdataeditorbirthday);
        String ss = textfield_userdataeditorbirthday.getText();
        return ss;

    }

    *
     * 修改资料页面是否输入无误
     *
     * @return

    public boolean iseditoring() {
        //判断是否输入符合标准，并且返回boolean值
        boolean isregister = true;
        String nickname = "";
        String sex = "";
        String birthday = "";
        nickname = textfilededitornickname();
        sex = textfiledregistersex();
        birthday = textfiledregisterbirthday();
        if (nickname.equals("") || sex.equals("") || birthday.equals("")) {
            if (nickname.equals("")) {
                WhiteBorder(textfield_userdataeditornickname);
            }
            if (sex.equals("")) {
                WhiteBorder(textfield_userdataeditorsex);
            }
            if (birthday.equals("")) {
                WhiteBorder(textfield_userdataeditorbirthday);
            }

            isregister = false;
        }
        return isregister;
    }

    *
     * 点击好友事件
     * 返回所点击的好友

    public friend GetFriend(ListView C1, ObservableList<friend> C2) {
        int friend_index = C1.getSelectionModel().getSelectedIndex();
        if (friend_index >= 0) {
            friend friend_flag = C2.get(friend_index);
            return friend_flag;
        }
        return null;
    }

    *
     * 点击消息返回消息事件

    public message Getmsg(ListView C1, ObservableList<message> C2) {
        int friend_index = C1.getSelectionModel().getSelectedIndex();
        if (friend_index >= 0) {
            message getmsg = C2.get(friend_index);
            return getmsg;
        }
        return null;
    }


    *
     * 返回聊天记录

    public ObservableList<message> GetChatData(String friend_account) throws SQLException, IOException {
        //链接数据库
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = MySQLDrive.GetStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            rs = stmt.executeQuery("select *from chat_data");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ObservableList<message> C1 = FXCollections.observableArrayList();
        while (rs.next()) {
            //在数据库里查找消息
            //（1）查找己方发出的消息
            if (rs.getString(2).equals(friend_account) && rs.getString(1).equals(Controller_LoginHandle.USER_DADA.getaccount())) {
                message msg = null;
                //查看消息类型,私聊群聊文字消息

                if (rs.getInt(6) == 1 || rs.getInt(6) == 3) {
                    msg = new message(1, Controller_LoginHandle.USER_DADA, friend_pitch, rs.getString(3), rs.getString(5));

                } else if (rs.getInt(6) == 2 || rs.getInt(6) == 23) {//群聊私聊图片消息
                    //建立图片消息
                    new BuildImageMsg().GetImageMsg(rs, "Chat_image");
                    msg = new message(rs.getInt(6), Controller_LoginHandle.USER_DADA, friend_pitch, "C:/Us" +
                            "ers/12508/IdeaProjects/GroupChat/s" +
                            "rc/Chat_image/" + rs.getString(7),
                            rs.getString(5), rs.getString(7), null);//5:time
                } else if (rs.getInt(6) == 4 || rs.getInt(6) == 24)//群聊私聊文件消息
                {
                    new BuildImageMsg().GetImageMsg(rs, "Chat_File");
                    msg = new message(rs.getInt(6), Controller_LoginHandle.USER_DADA, friend_pitch, "C:/Us" +
                            "ers/12508/IdeaProjects/GroupChat/s" +
                            "rc/Chat_File/" + rs.getString(7),
                            rs.getString(5), rs.getString(7), null);//5:time
                }
                msg.man_send.user_head = Controller_LoginHandle.USER_DADA.getheadimage();
                C1.add(msg);
                //（2）查找对方发出的消息
            } else if (rs.getString(1).equals(friend_account) && rs.getString(2).equals(Controller_LoginHandle.USER_DADA.getaccount())) {

                User_data C_copy = new User_data(friend_account);
                friend D_copy = new friend();
                D_copy.friend_account = Controller_LoginHandle.USER_DADA.getaccount();
                message msg = null;

                if (rs.getInt(6) == 1) {
                    //文字类型
                    msg = new message(1, C_copy, D_copy, rs.getString(3), rs.getString(5));
                } else if (rs.getInt(6) == 2) {
                    //接收放图片类的改造,,私聊图片消息
                    new BuildImageMsg().GetImageMsg(rs, "Chat_image");
                    msg = new message(2, C_copy, D_copy, "C:/Users/12508/IdeaProjects/GroupChat/src/" +
                            "Chat_image/" + rs.getString(7), rs.getString(5), rs.getString(7),
                            null);//5:time
                } else if (rs.getInt(6) == 3) {
                    //群聊文字消息
                    User_data E_copy = new User_data(rs.getString(8));
                    msg = new message(3, E_copy, D_copy, rs.getString(3), rs.getString(5));

                } else if (rs.getInt(6) == 23) {
                    new BuildImageMsg().GetImageMsg(rs, "Chat_image");
                    User_data E_copy = new User_data(rs.getString(8));
                    msg = new message(2, E_copy, D_copy, "C:/Users/12508/IdeaProjects/GroupChat/src/" +
                            "Chat_image/" + rs.getString(7), rs.getString(5), rs.getString(7),
                            null);//5:time
                } else if (rs.getInt(6) == 4) {
                    new BuildImageMsg().GetImageMsg(rs, "Chat_File");
                    msg = new message(4, C_copy, D_copy, "C:/Users/12508/IdeaProjects/GroupChat/src/" +
                            "Chat_File/" + rs.getString(7), rs.getString(5), rs.getString(7),
                            null);//5:time
                } else if (rs.getInt(6) == 24) {
                    new BuildImageMsg().GetImageMsg(rs, "Chat_File");
                    User_data E_copy = new User_data(rs.getString(8));
                    msg = new message(4, E_copy, D_copy, "C:/Users/12508/IdeaProjects/GroupChat/src/" +
                            "Chat_File/" + rs.getString(7), rs.getString(5), rs.getString(7),
                            null);//5:time
                }

                msg.man_send.user_head = "file:///C:/Users/12508/IdeaProjects/GroupChat/src/FriendImage/" + msg.man_send.getaccount() + ".jpg";
                C1.add(msg);
            }

        }
        return C1;
    }

    *
     * 好友列表刷新按钮触动时间

    public void friendlist_flush() {
        friendobslist.clear();
        try {
            friendobslist = new User_getfriendlist().getfriendlist(Controller_LoginHandle.USER_DADA.getaccount(), 1);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        friendList.setItems(friendobslist);
    }

    *
     * 好友列表集中处理事件
     * 点击好友

    @FXML
    public void FriendListClick(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 1) {
            friend C1 = friend_pitch;
            friend_pitch = null;
            friend_pitch = GetFriend(friendList, friendobslist);
            //打开与点击的好友的聊天事件
            if (friend_pitch != null) {
                obslist.clear();
                if (page == 3 && C1.friend_account != friend_pitch.friend_account) {
                    //1.先清空聊天
                    obslist.clear();
                    try {
                        obslist = GetChatData(friend_pitch.friend_account);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        obslist = GetChatData(friend_pitch.friend_account);
                        msgList.setItems(obslist);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    OpenOrOffPage_text(page, 3);
                }
                msgList.setItems(obslist);
                page = 3;
            } else {
                friend_pitch = C1;
                return;
            }


        }
    }

    *
     * 伪点击好友事件
     *
     * @param

    public void FriendListClick(friend addfriend) {
        friend C1 = friend_pitch;
        friend_pitch = null;
        friend_pitch = addfriend;
        //打开与点击的好友的聊天事件
        if (friend_pitch != null) {
            obslist.clear();
            try {
                obslist = GetChatData(friend_pitch.friend_account);
                msgList.setItems(obslist);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            OpenOrOffPage_text(page, 3);
            msgList.setItems(obslist);
            page = 3;
        } else {
            friend_pitch = C1;
            return;
        }
    }

    *
     * 点击添加好友按钮事件

    public void button_addfriend_event() {
        OpenOrOffPage_text(page, 4);
        page = 4;
    }

    *
     * 获取查找好友输入

    public String textfileaddfriend() {
        RedBorder(textfield_addfriend);
        String ss = textfield_addfriend.getText();
        return ss;
    }

    *
     * 好友账号输入是否无误

    public boolean isAdding() {
        //判断是否输入符合标准，并且返回boolean值
        boolean isAdd = true;
        String friendaccount = "";
        friendaccount = textfileaddfriend();
        if (friendaccount.equals("")) {
            WhiteBorder(textfield_addfriend);
            isAdd = false;
        }
        return isAdd;
    }

    public boolean isAdding(friend C1) throws SQLException {
        boolean isAdd = true;
        Statement stmt = MySQLDrive.GetStatement();
        ResultSet rs = stmt.executeQuery("select *from friend_list");
        while (rs.next()) {
            if (Controller_LoginHandle.USER_DADA.getaccount().equals(rs.getString(1))) {
                if (C1.friend_account.equals(rs.getString(2))) {
                    return false;
                }
            }

        }
        return true;
    }

    *
     * 刷新查询页面

    public void Page_addfriend_flush() {
        page_add_hbox_addfriend.setVisible(false);
        button_addfriendpage.setText("添加");
        button_addfriendpage.setVisible(true);
        notfindaddfriend.setVisible(false);
        label_addfriend_error.setVisible(false);
        textfield_addfriend.clear();
        Friend_add = null;
    }

    *
     * 点击查找按钮

    @FXML
    private HBox page_add_hbox_addfriend;
    @FXML
    private ImageView image_addfriend;
    @FXML
    private Label label_addfriend;
    @FXML
    private Label label_addfriend_error;
    @FXML
    private Button button_addfriendpage;
    @FXML
    private Label notfindaddfriend;
    private friend Friend_add = null;

    public void button_addfriend_find() throws IOException, SQLException {
        //查询好友刷新
        page_add_hbox_addfriend.setVisible(false);
        button_addfriendpage.setText("添加");
        notfindaddfriend.setVisible(false);
        label_addfriend_error.setVisible(false);

        if (isAdding()) {
            String friendaccount = textfileaddfriend();
            friend C1 = Find.find(friendaccount, 1);
            if (C1 != null) {
                //有就显示信息
                image_addfriend.setImage(new Image(C1.friend_head));
                label_addfriend.setText(C1.friend_friendname);
                if (isAdding(C1)) {
                    //双方不是好友 可以添加
                    page_add_hbox_addfriend.setVisible(true);
                    Friend_add = C1;
                } else {
                    //否则弹出Label提醒你已经加了这个好友并且更改点击按钮消息事件
                    label_addfriend_error.setVisible(true);
                    button_addfriendpage.setVisible(false);
                    page_add_hbox_addfriend.setVisible(true);
                }

            } else {
                //弹出Label提醒输入错误
                notfindaddfriend.setVisible(true);
            }
        }
    }

    *
     * 点击添加好友事件||群聊

    public void button_addfriend_correct() {
        if (button_addfriendpage.getText().equals("添加")) {
            try {
                if (Friend_add.friend_account.length() != 6)
                    Add.AddFriend(Friend_add);
                else {
                    friend C1 = new UserData_to_Friend().UserchangeFriend(Controller_LoginHandle.USER_DADA);
                    Add.AddGroup(C1, Friend_add);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            friendobslist.add(Friend_add);
            button_addfriendpage.setVisible(false);
            label_addfriend_error.setVisible(true);
        }
    }

    *
     * 好友查询页面点击返回事件

    public void button_addfriend_back_action() {
        OpenOrOffPage_text(4, 0);
        page = 0;
    }

    *
     * 聊天点击消息事件

    public void MsgListviewClick(MouseEvent event) throws IOException {
        if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 1) {
            message msg_pitch = Getmsg(msgList, obslist);//获取获得的息
            //分析所点击的消息种类
            if (msg_pitch != null) {
                //点击的事件是文件类型
                if (msg_pitch.msg_format == 2 || msg_pitch.msg_format == 4 || msg_pitch.msg_format == 23 || msg_pitch.msg_format == 24) {
                    new File_Open().Open(msg_pitch.image);
                }
            }
        }
    }

    public void change_head() throws SQLException, IOException {
        if (friend_pitch == null || friend_pitch.friend_account.equals(Controller_LoginHandle.USER_DADA.getaccount())) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("选择需要的打开的头像");
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Photo Files"
                    , "*.jpg", "*.png", "*.bmp", "*.jpeg"));
            fileChooser.setInitialDirectory(new File("."));
            File file = fileChooser.showOpenDialog(new Stage());
            if (file != null) {
                new Controller_RegisterHandle().change_head(file, Controller_LoginHandle.USER_DADA.getaccount());//修改数据库里的数据
                userdata_flush(file.getAbsolutePath());
            }


        }

    }

    public void userdata_flush(String path) throws IOException//刷新自己的userdata
    {
        Image im = new Image("file:///" + path);
        head_chat.setImage(im);
        userdata_head.setImage(im);
    }
    @FXML
    private AnchorPane change_password;
    @FXML
    private TextField newpassword;
    public void button_userdata_password_action()
    {
        change_password.setVisible(true);
    }
    public void changepassword_back() {
        change_password.setVisible(false);
    }
    public void Confirm_change_password() throws SQLException {
        if(!(newpassword.getText().equals("")))
        {
            PreparedStatement NewUser= MySQLDrive.GetPreparedStatement("update user_list set password= ? where account=?");
            NewUser.setString(2,Controller_LoginHandle.USER_DADA.getaccount());
            NewUser.setString(1,newpassword.getText());
            NewUser.executeUpdate();
            changepassword_back();
            newpassword.setText("");
        }
        else RedBorder(newpassword);
    }
*/
}



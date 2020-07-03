package client.java.controller;

import client.java.pojo.ClientConnection;
import client.java.pojo.Friend;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import message.Message;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import static client.java.pojo.ClientConnection.SEND;

public class ControllerChatCopy implements Initializable {
    @FXML
    private ImageView headChat;
    @FXML
    private TextArea msg;
    @FXML
    private ListView msgList;
    @FXML
    private ListView friendList;
    @FXML
    private VBox page_blank;
    @FXML
    private VBox page_userdata;
    @FXML
    private VBox page_buildgroup;
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
    private VBox vBoxNow;
    public static ObservableList<Friend> FRIENDLIST = FXCollections.observableArrayList();

    public void initialize(URL location, ResourceBundle resources) {
        vBoxNow=page_blank;
        headChat.setImage(new Image(ControllerLogin.USER_DADA.getHeadUrl()));
        //完善好友列表
        friendList.setCellFactory(new Callback<ListView, ListCell>() {
            public ListCell<Friend> call(ListView param) {
                ListCell<Friend> listcell = new ListCell<Friend>() {
                    protected void updateItem(Friend item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty == false) {
                            HBox hbox = new HBox(50);
                            Label bu1 = new Label();
                            bu1.setText(item.friend_note);
                            Image image = new Image("file:///C:/Users/12508/IdeaProjects/ChatProject/src/main/client/java/friendimage/" + item.friend_account + ".jpg");
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
        friendList.setItems(FRIENDLIST);

    }

    public void openPage(VBox a){
        a.setVisible(true);
        vBoxNow.setVisible(false);
        vBoxNow=a;

    }
    public void buttonUserdataAction(){
        openPage(page_userdata);
    }
    public void buttonMassageAction(){
        openPage(page_chat);
    }
    public void buttonAddFriendAction(){
        openPage(page_addfriend);
    }
    public void friendlist_flush() {
    }
    public void buttonGroupAddAction(){
        openPage(page_buildgroup);
    }

}

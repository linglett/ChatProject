package client.java.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ControllerRegisterSucceed {
    @FXML
    private Button successButton;
    public void change()
    {
        successButton.setText(ControllerRegister.ACCOUNT);
    }
}

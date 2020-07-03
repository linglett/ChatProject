package client.java.stage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Statement;

public class LoginStage extends Application {

    public static Stage LOGINSTAGE;
    @Override
    public void start(Stage primaryStage) throws Exception {
        LOGINSTAGE = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("login_sample.fxml"));
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root, 740, 500));
        primaryStage.show();
    }
    public static void main(String... args) {
        launch(args);
    }

}

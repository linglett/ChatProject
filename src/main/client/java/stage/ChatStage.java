package client.java.stage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ChatStage extends Application {

        @Override
        public void start(Stage primaryStage) throws Exception {
            Parent root = FXMLLoader.load(getClass().getResource("ChatStage_sample.fxml"));
            primaryStage.setTitle("Chat");
            primaryStage.setScene(new Scene(root, 1329, 879));
            primaryStage.show();
        }

    }
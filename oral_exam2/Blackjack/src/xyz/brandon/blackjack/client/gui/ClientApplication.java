package xyz.brandon.blackjack.client.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ClientApplication extends Application {

    private Scene connectScene;
    private Scene readyScene;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("ConnectInterface.fxml"));

            Scene connectScene = new Scene(root);

            primaryStage.setTitle("Black Jack");
            primaryStage.setScene(connectScene);
            primaryStage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}

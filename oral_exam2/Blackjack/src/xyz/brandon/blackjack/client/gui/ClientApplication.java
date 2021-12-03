package xyz.brandon.blackjack.client.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Main client side GUI application class
 */
public class ClientApplication extends Application {

    /**
     * Called when object run, launches the application
     * @param args  input args
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Starts the Blackjack application, opens the ConnectInterface.fxml scene.
     * @param primaryStage
     */
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

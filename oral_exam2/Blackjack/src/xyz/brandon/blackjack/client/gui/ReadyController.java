package xyz.brandon.blackjack.client.gui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import xyz.brandon.blackjack.client.Player;
import xyz.brandon.blackjack.client.network.Client;

import java.io.IOException;

public class ReadyController {

    @FXML
    private GridPane ReadyPanel;

    @FXML
    private Label messageLabel;

    @FXML
    private Button readyButton;

    @FXML
    private TextField usernameField;

    @FXML
    void sendReady(ActionEvent event) {
        String formattedUsername = usernameField.getText().replaceAll("\\s", ""); //Can't have spaces in username

        Stage stage = (Stage) messageLabel.getScene().getWindow();
        Client client = (Client)stage.getUserData();
        System.out.println(client.toString());

        if (formattedUsername.length() == 0) {
            displayAlert("Unable to ready up, you must enter a valid username first!");
        } else {
            Player player = new Player(formattedUsername);
            client.sendString("ready", "username:"+formattedUsername, true);
            hideAlert();
            readyButton.setVisible(false);
            displayAlert("You are now ready, waiting for other players...");
            if (client.listenFor("game", "status:start")) {
                switchToTableScene(player, client);
            }
        }
    }

    public void displayAlert(String message) {
        messageLabel.setVisible(true);
        messageLabel.setText(message);
    }

    public void hideAlert() {
        messageLabel.setVisible(false);
    }

    public void switchToTableScene(Player player, Client client) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("TableInterface.fxml"));
            Stage stage = (Stage) messageLabel.getScene().getWindow();
            stage.setUserData(player);

            Scene scene = new Scene(loader.load());
            stage.setScene(scene);

            TableController controller = loader.<TableController>getController();
            controller.setupTable(player, client);
        }catch (IOException io){
            io.printStackTrace();
        }
    }

}
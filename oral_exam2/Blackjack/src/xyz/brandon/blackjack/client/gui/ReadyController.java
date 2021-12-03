package xyz.brandon.blackjack.client.gui;

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

/**
 * Controller for ReadyInterface.fxml
 */
public class ReadyController {

    /**
     * Main ready panel scene body
     */
    @FXML
    private GridPane ReadyPanel;

    /**
     * Message label used for displaying alerts
     */
    @FXML
    private Label messageLabel;

    /**
     * ready button for readying up
     */
    @FXML
    private Button readyButton;

    /**
     * Username field for inputting username
     */
    @FXML
    private TextField usernameField;

    /**
     * Event called when ready button pressed. Verifies value is username field is a valid username,
     * then sends this data to server and notifies it the user is ready. Then waits for server to send
     * game start message and switches to TableInterface.fxml scene.
     * @param event
     */
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

    /**
     * Display alert message at bottom of screen.
     * @param message   message to display
     */
    public void displayAlert(String message) {
        messageLabel.setVisible(true);
        messageLabel.setText(message);
    }

    /**
     * Hides the alert message
     */
    public void hideAlert() {
        messageLabel.setVisible(false);
    }

    /**
     * Method to switch to table scene, called after user has readied up.
     * @param player    Reference to player object. Passed to next scene
     * @param client    Reference to client object. Passed to next scene
     */
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
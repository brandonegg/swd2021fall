package xyz.brandon.blackjack.client.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import xyz.brandon.blackjack.client.network.Client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Controller for ConnectInterface.fxml
 */
public class ConnectController {

    /**
     * Main pane of connect interface
     */
    @FXML
    private GridPane ConnectPane;

    /**
     * Field for entering server address
     */
    @FXML
    private TextField addressField;

    /**
     * Label for displaying alerts
     */
    @FXML
    private Label messageLabel;

    /**
     * Field for entering server port
     */
    @FXML
    private TextField portField;

    /**
     * Called when connect button pressed. Attempts to connect to provided
     * address field and port field. Notifies user with alert as to why
     * connection failed. If connection successful, advance to ready scene.
     * @param event connect button pressed event
     */
    @FXML
    void connectClient(ActionEvent event) {

        InetAddress address = null;
        int port = -1;

        try {
            address = InetAddress.getByName(addressField.getText());
        } catch (UnknownHostException e) {
            e.printStackTrace();
            displayAlert("Address not found!");
            return;
        }

        try {
            port = Integer.valueOf(portField.getText());
        } catch (Exception e) {
            displayAlert("Invalid port submitted");
            return;
        }

        if (port == -1 || address == null || addressField.getText().replaceAll("\\s", "").equals("")) {
            displayAlert("Unable to convert address and port");
        } else {
            Client client = new Client(address,port);
            try {
                client.connect();
            } catch (IOException e) {
                displayAlert("Unable to connect to server!");
            }

            if (client.isConnected()) {
                switchToReadyScene(client);
            }
        }

    }

    /**
     * Method for displaying alert
     * @param message   message to display
     */
    public void displayAlert(String message) {
        messageLabel.setVisible(true);
        messageLabel.setText(message);
    }

    /**
     * Hides alert
     */
    public void hideAlert() {
        messageLabel.setVisible(false);
    }

    /**
     * Switches to ReadyInterface scene. Passes client data along to next scene.
     * @param client    client object to pass to next scene.
     */
    public void switchToReadyScene(Client client) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ReadyInterface.fxml"));
            Stage stage = (Stage) messageLabel.getScene().getWindow();
            stage.setUserData(client);

            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
        }catch (IOException io){
            io.printStackTrace();
        }
    }

}

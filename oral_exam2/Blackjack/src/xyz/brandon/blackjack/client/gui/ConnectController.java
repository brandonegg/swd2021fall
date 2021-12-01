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

public class ConnectController {

    @FXML
    private GridPane ConnectPane;

    @FXML
    private TextField addressField;

    @FXML
    private Label messageLabel;

    @FXML
    private TextField portField;

    @FXML
    void connectClient(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ReadyInterface.fxml"));
            Stage stage = (Stage) messageLabel.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
        }catch (IOException io){
            io.printStackTrace();
        }

        InetAddress address = null;
        int port = -1;

        try {
            address = InetAddress.getByName(addressField.getText());
        } catch (UnknownHostException e) {
            e.printStackTrace();
            displayAlert("Address not found!");
            return;
        }

        //try {
            port = Integer.valueOf(portField.getText());
        //} catch (Exception e) {
        //    displayAlert("Invalid port submitted");
        //    return;
        //}

        if (port == -1 || address == null) {
            displayAlert("Unable to convert address and port");
        } else {
            Client client = new Client(address,port);
            try {
                client.connect();

            } catch (IOException e) {
                displayAlert("Unable to connect to server!");
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

}

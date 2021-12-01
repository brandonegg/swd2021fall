package xyz.brandon.blackjack.client.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

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

        if (formattedUsername.length() == 0) {
            displayAlert("Unable to ready up, you must enter a valid username first!");
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

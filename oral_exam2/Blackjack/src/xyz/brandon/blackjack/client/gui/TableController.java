package xyz.brandon.blackjack.client.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import xyz.brandon.blackjack.Card;
import xyz.brandon.blackjack.client.Hand;
import xyz.brandon.blackjack.client.Player;
import xyz.brandon.blackjack.utils.ArgsParser;

import java.io.IOException;
import java.util.ArrayList;

public class TableController {

    @FXML
    private Label activePlayerLabel;

    @FXML
    private VBox cardPanel;

    @FXML
    private Button hitButton;

    @FXML
    private Label notYourTurnLabel;

    @FXML
    private Label playersScoreLabel;

    @FXML
    private Label yourScoreLabel;

    @FXML
    private Button standButton;

    @FXML
    private Label messageLabel;

    private Hand tableHand;
    private String currentTurn;
    private TableModel tableModel;
    private Player player;

    public TableController() {
        tableHand = new Hand();
        player =null;
    }
    public void setupTable(Player player){
        System.out.println("Setting up table from server turn");
        this.player = player;
        currentTurn = "";
        tableModel = new TableModel(this, player);
        tableModel.waitForNewTurn();
    }



    @FXML
    void playerHits(ActionEvent event) {
        if (player != null) {
            player.getClient().sendString("action", "username:"+player.getUsername()+"_type:hit", false);
            tableModel.recieveCard();
        }
    }

    @FXML
    void playerStands(ActionEvent event) {
    }

    public String getCurrentTurn() {
        return currentTurn;
    }

    public void setActivePlayer(String username, boolean currentPlayer) {
        hideAlert();
        clearDeck();
        if (currentPlayer) {
            hitButton.setVisible(true);
            standButton.setVisible(true);
            notYourTurnLabel.setVisible(false);
            activePlayerLabel.setText("CURRENT PLAYER: YOU!");
            yourScoreLabel.setVisible(false);
        } else {
            activePlayerLabel.setText("CURRENT PLAYER: " + username);
            hideControls();
        }
        currentTurn = username;
    }

    public void addCard(Card card) {
        cardPanel.getChildren().add(new Label(card.toString()));

    }

    public void updateYourScoreLabel(String score) {
        yourScoreLabel.setText("Your score: " +score);
    }

    public void updatePlayerScoreLabel(String score) {
        System.out.println("updating score"+score);
        playersScoreLabel.setText("Player's Total: " + score);
    }

    public void clearDeck() {
        int i =0;
        for (Node node : cardPanel.getChildren()) {
            if (i>0) {
                cardPanel.getChildren().remove(node);
            }
            i++;
        }
    }

    public void displayAlert(String message) {
        messageLabel.setVisible(true);
        messageLabel.setText(message);
    }

    public void hideAlert() {
        messageLabel.setVisible(false);
    }

    public void hideControls() {
        hitButton.setVisible(false);
        standButton.setVisible(false);
        notYourTurnLabel.setVisible(true);
        yourScoreLabel.setVisible(true);
    }

}

package xyz.brandon.blackjack.client.gui;

import javafx.application.Platform;
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
import xyz.brandon.blackjack.client.network.Client;
import xyz.brandon.blackjack.utils.ArgsParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

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
    private Client client;

    public TableController() {
        tableHand = new Hand();
        player =null;
    }
    public void setupTable(Player player, Client client){
        System.out.println("Setting up table from server turn");
        this.player = player;
        currentTurn = "";
        tableModel = new TableModel(this, player, client);
        tableModel.waitForNewTurn();
        this.client = client;
    }



    @FXML
    void playerHits(ActionEvent event) {
        if (player != null) {
            client.sendString("action", "username:"+player.getUsername()+"_type:hit", false);
            tableModel.recieveCard(tableModel.getClientPlayer());
        }
    }

    @FXML
    void playerStands(ActionEvent event) {
        if (player != null) {
            client.sendString("action", "username:"+player.getUsername()+"_type:stand", false);
            updateYourScoreLabel(Integer.toString(player.getHandValue()));
            tableModel.waitForNewTurn();
        }
    }

    public String getCurrentTurn() {
        return currentTurn;
    }

    public void setActivePlayer(String username, boolean currentPlayer) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
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
                    System.out.println("Updating active player to " + username);
                }
                currentTurn = username;
                updatePlayerScoreLabel("none");
            }
        });
    }

    public void addCard(Card card) {
        Platform.runLater(()->cardPanel.getChildren().add(new Label(card.toString())));
    }

    public void updateYourScoreLabel(String score) {
        Platform.runLater(()->yourScoreLabel.setText("Your score: " +score));
    }

    public void updatePlayerScoreLabel(String score) {
        System.out.println("updating score "+score);
        playersScoreLabel.setText("Player's Total: " + score);
    }

    public void clearDeck() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                int i =0;
                ArrayList<Node> nodesToRemove = new ArrayList<Node>();
                for (Node node : cardPanel.getChildren()) {
                    if (i>0) {
                        nodesToRemove.add(node);
                    }
                    i++;
                }

                for (Node node : nodesToRemove) {
                    cardPanel.getChildren().remove(node);
                }
            }
        });
    }

    public void displayAlert(String message) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                messageLabel.setVisible(true);
                messageLabel.setText(message);
            }
        });
    }

    public void hideAlert() {
        messageLabel.setVisible(false);
    }

    public void hideControls() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                hitButton.setVisible(false);
                standButton.setVisible(false);
                notYourTurnLabel.setVisible(true);
                yourScoreLabel.setVisible(true);
            }
        });
    }

    public void recieveServerCard(ArgsParser args, Player player) {
        Platform.runLater(()-> tableModel.recieveCard(args, player));
    }

}

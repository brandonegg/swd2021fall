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

/**
 * Table controller object for controller TableInterface.fxml
 */
public class TableController {

    /**
     * Label object displaying who the active player is
     */
    @FXML
    private Label activePlayerLabel;

    /**
     * VBox storing the panel cards are displayed in
     */
    @FXML
    private VBox cardPanel;

    /**
     * Hit button displayed when it is clients turn.
     */
    @FXML
    private Button hitButton;

    /**
     * Label displayed when it is not clients turn
     */
    @FXML
    private Label notYourTurnLabel;

    /**
     * Label displaying the current players score
     */
    @FXML
    private Label playersScoreLabel;

    /**
     * Label displaying clients score (none if haven't played)
     */
    @FXML
    private Label yourScoreLabel;

    /**
     * Button displayed when it is clients turn, allows them to stand
     */
    @FXML
    private Button standButton;

    /**
     * Label for displaying status messages
     */
    @FXML
    private Label messageLabel;

    /**
     * String representing who is currently playing
     */
    private String currentTurn;
    /**
     * Reference to tablemodel object
     */
    private TableModel tableModel;
    /**
     * Reference to client's player object
     */
    private Player player;
    /**
     * Reference to server Client
     */
    private Client client;
    private Hand tableHand;

    /**
     * Constructs a table controller with empty hand and no player
     */
    public TableController() {
        tableHand = new Hand();
        player =null;
    }

    /**
     * Called after table scene is displayed. Handles TabelModel creation and receives
     * the client and player references.
     * @param player    reference to player object
     * @param client    reference to client object
     */
    public void setupTable(Player player, Client client){
        System.out.println("Setting up table from server turn");
        this.player = player;
        currentTurn = "";
        tableModel = new TableModel(this, player, client);
        tableModel.waitForNewTurn();
        this.client = client;
    }


    /**
     * Called when hit button is pressed. Notifies server, receives card and
     * updates player hand
     * @param event Called when button pressed
     */
    @FXML
    void playerHits(ActionEvent event) {
        if (player != null) {
            client.sendString("action", "username:"+player.getUsername()+"_type:hit", false);
            tableModel.recieveCard(tableModel.getClientPlayer());
        }
    }

    /**
     * Called when stand button pressed. Notifies server and turn ends so next turn message
     * is waited on from server.
     * @param event
     */
    @FXML
    void playerStands(ActionEvent event) {
        if (player != null) {
            client.sendString("action", "username:"+player.getUsername()+"_type:stand", false);
            updateYourScoreLabel(Integer.toString(player.getHandValue()));
            tableModel.waitForNewTurn();
        }
    }

    /**
     * Returns who is current player.
     * @return  current player username
     */
    public String getCurrentTurn() {
        return currentTurn;
    }

    /**
     * Updates the active player display elements. If player playing is client player, buttons are displayed.
     * @param username      username of player
     * @param currentPlayer true if username is client's player
     */
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

    /**
     * Adds card to card VBox panel.
     * @param card  card object to add
     */
    public void addCard(Card card) {
        Platform.runLater(()->cardPanel.getChildren().add(new Label(card.toString())));
    }

    /**
     * Updates score displayed by your score label.
     * @param score Score value
     */
    public void updateYourScoreLabel(String score) {
        Platform.runLater(()->yourScoreLabel.setText("Your score: " +score));
    }

    /**
     * Update player score displayed by player score label
     * @param score Score value
     */
    public void updatePlayerScoreLabel(String score) {
        System.out.println("updating score "+score);
        playersScoreLabel.setText("Player's Total: " + score);
    }

    /**
     * Clears the local card VBox panel deck.
     */
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

    /**
     * Display alert message at bottom of screen
     * @param message   message to display
     */
    public void displayAlert(String message) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                messageLabel.setVisible(true);
                messageLabel.setText(message);
            }
        });
    }

    /**
     * Hides the alert message
     */
    public void hideAlert() {
        messageLabel.setVisible(false);
    }

    /**
     * Hides the client player controls. Called when active player is updated from this client's player.
     */
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

    /**
     * Wait to receive card from server when called from another thread. Specifically
     * when waiting on external players turn.
     * @param args      Args of card received
     * @param player    player object receiving card
     */
    public void recieveServerCard(ArgsParser args, Player player) {
        Platform.runLater(()-> tableModel.recieveCard(args, player));
    }

}

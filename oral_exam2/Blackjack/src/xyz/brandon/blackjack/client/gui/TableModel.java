package xyz.brandon.blackjack.client.gui;

import xyz.brandon.blackjack.Card;
import xyz.brandon.blackjack.Suit;
import xyz.brandon.blackjack.client.Player;
import xyz.brandon.blackjack.client.network.Client;
import xyz.brandon.blackjack.utils.ArgsParser;

/**
 * Backend model for the fxml TableInferface
 */
public class TableModel {

    /**
     * Reference to client's player object
     */
    private Player clientPlayer;
    /**
     * Reference to table controller for TableInterface
     */
    private TableController tableController;
    /**
     * Reference to client object
     */
    private Client client;

    /**
     * Table model constructor. Created by TableController on setup.
     * @param tableController   Reference to table controller
     * @param clientPlayer      Reference to player object
     * @param client            Reference to client object
     */
    public TableModel(TableController tableController, Player clientPlayer, Client client) {
        this.tableController = tableController;
        this.clientPlayer = clientPlayer;
        this.client = client;
    }

    /**
     * Wait for new turn message from server, then update table interface to represent
     * the current active player sent from server.
     * @see Client
     * @see xyz.brandon.blackjack.server.BlackJackGame
     */
    public void waitForNewTurn() {
        System.out.println("Waiting for turn output...");
        ArgsParser args = client.listenForIdentifier("turn");
        System.out.println("Caught identifier: " +args.getIdentifier());
        System.out.println("args="+args.getArgs().toString());

        if (args.has("username")) {
            String username = args.get("username");
            if (!tableController.getCurrentTurn().equals(username)) {
                tableController.clearDeck();
                if (username.equals(clientPlayer.getUsername())) {
                    System.out.println("New turn is for client");
                    tableController.setActivePlayer(username, true);
                } else {
                    System.out.println("New turn is for " + username);
                    tableController.setActivePlayer(username, false);
                    recieveOtherPlayerAction(new Player(username));
                }
            }
        }

    }

    /**
     * Handler for receiving data from server when another player other than client's player
     * is playing (including dealer)
     * @param otherPlayer   local player object representation of active player
     * @return              Reference to the thread listening for active player actions
     * @see ServerActivePlayerListener
     */
    public ServerActivePlayerListener recieveOtherPlayerAction(Player otherPlayer) {
        ServerActivePlayerListener serverActivePlayerListener = new ServerActivePlayerListener(otherPlayer, client, this);
        serverActivePlayerListener.start();
        return serverActivePlayerListener;
    }

    /**
     * Method used to listen for card being sent from server. Used when listening for server message
     * needs to be handled on a separate thread so args are directly sent rather than listened for.
     * @param args      Already received server args
     * @param receiver  Player receiving the card
     * @return          True is player is alright to continue, false if player stands or busts.
     */
    public boolean recieveCard(ArgsParser args, Player receiver) {
        System.out.println("Received card: " + args.get("name"));
        if (args.has("suit") && args.has("number")) {
            Card card = new Card(Suit.valueOf(args.get("suit").toUpperCase()), Integer.parseInt(args.get("number")));
            tableController.addCard(card);
            receiver.recieveCard(card);
            int handValue = receiver.getHandValue();
            tableController.updatePlayerScoreLabel(Integer.toString(handValue));

            if (handValue > 21) {
                playerBusts(receiver);
                return false;
            }
        } else if (args.has("action")) {//Player stands
            if (args.get("action").equals("stand")) {
                return false; //end recieving cards once player stands;
            }
        }


        return true;
    }

    /**
     * Used for receiving card when its client's turn and server client communications are handled on the
     * TableController thread.
     * @param receiver  Player to receive card
     * @return          True if player can continue, false if player busts or stands.
     */
    public boolean recieveCard(Player receiver) {
        ArgsParser args = client.listenForIdentifier("card");
        return recieveCard(args, receiver);
    }

    /**
     * Returns client player reference
     * @return  player object of client
     */
    public Player getClientPlayer() {
        return clientPlayer;
    }

    /**
     * Method called when a player busts when receiving a new card.
     * @param player    player that busts
     */
    public void playerBusts(Player player) {
        tableController.displayAlert("BUST!");
        if (player.getUsername().equals(clientPlayer.getUsername())) {
            tableController.hideControls();
            tableController.updateYourScoreLabel("BUST!");
            tableController.updatePlayerScoreLabel("none");
            client.sendString("action", "username:"+player.getUsername()+"_type:bust", false);
            waitForNewTurn();
        }
    }

    /**
     * returns reference table controller object
     * @return  TableController object
     */
    public TableController getTableController() {
        return tableController;
    }
}

package xyz.brandon.blackjack.client.gui;

import xyz.brandon.blackjack.Card;
import xyz.brandon.blackjack.Suit;
import xyz.brandon.blackjack.client.Player;
import xyz.brandon.blackjack.utils.ArgsParser;

public class TableModel {

    private Player player;
    private TableController tableController;
    private boolean gameActive;

    public TableModel(TableController tableController, Player player) {
        this.tableController = tableController;
        this.player = player;
    }

    public void waitForNewTurn() {
        System.out.println("Waiting for turn output...");
        ArgsParser args = player.getClient().listenForIdentifier("turn");
        System.out.println("Caught identifier: " +args.getIdentifier());

        if (args.has("username")) {
            String username = args.get("username");
            if (!tableController.getCurrentTurn().equals(username)) {
                tableController.clearDeck();
                tableController.setActivePlayer(username, username.equals(player.getUsername()));
            }
        }

    }

    public void recieveCard() {
        ArgsParser args = player.getClient().listenForIdentifier("card");
        System.out.println("Received card: " + args.get("name"));
        if (args.has("suit") && args.has("number")) {
            Card card = new Card(Suit.valueOf(args.get("suit").toUpperCase()), Integer.parseInt(args.get("number")));
            tableController.addCard(card);
            player.recieveCard(card);
            int handValue = player.getHandValue();
            tableController.updatePlayerScoreLabel(Integer.toString(handValue));

            if (handValue > 21) {
                playerBusts();
            }
        }
    }

    public void playerBusts() {
        tableController.hideControls();
        tableController.displayAlert("BUST!");
        //TODO: communicate with server, action=username:username_type:bust, then call waitForNewTurn, once server recieves bust notice it will send a new turn identifier
    }

}

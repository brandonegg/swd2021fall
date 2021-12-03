package xyz.brandon.blackjack.client.gui;

import xyz.brandon.blackjack.Card;
import xyz.brandon.blackjack.Suit;
import xyz.brandon.blackjack.client.Player;
import xyz.brandon.blackjack.client.network.Client;
import xyz.brandon.blackjack.utils.ArgsParser;

public class TableModel {

    private Player clientPlayer;
    private TableController tableController;
    private boolean gameActive;
    private Client client;

    public TableModel(TableController tableController, Player clientPlayer, Client client) {
        this.tableController = tableController;
        this.clientPlayer = clientPlayer;
        this.client = client;
    }

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

    public ServerActivePlayerListener recieveOtherPlayerAction(Player otherPlayer) {
        ServerActivePlayerListener serverActivePlayerListener = new ServerActivePlayerListener(otherPlayer, client, this);
        serverActivePlayerListener.start();
        return serverActivePlayerListener;
    }

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
    public boolean recieveCard(Player receiver) {
        ArgsParser args = client.listenForIdentifier("card");
        return recieveCard(args, receiver);
    }

    public Player getClientPlayer() {
        return clientPlayer;
    }

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

    public TableController getTableController() {
        return tableController;
    }
}

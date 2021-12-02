package xyz.brandon.blackjack.client.gui;

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

}

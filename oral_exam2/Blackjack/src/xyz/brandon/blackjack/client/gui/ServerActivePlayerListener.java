package xyz.brandon.blackjack.client.gui;

import xyz.brandon.blackjack.client.Player;
import xyz.brandon.blackjack.client.network.Client;
import xyz.brandon.blackjack.utils.ArgsParser;

public class ServerActivePlayerListener extends Thread {

    private Player player;
    private Client client;
    private TableModel tableModel;
    private boolean doneDealing;

    public ServerActivePlayerListener(Player player, Client client, TableModel tableModel) {
        this.player = player;
        this.client = client;
        this.tableModel = tableModel;
    }

    @Override
    public void run() {
        doneDealing = false;

        ArgsParser args = client.listenForIdentifier("card");
        while(!args.has("gamestatus")) {
            tableModel.getTableController().recieveServerCard(args, player);
            args = client.listenForIdentifier("card");
        }
        System.out.println(args.getArgs().toString());
        System.out.println("Gamestatus over");
        if (args.get("gamestatus").equals("gameover")) {
            System.out.println("The round is over! Displaying results");
            tableModel.getTableController().displayAlert("player: " + args.get("winner") + " got closest to 21 and is our winner!");
            tableModel.getClientPlayer().resetHand();
            tableModel.getTableController().updateYourScoreLabel("haven't played");
        }
        tableModel.waitForNewTurn();

        doneDealing = true;
    }

    public boolean isDoneDealing() {
        return doneDealing;
    }


}

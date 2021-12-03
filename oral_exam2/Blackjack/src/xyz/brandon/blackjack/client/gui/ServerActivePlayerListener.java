package xyz.brandon.blackjack.client.gui;

import xyz.brandon.blackjack.client.Player;
import xyz.brandon.blackjack.client.network.Client;
import xyz.brandon.blackjack.utils.ArgsParser;

/**
 * Object designed to listen for external player activity when it is a different
 * players turn, and update client players GUI.
 */
public class ServerActivePlayerListener extends Thread {

    /**
     * Local copy of active player
     */
    private Player player;
    /**
     * Reference to client object
     */
    private Client client;
    /**
     * Reference to TableModel
     */
    private TableModel tableModel;
    /**
     * Stores if player is still being dealt cards from server
     */
    private boolean doneDealing;

    /**
     * Creates active player listener, initializes member variables
     * @param player        external player local representation
     * @param client        reference to client object
     * @param tableModel    reference to TableModel
     */
    public ServerActivePlayerListener(Player player, Client client, TableModel tableModel) {
        this.player = player;
        this.client = client;
        this.tableModel = tableModel;
    }

    /**
     * Main method called when thread is started. Listens for cards sent from server
     * until server specifies the external player's turn is done. Updates Table GUI accordingly
     */
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

    /**
     * Returns whether player being listened on turn is done
     * @return  true if done
     */
    public boolean isDoneDealing() {
        return doneDealing;
    }


}

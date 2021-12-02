package xyz.brandon.blackjack.client;

import xyz.brandon.blackjack.Card;
import xyz.brandon.blackjack.client.network.Client;
import xyz.brandon.blackjack.utils.ArgsParser;

public class Player {

    private String username;
    private Client client;
    private Hand hand;

    public Player(Client client) {
        this.client = client;
        this.username = null;
        this.hand = new Hand();
    }

    public String getCurrentTurn() {
        ArgsParser args = client.listenForIdentifier("turn");
        return args.get("username");
    }

    public String getUsername() {
        return username;
    }

    public Client getClient() {
        return client;
    }

    public boolean readyUp(String username) {
        if (hasServer()) {
            if (client.sendString("ready", "username:"+username, true)) {
                this.username = username;
            }
        }
        return (this.username != null);
    }

    public boolean hasServer() {
        if (client != null && client.isConnected()) {
            return true;
        }
        return false;
    }

    public void recieveCard(Card card) {
        hand.addCard(card);
    }

    public void clearHand() {
        hand.clear();
    }

    public int getHandValue() {
        return hand.getValue();
    }
}

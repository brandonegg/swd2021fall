package xyz.brandon.blackjack.client;

import xyz.brandon.blackjack.client.network.Client;

public class Player {

    private String username;
    private Client client;
    private Hand hand;

    public Player(Client client) {
        this.client = client;
        this.username = null;
        this.hand = null;
    }

    public boolean readyUp(String username) {
        if (hasServer()) {
            if (client.sendString("ready", "username:"+username)) {
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

}

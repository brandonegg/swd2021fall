package xyz.brandon.blackjack.server;

import xyz.brandon.blackjack.Card;
import xyz.brandon.blackjack.server.network.GameServer;

import java.util.HashMap;

public class BlackJackGame extends Thread {

    private GameServer blackJackServer;
    private Deck deck;
    private int playerCount;
    private boolean gameActive;
    private HashMap<String, String> actionQueue;

    public BlackJackGame(GameServer blackJackServer) {
        this.deck = new Deck();
        deck.buildDeck();
        this.blackJackServer = blackJackServer;
    }

    @Override
    public void run() {
        System.out.println("Starting game"); //testing
        blackJackServer.sendClientsMsg("game=status:start");

        gameActive = true;
        actionQueue = new HashMap<>();

        System.out.println("Shuffling deck...");
        deck.shuffle();

        String dealingTo = null;
        while (gameActive) {

            for (String username : blackJackServer.getPlayers()) {
                dealingTo = username;
                System.out.println("Dealing to " + username);
                blackJackServer.sendClientsMsg("turn=username:" + username);
            }

            boolean userStands = false;
            while(!userStands) { //eventually end loop when player stands
                HashMap<String, String> cloneQueue = new HashMap<>(actionQueue);
                for (String user : cloneQueue.keySet()) {
                    String action = cloneQueue.get(user);

                    if (action.equals("hit")) {
                        Card dealtCard = deck.drawCard();
                        System.out.println("Dealing a " +dealtCard.toString());
                        blackJackServer.sendUserClientMsg(user, "card=suit:" + dealtCard.getSuit().toString() + "_number:" + dealtCard.getNumber());
                        actionQueue.remove(user);
                    } else if (action.equals("stand")) {
                        //TODO: stand
                    } else {
                        System.out.println("Unrecognized action: " + action);
                    }
                }
            }
        }
    }

    public boolean isGameActive() {
        return gameActive;
    }

    public void queueAction(String username, String action) {
        actionQueue.put(username, action);
    }
}

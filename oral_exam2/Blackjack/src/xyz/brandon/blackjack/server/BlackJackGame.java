package xyz.brandon.blackjack.server;

import xyz.brandon.blackjack.Card;
import xyz.brandon.blackjack.client.Player;
import xyz.brandon.blackjack.server.network.GameServer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class BlackJackGame extends Thread {

    private GameServer blackJackServer;
    private Deck deck;
    private int playerCount;
    private boolean gameActive;
    private HashMap<String, String> actionQueue;
    private HashMap<String, Integer> roundScores; //score of null = bust

    public BlackJackGame(GameServer blackJackServer) {
        this.deck = new Deck();
        deck.buildDeck();
        this.blackJackServer = blackJackServer;
        roundScores = new HashMap<>();
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
        ArrayList<String> playerOrder = new ArrayList<>(blackJackServer.getPlayers());
        playerOrder.add("dealer playing");

        while (gameActive) {
            roundScores.clear();

            while (playerOrder.size() > 0) {

                dealingTo = playerOrder.remove(0);
                System.out.println("Dealing to " + dealingTo);
                blackJackServer.sendClientsMsg("turn=username:" + dealingTo);

                Player currentPlayer = new Player(dealingTo);

                if (dealingTo.equals("dealer playing")) {
                    //TODO: Dealer is playing
                    int previousScore = 0;
                    while (currentPlayer.getHandValue() <= 21) {
                        Card dealtCard = deck.drawCard();
                        currentPlayer.recieveCard(dealtCard);
                        blackJackServer.sendClientsMsg("card=suit:" + dealtCard.getSuit().toString() + "_number:" + dealtCard.getNumber());
                        try {
                            sleep(5000); //temporary delay
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                } else { //Player is playing
                    boolean userActive = true;

                    while (userActive) { //eventually end loop when player stands
                        HashMap<String, String> cloneQueue = new HashMap<>(actionQueue);
                        for (String user : cloneQueue.keySet()) {
                            String action = cloneQueue.get(user);

                            if (action.equals("hit")) {
                                Card dealtCard = deck.drawCard();
                                System.out.println("Dealing a " + dealtCard.toString());
                                currentPlayer.recieveCard(dealtCard);
                                blackJackServer.sendClientsMsg("card=suit:" + dealtCard.getSuit().toString() + "_number:" + dealtCard.getNumber());
                                actionQueue.remove(user);
                            } else if (action.equals("stand")) {
                                Integer handValue = currentPlayer.getHandValue();
                                roundScores.put(currentPlayer.getUsername(), currentPlayer.getHandValue());
                                System.out.println(currentPlayer.getUsername() + " stands with a hand value of " + handValue.toString());
                                userActive = false;
                            } else if (action.equals("bust")) {
                                System.out.println(currentPlayer.getUsername() + " busts!");
                                roundScores.put(currentPlayer.getUsername(), null);
                                userActive = false;
                            } else {
                                System.out.println("Unrecognized action: " + action);
                            }
                        }
                    }
                }
            }

            //TODO: When round is over, calculate who has highest score.
        }
    }

    public boolean isGameActive() {
        return gameActive;
    }

    public void queueAction(String username, String action) {
        System.out.println("Queueing action " + action + " for user: " +username);
        actionQueue.put(username, action);
    }
}

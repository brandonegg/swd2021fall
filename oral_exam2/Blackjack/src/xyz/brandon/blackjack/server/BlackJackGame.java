package xyz.brandon.blackjack.server;

import xyz.brandon.blackjack.Card;
import xyz.brandon.blackjack.client.Player;
import xyz.brandon.blackjack.server.network.GameServer;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Game manager for handling general black jack game functionality
 * from server side. Handled in separate thread.
 * @see Thread
 */
public class BlackJackGame extends Thread {

    /**
     * Reference to blackjack game server object
     */
    private GameServer blackJackServer;
    /**
     * Servers deck of cards to deal from
     */
    private Deck deck;
    /**
     * Stores whether the game manager is currently managing a game.
     */
    private boolean gameActive;
    /**
     * Stores queued actions received from clients
     */
    private HashMap<String, String> actionQueue;
    /**
     * Keeps track of the scores of each player during a round,
     * a bust is represented as null.
     */
    private HashMap<String, Integer> roundScores; //score of null = bust

    /**
     * Constructs a blackjack game server. Requires a game server object
     * for sending data back to connected clients.
     * @param blackJackServer   Blackjack game server reference
     */
    public BlackJackGame(GameServer blackJackServer) {
        this.deck = new Deck();
        deck.buildDeck();
        this.blackJackServer = blackJackServer;
        roundScores = new HashMap<>();
    }

    /**
     * Main method ran when thread is started. Handles game functionality
     * such as dealing cards and selecting whos turn it is.
     */
    @Override
    public void run() {
        System.out.println("Starting game"); //testing
        blackJackServer.sendClientsMsg("game=status:start");

        gameActive = true;

        String dealingTo = null;

        while (gameActive) {
            actionQueue = new HashMap<>();
            deck = new Deck();
            deck.buildDeck();
            System.out.println("Shuffling deck...");
            deck.shuffle();
            ArrayList<String> playerOrder = new ArrayList<>(blackJackServer.getPlayers());
            playerOrder.add("dealer playing");
            roundScores.clear();
            System.out.println("New game with players: " +playerOrder.toString());

            while (playerOrder.size() > 0) {

                dealingTo = playerOrder.remove(0);
                System.out.println("Dealing to " + dealingTo);
                blackJackServer.sendClientsMsg("turn=username:" + dealingTo);

                Player currentPlayer = new Player(dealingTo);

                if (dealingTo.equals("dealer playing")) {
                    int previousValue = 0;

                    while (currentPlayer.getHandValue() <= 21) {
                        previousValue = currentPlayer.getHandValue();

                        Card dealtCard = deck.drawCard();
                        currentPlayer.recieveCard(dealtCard);
                        if (currentPlayer.getHandValue() <= 21) {
                            blackJackServer.sendClientsMsg("card=suit:" + dealtCard.getSuit().toString() + "_number:" + dealtCard.getNumber());
                        }
                        try {
                            sleep(5000); //temporary delay
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }

                    roundScores.put(currentPlayer.getUsername(), previousValue);

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

            String maxScoreUser = null;
            int maxScore = 0;
            for (String user : roundScores.keySet()) {
                if (roundScores.get(user) != null) {
                    if (roundScores.get(user) > maxScore) {
                        maxScoreUser = user;
                        maxScore = roundScores.get(user);
                    }
                }
            }
            if (maxScoreUser == null) {
                maxScoreUser = "none";
            }
            System.out.println(playerOrder.size());
            blackJackServer.sendClientsMsg("card=gamestatus:gameover_winner:"+maxScoreUser);
            try {
                sleep(10000); //Wait til next game
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Returns whether game is active or not
     * @return  true for game active, false if inactive (aka game thread has not been started yet)
     */
    public boolean isGameActive() {
        return gameActive;
    }

    /**
     * Queues an action performed by a client
     * @param username  username of player which performed action
     * @param action    the action they attempt to preform (hit, stand, bust)
     */
    public void queueAction(String username, String action) {
        System.out.println("Queueing action " + action + " for user: " +username);
        actionQueue.put(username, action);
    }
}

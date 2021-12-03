package xyz.brandon.blackjack.client;

import xyz.brandon.blackjack.Card;

/**
 * Object for representing a player in a blackjack game. Handles local
 * interactions and models player data sent to and received from server.
 */
public class Player {

    /**
     * Username of player
     */
    private String username;
    /**
     * Players hand of cards
     * @see Hand
     * @see Card
     */
    private Hand hand;

    /**
     * Player object constructor
     * @param username  username of player
     */
    public Player(String username) {
        this.username = username;
        this.hand = new Hand();
    }

    /**
     * Returns the username of player
     * @return  username of player
     */
    public String getUsername() {
        return username;
    }

    /**
     * Called when a player has been dealt a new card. Card is
     * added to hand.
     * @param card  card to add to hand
     */
    public void recieveCard(Card card) {
        hand.addCard(card);
    }

    /**
     * Clears the player's hand. Called after every round
     */
    public void clearHand() {
        hand.clear();
    }

    /**
     * Computes and returns the value of the cards in the players hand.
     * @return  blackjack hand value
     */
    public int getHandValue() {
        return hand.getValue();
    }

    /**
     * Clear's the player's hand. Called after every round
     */
    public void resetHand() {
        hand = new Hand();
    }
}

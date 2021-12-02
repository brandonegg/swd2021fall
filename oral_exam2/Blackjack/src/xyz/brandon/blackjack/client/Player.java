package xyz.brandon.blackjack.client;

import xyz.brandon.blackjack.Card;

public class Player {

    private String username;
    private Hand hand;

    public Player(String username) {
        this.username = username;
        this.hand = new Hand();
    }

    public String getUsername() {
        return username;
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

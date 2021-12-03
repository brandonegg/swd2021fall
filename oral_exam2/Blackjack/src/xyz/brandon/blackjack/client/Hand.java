package xyz.brandon.blackjack.client;

import xyz.brandon.blackjack.Card;

import java.util.ArrayList;

/**
 * Used for representing a hand of cards, held by a Player
 * @see Player
 * @see Card
 */
public class Hand {

    /**
     * Ordered list of held cards
     */
    private ArrayList<Card> cards;

    /**
     * Creates new empty hand
     */
    public Hand() {
        cards = new ArrayList<>();
    }

    /**
     * Add card to hand
     * @param card  card to add
     */
    public void addCard(Card card) {
        cards.add(card);
    }

    /**
     * Removes card from hand
     * @param card  card to remove
     */
    public void removeCard(Card card) {
        cards.remove(card);
    }

    /**
     * Get list of cards in hand
     * @return  list of cards
     */
    public ArrayList<Card> getCards() {
        return cards;
    }

    /**
     * Clears the hand of cards.
     */
    public void clear() {
        cards.clear();
    }

    /**
     * Computes the blackjack hand value given by the cards in hand.
     * Accounts for ace being 1 or 11.
     * @return  value of hand
     */
    public int getValue() {
        final int maxValue = 21;
        int aceCount = 0;
        int runningTotal = 0;

        for (Card card : cards) {
            if (card.getValue() == 1) { //ace
                if ((runningTotal + 11) > maxValue) {
                    runningTotal += 1;
                } else {
                    runningTotal += 11;
                    aceCount += 1;
                }
            } else {
                runningTotal += card.getValue();
            }

            if (runningTotal > maxValue) {
                if (aceCount > 0) { //aces to convert
                    runningTotal -= 10;
                    aceCount -= 1;
                }
            }
        }

        return runningTotal;
    }

}

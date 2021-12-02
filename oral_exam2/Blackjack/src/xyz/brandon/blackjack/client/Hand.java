package xyz.brandon.blackjack.client;

import xyz.brandon.blackjack.Card;

import java.util.ArrayList;

public class Hand {

    private ArrayList<Card> cards;

    public Hand() {
        cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void removeCard(Card card) {
        cards.remove(card);
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void clear() {
        cards.clear();
    }

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

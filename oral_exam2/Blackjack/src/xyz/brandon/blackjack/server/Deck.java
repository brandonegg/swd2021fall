package xyz.brandon.blackjack.server;

import xyz.brandon.blackjack.Group;
import xyz.brandon.blackjack.Card;
import xyz.brandon.blackjack.Suit;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;

public class Deck {

    ArrayList<Card> pile;

    public Deck() {
        pile = new ArrayList<>();
        buildDeck();
    }

    public boolean isEmpty() {
        return pile.size() == 0;
    }

    public Card peekCard() {
        if (!isEmpty()) {
            return pile.get(0);
        } else {
            throw new NoSuchElementException("Deck is empty");
        }
    }

    public Card drawCard() {
        if (!isEmpty()) {
            return pile.remove(0);
        } else {
            throw new NoSuchElementException("Deck is empty");
        }
    }

    public void buildDeck() {
        for (Suit suit : Suit.values()) {
            for (int i = 1; i<=13; i++) {
                pile.add(new Card(suit,i));
            }
        }
    }

    public void shuffle() {
        Random rand = new Random();
        for (int i = 0; i < pile.size(); i++) {
            int swapIndex = rand.nextInt(pile.size());
            Card temp = pile.get(swapIndex);

            pile.set(swapIndex, pile.get(i));
            pile.set(i, temp);
        }
    }

    @Override
    public String toString() {
        return "Deck contains " + pile.size() + " cards. The next card is a " + peekCard().toString();
    }

}

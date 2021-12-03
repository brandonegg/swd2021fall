package xyz.brandon.blackjack.server;

import xyz.brandon.blackjack.Group;
import xyz.brandon.blackjack.Card;
import xyz.brandon.blackjack.Suit;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * Object representation for a deck of cards used by dealer (server)
 */
public class Deck {

    /**
     * Ordered list of cards in deck
     */
    ArrayList<Card> pile;

    /**
     * Class constructor for deck, automatically adds all 52
     * cards to pile.
     */
    public Deck() {
        pile = new ArrayList<>();
        buildDeck();
    }

    /**
     * Returns if deck is empty
     * @return  true if no cards remain in deck
     */
    public boolean isEmpty() {
        return pile.size() == 0;
    }

    /**
     * Shows but does not remove the next card in the deck
     * @return  next card in deck
     */
    public Card peekCard() {
        if (!isEmpty()) {
            return pile.get(0);
        } else {
            throw new NoSuchElementException("Deck is empty");
        }
    }

    /**
     * Returns and removes the next card in the deck
     * @return  next card in deck
     */
    public Card drawCard() {
        if (!isEmpty()) {
            return pile.remove(0);
        } else {
            throw new NoSuchElementException("Deck is empty");
        }
    }

    /**
     * Generates a standard 52 card blackjack deck
     */
    public void buildDeck() {
        for (Suit suit : Suit.values()) {
            for (int i = 1; i<=13; i++) {
                pile.add(new Card(suit,i));
            }
        }
    }

    /**
     * Randomly reorganizes the pile of cards
     */
    public void shuffle() {
        Random rand = new Random();
        for (int i = 0; i < pile.size(); i++) {
            int swapIndex = rand.nextInt(pile.size());
            Card temp = pile.get(swapIndex);

            pile.set(swapIndex, pile.get(i));
            pile.set(i, temp);
        }
    }

    /**
     * Returns string representation of deck showing card at the top of the deck
     * and number of cards in pile.
     * @return  string showing pile size and next card
     */
    @Override
    public String toString() {
        return "Deck contains " + pile.size() + " cards. The next card is a " + peekCard().toString();
    }

}

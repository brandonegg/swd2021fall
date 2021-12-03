package xyz.brandon.blackjack;

/**
 * Object for representing a black jack playing card
 */
public class Card {

    /**
     * The suit of the card
     */
    private Suit suit;
    /**
     * The number of the card, with exception to ace = 1,
     * 11 = jack, 12 = queen, and 13 = king
     */
    private int number; //1 == Ace, 11=Jack, 12=Queen, 13=King

    /**
     * Card object constructor
     * @param suit      Suit of card
     * @param number    number of card
     */
    public Card(Suit suit, int number) {
        this.suit = suit;
        this.number = number;
    }

    /**
     * Returns value of card in black jack. Aces are treated as 1, left for
     * higher level interpretation.
     * @return  value of card in black jack
     */
    public int getValue() {
        if (number >= 11) {
            return 10;
        } else {
            return number;
        }
    }

    /**
     * Get the suit of the card
     * @return  suit of card
     */
    public Suit getSuit() {
        return suit;
    }

    /**
     * Get the number representation on card
     * (ace = 1, 11=jack, 12=queen, 13=king)
     * @return  number on card
     */
    public int getNumber() {
        return number;
    }

    /**
     * Get string representation of card based on number.
     * Used specifically for non-numbered cards (ace, jack, king, queen)
     * @return  string name for card
     */
    public String getCardName() {
        if (number == 1) {
            return "Ace";
        } else if (number == 11) {
            return "Jack";
        } else if (number == 12) {
            return "Queen";
        } else if (number == 13) {
            return "King";
        } else {
            return Integer.toString(number);
        }
    }

    /**
     * Outputs how a card would be spoken.
     * "*name* of *suit*"
     * @return  string representation of a card
     */
    @Override
    public String toString() {
        return getCardName() + " of " + suit.getName();
    }

}

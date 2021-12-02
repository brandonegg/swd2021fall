package xyz.brandon.blackjack;

public class Card {

    private Suit suit;
    private int number; //1 == Ace, 11=Jack, 12=Queen, 13=King

    public Card(Suit suit, int number) {
        this.suit = suit;
        this.number = number;
    }

    public int getValue() {
        if (number >= 11) {
            return 10;
        } else {
            return number;
        }
    }

    public Suit getSuit() {
        return suit;
    }

    public int getNumber() {
        return number;
    }

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

    @Override
    public String toString() {
        return getCardName() + " of " + suit.getName();
    }

}

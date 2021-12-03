package xyz.brandon.blackjack;

/**
 * Enum representing the various suits in deck of playing cards.
 */
public enum Suit {
    CLUBS("Clubs", Group.BLACK),DIAMONDS("Diamonds", Group.RED),HEARTS("Hearts", Group.RED),SPADES("Spades", Group.BLACK);

    /**
     * String representation of the suit
     */
    private String name;
    /**
     * Group suit belongs to, red or black
     */
    private Group group;

    /**
     * Constructs the enum
     * @param name  associated name for suit
     * @param group group of suit
     */
    Suit(String name, Group group) {
        this.name = name;
        this.group = group;
    }

    /**
     * Gets name of suit
     * @return  string representation of suit
     */
    public String getName() {
        return name;
    }

    /**
     * Returns group of suit
     * @return  group suit belongs to
     */
    public Group getGroup() {
        return group;
    }

}

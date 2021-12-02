package xyz.brandon.blackjack;

public enum Suit {
    CLUBS("Clubs", Group.BLACK),DIAMONDS("Diamonds", Group.RED),HEARTS("Hearts", Group.RED),SPADES("Spades", Group.BLACK);

    private String name;
    private Group group;

    Suit(String name, Group group) {
        this.name = name;
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public Group getGroup() {
        return group;
    }

}

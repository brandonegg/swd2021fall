package xyz.brandon.blackjack;

/**
 * Enum representing the two groups (colors) in a deck of playing cards
 */
public enum Group {
    RED("Red"), BLACK("Black");

    /**
     * String representation of group
     */
    private String name;

    /**
     * enum constructor for group
     * @param name  name of group
     */
    Group(String name) {
        this.name = name;
    }

    /**
     * Get the name of the group
     * @return  string representation of group
     */
    public String getName() {
        return name;
    }
}

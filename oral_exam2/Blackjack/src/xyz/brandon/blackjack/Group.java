package xyz.brandon.blackjack;

public enum Group {
    RED("Red"), BLACK("Black");

    private String name;

    Group(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

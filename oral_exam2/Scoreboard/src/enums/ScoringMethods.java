package enums;

public enum ScoringMethods {
    TOUCHDOWN(6),
    FIELD_GOAL(3),
    EXTRA_POINT(1),
    SAFETY(3),
    TWO_POINT_CONVERSION(2);

    private final int value;

    ScoringMethods(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}

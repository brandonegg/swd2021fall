package enums;

/**
 * List of available scoring methods the Scoreboard application can keep score of.
 */
public enum ScoringMethods {
    TOUCHDOWN(6),
    FIELD_GOAL(3),
    EXTRA_POINT(1),
    SAFETY(3),
    TWO_POINT_CONVERSION(2),
    ONE_POINTER(1),
    TWO_POINTER(2),
    THREE_POINTER(3),
    GOAL(1);

    /**
     * Points gained from associated scoring method
     */
    private final int value;

    /**
     * Constructor for ScoringMethods
     * @param value points associated with scoring method
     */
    ScoringMethods(int value) {
        this.value = value;
    }

    /**
     * Returns amount of points gained from scoring method
     * @return  int representing points
     */
    public int getValue() {
        return value;
    }

}

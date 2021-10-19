package enums;

import dataholder.Period;
import sports.Football;
import sports.Game;

public enum Sport {
    FOOTBALL("quarter", 4, Football.class),
    BASKETBALL("quarter", 4, Football.class),
    SOCCER("half", 2, Football.class),
    HOCKEY("quarter",4, Football.class);

    private final String periodName;
    private final int periodLength;
    private final Class gameClass;

    Sport(String periodName, int periodLength, Class gameClass) {
        this.periodName = periodName;
        this.periodLength = periodLength;
        this.gameClass = gameClass;
    }

    public Period newPeriod() {
        return new Period(periodName, periodLength);
    }

    public Class getGameClass() {
        return gameClass;
    }
}

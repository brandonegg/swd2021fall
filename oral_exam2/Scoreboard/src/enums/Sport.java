package enums;

import dataholder.Period;
import dataholder.Team;
import sports.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public enum Sport {
    FOOTBALL("quarter", 4, 15, Football.class, new ScoringMethods[]
            {ScoringMethods.TOUCHDOWN, ScoringMethods.EXTRA_POINT, ScoringMethods.FIELD_GOAL, ScoringMethods.TWO_POINT_CONVERSION, ScoringMethods.SAFETY}),
    BASKETBALL("quarter", 4, 12, Basketball.class, new ScoringMethods[]
            {ScoringMethods.ONE_POINTER, ScoringMethods.TWO_POINTER, ScoringMethods.THREE_POINTER}),
    SOCCER("half", 2, 45, Soccer.class, new ScoringMethods[]
            {ScoringMethods.GOAL}),
    HOCKEY("regulation",3, 20, Hockey.class, new ScoringMethods[]
            {ScoringMethods.GOAL});

    private final String periodName;
    private final int periodLength;
    private final int periodAmount;
    private final Class gameClass;
    private ScoringMethods[] scoringMethods;

    Sport(String periodName, int periodAmount, int periodLength, Class gameClass, ScoringMethods[] scoringMethods) {
        this.periodName = periodName;
        this.periodAmount = periodAmount;
        this.periodLength = periodLength;
        this.gameClass = gameClass;
        this.scoringMethods = scoringMethods;
    }

    public Period newPeriod() {
        return new Period(periodName, periodAmount, periodLength);
    }

    public Game createGameInstance(Team homeTeam, Team awayTeam) {
        try {
            Constructor classConstructor = gameClass.getConstructor(new Class[] {Team.class, Team.class});
            return (Game)classConstructor.newInstance(homeTeam, awayTeam);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ScoringMethods[] getScoringMethods() {
        return scoringMethods;
    }
}

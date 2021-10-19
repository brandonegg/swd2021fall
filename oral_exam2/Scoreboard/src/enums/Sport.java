package enums;

import dataholder.Period;
import dataholder.Team;
import sports.Football;
import sports.Game;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

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
}

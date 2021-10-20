package enums;

import dataholder.Period;
import dataholder.Team;
import sports.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Sport is an enum which defines the different sports available to choose from alongside their associated
 * stats such as period name, length, and types of scoring methods.
 */
public enum Sport {
    /**
     * Type sport with football related variables
     */
    FOOTBALL("quarter", 4, 15, Football.class, new ScoringMethods[]
            {ScoringMethods.TOUCHDOWN, ScoringMethods.EXTRA_POINT, ScoringMethods.FIELD_GOAL, ScoringMethods.TWO_POINT_CONVERSION, ScoringMethods.SAFETY}),
    /**
     * Type sport with basketball related variables
     */
    BASKETBALL("quarter", 4, 12, Basketball.class, new ScoringMethods[]
            {ScoringMethods.ONE_POINTER, ScoringMethods.TWO_POINTER, ScoringMethods.THREE_POINTER}),
    /**
     * Type sport with soccer related variables
     */
    SOCCER("half", 2, 45, Soccer.class, new ScoringMethods[]
            {ScoringMethods.GOAL}),
    /**
     * Type sport with hockey related variables
     */
    HOCKEY("regulation",3, 20, Hockey.class, new ScoringMethods[]
            {ScoringMethods.GOAL});

    /**
     * Name of a game period for the given sport
     */
    private final String periodName;
    /**
     * Length of a period for the given sport
     */
    private final int periodLength;
    /**
     * Amount of periods in a game for the given sport
     */
    private final int periodAmount;
    /**
     * Associated child class of Game class for the given sport
     */
    private final Class gameClass;
    /**
     * Array of the ScoringMethods available to the given sport
     * @see ScoringMethods
     */
    private ScoringMethods[] scoringMethods;

    /**
     * Sport constructor
     * @param periodName    Name of a period
     * @param periodAmount  number of periods
     * @param periodLength  length of a period
     * @param gameClass     Class object representing game of sport
     * @param scoringMethods    Array of possible methods of scoring
     */
    Sport(String periodName, int periodAmount, int periodLength, Class gameClass, ScoringMethods[] scoringMethods) {
        this.periodName = periodName;
        this.periodAmount = periodAmount;
        this.periodLength = periodLength;
        this.gameClass = gameClass;
        this.scoringMethods = scoringMethods;
    }

    /**
     * Creates a new Period object instance from the given Sport
     * @return  new Period object
     * @see Period
     */
    public Period newPeriod() {
        return new Period(periodName, periodAmount, periodLength);
    }

    /**
     * Creates a Game instance from the given sports associated class.
     * @param homeTeam  The assigned home Team
     * @param awayTeam  The assigned away Team
     * @return  New game object representing the sport and provided teams.
     * @see Game
     */
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

    /**
     * Return the array of scoring methods
     * @return  Array of ScoringMethods
     * @see ScoringMethods
     */
    public ScoringMethods[] getScoringMethods() {
        return scoringMethods;
    }
}

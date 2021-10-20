package sports;

import dataholder.Period;
import dataholder.Team;
import enums.ScoringMethods;
import enums.Sport;

import java.util.Arrays;
import java.util.List;

/**
 * A Game object handles all game related variables such as teams, period data, type of sport, and current score.
 * A Game object can be created directly or indirectly through one of its child classes that specify what time of sport the
 * game object keeps track of.
 * @see Football
 * @see Basketball
 * @see Hockey
 * @see Soccer
 */
public class Game {

    /**
     * Stores the local Period object which keeps track of period name, length, and number.
     * @see Period
     */
    private Period periodData;
    /**
     * Team object which stores the home team's name and score.
     * @see Team
     */
    private Team homeTeam;
    /**
     * Team object which stores the away team's name and score.
     * @see Team
     */
    private Team awayTeam;
    /**
     * Sport enum which stores the sport which this game object is keeping track of.
     * @see Sport
     */
    private Sport sport;
    /**
     * Stores the ScoringMethods associated with the given game.
     * @see ScoringMethods
     */
    private List<ScoringMethods> scoringMethods;
    /**
     * Boolean representing if the game is active, when active is false the game has ended.
     */
    private boolean active;

    /**
     * Game constructor
     * @param home  Home Team object
     * @param away  Away Team object
     * @param sport Selected Sport
     */
    public Game(Team home, Team away, Sport sport) {
        active = true;
        this.homeTeam = home;
        this.awayTeam = away;
        this.sport = sport;
        scoringMethods = Arrays.asList(sport.getScoringMethods());

        periodData = sport.newPeriod();
    }

    /**
     * Adds score amount to the provided team.
     * @param team      The selected Team to add amount to score, must be either home or away team.
     * @param amount    The amount to add to the selected Team's score.
     */
    public void addScore(Team team, int amount) {
        if (isActive()) {
            if (team.equals(homeTeam)) {
                homeTeam.setScore(homeTeam.getScore()+amount);
            } else if (team.equals(awayTeam)) {
                awayTeam.setScore(awayTeam.getScore()+amount);
            } else {
                throw new IllegalArgumentException("Provided team is not playing in this game");
            }
        }
    }

    /**
     * Adds score amount based on provided ScoreingMethod's value
     * @param team  The selected Team to add amount to score, must be either home or away team.
     * @param score The ScoringMethod that the team made.
     * @see ScoringMethods
     */
    public void addScore(Team team, ScoringMethods score) {
        addScore(team, score.getValue());
    }

    /**
     * Updates the period object to switch to the next period. If the maximum period has already been reached
     * active is set to false, ending the game.
     * @see Period
     */
    public void nextPeriod() {
        if (!periodData.increment()) {
            active = false; //end game if last period is over
        }
    }

    /**
     * Sets Period for the Game
     * @param periodData    Period instance
     * @see Period
     */
    public void setPeriodData(Period periodData) {
        this.periodData = periodData;
    }

    /**
     * Sets away team for the Game
     * @param away  Away Team instance
     * @see Team
     */
    public void setAwayTeam(Team away) {
        this.awayTeam = away;
    }

    /**
     * Sets home team for the Game
     * @param home  Home team object
     * @see   Team
     */
    public void setHomeTeam(Team home) {
        this.homeTeam = home;
    }

    /**
     * Sets the sport for the game
     * @param sport Sport enum type
     * @see Sport
     */
    public void setSport(Sport sport) {
        this.sport = sport;
    }

    /**
     * Calling this method ends the game, setting active to false.
     */
    public void endGame() {
        this.active = false;
    }

    /**
     * This method restarts the game, resetting scores and period.
     */
    public void startGame() {
        homeTeam.setScore(0);
        awayTeam.setScore(0);
        periodData = new Period(periodData.getName(), periodData.getMaxPeriod(), periodData.getLength());
        this.active = true;
    }

    /**
     * Returns the active period object
     * @return  Period object
     * @see Period
     */
    public Period getPeriod() {
        return periodData;
    }

    /**
     * Returns the home team object
     * @return  Team object
     * @see Team
     */
    public Team getHomeTeam() {
        return homeTeam;
    }

    /**
     * Returns the away team object
     * @return  Team object
     * @see Team
     */
    public Team getAwayTeam() {
        return awayTeam;
    }

    /**
     * Returns the selected Sport
     * @return  Sport enum type
     * @see Sport
     */
    public Sport getSport() {
        return sport;
    }

    /**
     * Returns whether the game is active, false represents a game that has ended.
     * @return  boolean representing game over/in progress
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Returns the team that is currently winning, or if active is false, the winner of the game.
     * If the game is currently tied, null is returned.
     * @return  An instance of the Team which is currently winning or null if tied.
     * @see Team
     */
    public Team getWinner() {
        if (homeTeam.getScore() > awayTeam.getScore()) {
            return homeTeam;
        } else if (awayTeam.getScore() > homeTeam.getScore()) {
            return awayTeam;
        }
        return null;
    }

    /**
     * Returns the string representation of the Game object
     * @return  String representing object
     * @see String
     */
    public String toString() {
        String outputStr = homeTeam.toString()+", "+awayTeam.toString()+"\n"
                +"Current " +periodData.toString();
        if (!isActive()) {
            outputStr += "\nWinner: ";
            Team winner = getWinner();
            if (winner == null) {
                outputStr += "tie!";
            } else {
                outputStr += winner.getName();
            }
        }

        return outputStr;
    }

    /**
     * Returns the scoring options available for the given sport defined in the Sport enum.
     * @return  List of available ScoringMethods
     * @see Sport
     * @see ScoringMethods
     */
    public List<ScoringMethods> getScoringMethods() {
        return scoringMethods;
    }
}

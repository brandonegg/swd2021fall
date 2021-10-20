package sports;

import dataholder.Period;
import dataholder.Team;
import enums.ScoringMethods;
import enums.Sport;

import java.util.Arrays;
import java.util.List;

public class Game {

    private Period periodData;
    private Team homeTeam;
    private Team awayTeam;
    private Sport sport;
    private List<ScoringMethods> scoringMethods;
    private boolean active;

    public Game(Team home, Team away, Sport sport) {
        active = true;
        this.homeTeam = home;
        this.awayTeam = away;
        this.sport = sport;
        scoringMethods = Arrays.asList(sport.getScoringMethods());

        periodData = sport.newPeriod();
    }

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

    public void addScore(Team team, ScoringMethods score) {
        addScore(team, score.getValue());
    }

    public void nextPeriod() {
        if (!periodData.increment()) {
            active = false; //end game if last period is over
        }
    }

    public void setPeriodData(Period periodData) {
        this.periodData = periodData;
    }

    public void setAwayTeam(Team away) {
        this.awayTeam = away;
    }

    public void setHomeTeam(Team home) {
        this.homeTeam = home;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    public void endGame() {
        this.active = false;
    }

    public void startGame() {
        this.active = true;
        homeTeam.setScore(0);
        awayTeam.setScore(0);
    }

    public Period getPeriod() {
        return periodData;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public Sport getSport() {
        return sport;
    }

    public boolean isActive() {
        return active;
    }

    public Team getWinner() {
        if (homeTeam.getScore() > awayTeam.getScore()) {
            return homeTeam;
        } else if (awayTeam.getScore() > homeTeam.getScore()) {
            return awayTeam;
        }
        return null;
    }

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

    public List<ScoringMethods> getScoringMethods() {
        return scoringMethods;
    }
}

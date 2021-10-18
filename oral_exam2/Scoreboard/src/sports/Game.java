package sports;

import dataholder.Period;
import dataholder.Team;
import enums.Sport;

public class Game {

    private Period periodData;
    private Team homeTeam;
    private Team awayTeam;
    private Sport sport;
    private boolean active;

    public Game(Team home, Team away, Sport sport) {
        active = true;
        this.homeTeam = home;
        this.awayTeam = away;
        this.sport = sport;
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
        //TODO: reset score, other initial setup things
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
}

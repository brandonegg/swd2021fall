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
    }
}

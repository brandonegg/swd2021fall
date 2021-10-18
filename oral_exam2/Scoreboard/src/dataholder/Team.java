package dataholder;

import enums.Sport;

public class Team {

    private int score;
    private String name;
    private Sport sport;

    public void setScore(int score) {
        this.score = score;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public Sport getSport() {
        return sport;
    }

}

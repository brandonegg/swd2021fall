package dataholder;

import enums.Sport;

public class Team {

    private int score;
    private String name;

    public Team(String name) {
        score = 0;
        this.name = name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return name + " - " + score;
    }

}

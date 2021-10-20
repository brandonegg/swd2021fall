package dataholder;

/**
 * Team objects store data related to a given team such as name, and current score.
 * @see sports.Game
 */
public class Team {

    /**
     * Current score
     */
    private int score;
    /**
     * team name
     */
    private String name;

    /**
     * Team class constructor, initializes score to 0
     * @param name  Name assigned to team
     */
    public Team(String name) {
        score = 0;
        this.name = name;
    }

    /**
     * Sets the team score
     * @param score New team score
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Sets the team name
     * @param name  New team name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the team's current score
     * @return  int representing team score
     */
    public int getScore() {
        return score;
    }

    /**
     * Returns the team's name
     * @return  String representing name of team
     */
    public String getName() {
        return name;
    }

    /**
     * Produces string representation of Team object
     * @return  String "team name - score"
     * @see String
     */
    public String toString() {
        return name + " - " + score;
    }

}

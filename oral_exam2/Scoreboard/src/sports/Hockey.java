package sports;

import dataholder.Team;
import enums.Sport;

/**
 * Soccer is a Game with the unique Sport enum identifier being set to SOCCER
 * @see Game
 */
public class Hockey extends Game {

    /**
     * Makes call to Game parent class to instantiate the home/away team alongside setting the Sport to HOCKEY.
     * @param home  The home team object
     * @param away  The away team object
     */
    public Hockey(Team home, Team away) {
        super(home, away, Sport.HOCKEY);
    }

}

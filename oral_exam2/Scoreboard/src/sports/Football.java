package sports;

import dataholder.Team;
import enums.Sport;

/**
 * Football is a Game with the unique Sport enum identifier being set to FOOTBALL
 * @see Game
 */
public class Football extends Game {

    /**
     * Makes call to Game parent class to instantiate the home/away team alongside setting the Sport to FOOTBALL.
     * @param home  The home team object
     * @param away  The away team object
     */
    public Football(Team home, Team away) {
        super(home, away, Sport.FOOTBALL);
    }

}

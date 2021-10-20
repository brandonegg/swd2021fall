package sports;

import dataholder.Team;
import enums.Sport;

/**
 * Basketball is a Game with the unique Sport enum identifier being set to BASKETBALL
 * @see Game
 */
public class Basketball extends Game {

    /**
     * Makes call to Game parent class to instantiate the home/away team alongside setting the Sport to BASKETBALL.
     * @param home  The home team object
     * @param away  The away team object
     */
    public Basketball(Team home, Team away) {
        super(home, away, Sport.BASKETBALL);
    }

}

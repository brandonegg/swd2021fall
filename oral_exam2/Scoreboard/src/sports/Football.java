package sports;

import dataholder.Team;
import enums.Sport;

public class Football extends Game {

    public Football(Team home, Team away) {
        super(home, away, Sport.FOOTBALL);
    }

}

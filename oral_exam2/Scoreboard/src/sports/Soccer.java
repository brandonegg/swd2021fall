package sports;

import dataholder.Team;
import enums.Sport;

public class Soccer extends Game {

    public Soccer(Team home, Team away) {
        super(home, away, Sport.SOCCER);
    }

}

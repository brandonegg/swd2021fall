package sports;

import dataholder.Team;
import enums.Sport;

public class Hockey extends Game {

    public Hockey(Team home, Team away) {
        super(home, away, Sport.HOCKEY);
    }

}

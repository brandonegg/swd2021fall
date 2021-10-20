package sports;

import dataholder.Team;
import enums.Sport;

public class Basketball extends Game {

    public Basketball(Team home, Team away) {
        super(home, away, Sport.BASKETBALL);
    }

}

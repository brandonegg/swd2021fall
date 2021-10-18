package sports;

import dataholder.Team;
import enums.Sport;

public class Football extends Game {

    public enum SCORING_METHOD {
        TOUCHDOWN(6),
        FIELD_GOAL(3),
        EXTRA_POINT(1),
        SAFETY(3),
        TWO_POINT_CONVERSION(2);

        private final int value;

        SCORING_METHOD(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

    }

    public Football(Team home, Team away) {
        super(home, away, Sport.FOOTBALL);
    }

    public void addScore(Team team, SCORING_METHOD scoreType) {
        super.addScore(team, scoreType.getValue());
    }

}

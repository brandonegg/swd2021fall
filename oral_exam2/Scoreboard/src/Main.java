import dataholder.Team;
import enums.Sport;
import sports.Football;
import sports.Game;

import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        startCommandLine();
    }

    public static void startCommandLine() {
        Scanner in  = new Scanner(System.in);

        System.out.println("Select the type of game:");
        int i = 1;
        for (Sport sport : Sport.values()) {
            System.out.println(i+": " + sport.toString());
            i++;
        }
        System.out.print("Enter choice: ");
        int sportIndex = in.nextInt();
        Sport selectedSport = Sport.values()[sportIndex-1];
        System.out.println("");

        System.out.print("Enter Home Team: ");
        Team homeTeam = new Team(in.next());
        System.out.print("Enter Away Team: ");
        Team awayTeam = new Team(in.next());

        Game currentGame = null;

        switch(selectedSport) {
            case FOOTBALL:
                currentGame = new Football(homeTeam, awayTeam);
        }
        System.out.println(currentGame.toString());
    }
}

import dataholder.Team;
import enums.ScoringMethods;
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
        in.nextLine();//clear registry
        System.out.print("Enter Home Team: ");
        Team homeTeam = new Team(in.nextLine());
        System.out.print("Enter Away Team: ");
        Team awayTeam = new Team(in.nextLine());

        Game currentGame = selectedSport.createGameInstance(homeTeam, awayTeam);
        System.out.println(currentGame.toString());

        while (currentGame.isActive()) {
            System.out.println("Menu: ");
            i = 1;
            for (ScoringMethods scoringMethod : currentGame.getScoringMethods()) {
                System.out.println(i+". " + currentGame.getHomeTeam().getName() + " " + scoringMethod.toString());
                i++;
            }
            for (ScoringMethods scoringMethod : currentGame.getScoringMethods()) {
                System.out.println(i+". " + currentGame.getAwayTeam().getName() + " " + scoringMethod.toString());
                i++;
            }
            System.out.println(i+". End " + currentGame.getPeriod().getName());
            System.out.print("Enter choice: ");
            int choice = in.nextInt();

            if (choice > i) {
                System.out.println("Invalid repsponse, please pick a number between 1 and "+ i);
            } else if (choice == i) {
                //End period
                currentGame.nextPeriod();
            } else if (choice <= currentGame.getScoringMethods().size()) {
                //Home score
                currentGame.addScore(currentGame.getHomeTeam(), currentGame.getScoringMethods().get(choice-1));
            } else {
                //Away score
                currentGame.addScore(currentGame.getAwayTeam(), currentGame.getScoringMethods().get(choice-currentGame.getScoringMethods().size()-1));
            }
            System.out.println("");
            System.out.println(currentGame.toString());
            System.out.println("");
        }

        System.out.println("Game is over.");
        currentGame.toString();

    }
}

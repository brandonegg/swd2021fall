import enums.Sport;
import sports.Football;
import sports.Game;

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
        Sport.values()[i-1].getGameClass();//TODO:

    }
}

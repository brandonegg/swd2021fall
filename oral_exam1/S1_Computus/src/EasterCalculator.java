import java.util.Scanner;

/**
 * EasterCalculator is the main class for the Easter project, it takes an input year
 * from the console and computes the equivalent Easter date then runs EasterCycle
 * @see EasterCycle
 */
public class EasterCalculator {

    /**
     * Requests user to input year in console, then uses Easter to compute
     * the date of easter for that year. Then it runs EasterCycle and outputs results.
     * @param args  runtime inputs
     * @see         EasterCycle
     * @see         Easter
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Input year: ");
        int year = input.nextInt();

        Easter easterDate = new Easter(year);
        System.out.println(easterDate.toString());

        System.out.println("Now outputing date occurrences over an entire Easter cycle:");
        EasterCycle.outputEasterDateCounts();
    }
}

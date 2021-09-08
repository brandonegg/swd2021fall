import java.util.Scanner;

public class EasterCalculator {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Input year: ");
        int year = input.nextInt();

        Easter easterDate = new Easter(year);
        System.out.println(easterDate.toString());
    }
}

import java.util.Scanner;

/**
 * Main class for running interactive terminal
 * english to morse code and morse code to english translator.
 */
public class MorseCode {

    /**
     * Stores the CodeConverter object used to convert English sentences to Morse Code sentences
     * and Morse code sentences to English.
     * @see CodeConverter
     */
    public static CodeConverter codeConverter;

    /**
     * Initializes a CodeConverter object and starts the terminal user interface for translating.
     * @param args
     */
    public static void main(String[] args) {
        codeConverter = new CodeConverter(new MorseLibrary());
        startTerminalInterface();
    }

    /**
     * Launches terminal interface for user input to translate
     * English sentences to morse code and morse code to English sentences
     * @see CodeConverter
     */
    public static void startTerminalInterface() {
        Scanner input = new Scanner(System.in);
        boolean active = true;

        while(active) {
            System.out.println("Select an option below:");
            System.out.println("1. Convert English to Morse Code");
            System.out.println("2. Convert Morse Code to English");
            System.out.print("Enter: ");
            int selection = input.nextInt();

            boolean selectionActive = true;
            input.nextLine();
            if (selection == 1) {
                while (selectionActive) {
                    System.out.println("Note: type 'back' to return to the previous menu");
                    System.out.print("Enter English Sentence Below: ");
                    String text = input.nextLine();
                    if (text.equalsIgnoreCase("back")) {
                        selectionActive = false;
                    } else {
                        System.out.println("Morse code: " + codeConverter.convertEnglishSentence(text));
                    }
                }
            } else if (selection == 2) {
                while (selectionActive) {
                    System.out.println("Note: type 'back' to return to the previous menu");
                    System.out.print("Enter Morse Code Sentence: ");
                    String text = input.nextLine();
                    if (text.equalsIgnoreCase("back")) {
                        selectionActive = false;
                    } else {
                        System.out.println("English: " + codeConverter.convertMorseSentence(text));
                    }
                }
            } else {
                System.out.println("Invalid input!");
            }
        }
    }

}

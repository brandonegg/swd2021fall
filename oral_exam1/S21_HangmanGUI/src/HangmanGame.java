import components.HangmanFrame;

import javax.swing.*;
import java.util.ArrayList;

public class HangmanGame {
    //Strings all stored in lower case for consistency
    private HangmanFrame gameWindow;
    private String word;
    private ArrayList<String> guessedWords;
    private ArrayList<Character> incorrectGuessedLetters;

    public HangmanGame(String word) {
        this.word = word.toLowerCase();

        guessedWords = new ArrayList<>();
        incorrectGuessedLetters = new ArrayList<>();
        gameWindow = new HangmanFrame();
    }

    public void changeWord(String word) {
        //TODO: Restarts game, changes word
    }

    public boolean guessWord(String word) {
        /*TODO: Checks if word has been guessed already
            Then checks if word is correct word
            Returns true/false based on successful guess
         */
        return false;
    }
    public boolean hasWordBeenGuessed(String word) {
        if (guessedWords.contains(word.toLowerCase())) {
            return true;
        }
        return false;
    }

    public boolean startGame() {
        //Returns true if player wins
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setSize(400,200);
        gameWindow.setVisible(true);

        return true;
    }
}
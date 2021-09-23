package managers;

import components.HangmanFrame;

import javax.swing.*;
import java.util.ArrayList;

public class HangmanGame {
    //Strings all stored in lower case for consistency
    private GuessButtonListener guessButtonListener;

    private HangmanFrame gameWindow;
    private String wordToGuess;
    private String progressString;
    private ArrayList<String> guessedWords;
    private ArrayList<Character> guessedLetters;
    private ArrayList<Character> incorrectGuessedLetters;

    public HangmanGame(String word) {
        this.wordToGuess = word.toLowerCase();

        guessedWords = new ArrayList<>();
        incorrectGuessedLetters = new ArrayList<>();
        guessedLetters = new ArrayList<>();

        guessButtonListener = new GuessButtonListener(this);

        gameWindow = new HangmanFrame(word.length(), guessButtonListener);

        progressString = "";
        for (int i = 0; i<word.length(); i++) {
            progressString = progressString+" _";
        }

        gameWindow.updateWordProgress(progressString);

    }

    public void updateProgressStr(Character newChar) {
        for (int i=0; i<wordToGuess.length(); i++) {
            if (wordToGuess.charAt(i) == newChar) {
                progressString = progressString.substring(0,(2*i)+1) + newChar + progressString.substring((2*i)+2);
            }
        }
        gameWindow.updateWordProgress(progressString);
    }

    public void endGame(boolean playerWon) {
        if (playerWon) {
            JOptionPane.showMessageDialog(gameWindow, "You won!");
        } else {
            JOptionPane.showMessageDialog(gameWindow, "You lose!");
        }
    }

    public boolean makeGuess() {
        return makeGuess(gameWindow.getGuessText());
    }

    public boolean makeGuess(String word) {
        String cleanedWord = word.toLowerCase().strip();
        System.out.println(word);
        if (cleanedWord.length() == 1) {
            Character letter = cleanedWord.charAt(0);
            if (guessedLetters.contains(letter)) {
                System.out.println("The letter " +cleanedWord + " has already been guessed!");
                gameWindow.sendAlert("The letter " +cleanedWord + " has already been guessed!");

                return false;
            }

            if (wordToGuess.contains(cleanedWord)) {
                updateProgressStr(letter);
                guessedLetters.add(letter);

                if (checkCompletion()) {
                    endGame(true);
                }

                gameWindow.refreshGameFields();
                return true;
            } else {
                System.out.println("The letter " + cleanedWord + " is not in the word.");
                gameWindow.sendAlert("The letter '" + cleanedWord + "' is not in the word.");
                guessedLetters.add(letter);
                incorrectGuessedLetters.add(letter);

                gameWindow.updateIncorrectLetters(incorrectGuessedLetters);
                gameWindow.refreshGameFields();
                return false;
            }
        } else {
            if (cleanedWord.equals(wordToGuess)) {
                endGame(true);
                return true;
            } else {
                guessedWords.add(cleanedWord);
                gameWindow.sendAlert("'"+cleanedWord + "' is not the word!");

                gameWindow.refreshGameFields();

                return false;
            }
        }

    }
    public boolean checkCompletion() {
        if (progressString.contains("_")) {
            return false;
        } else {
            return true;
        }
    }

    public boolean hasWordBeenGuessed(String word) {
        if (guessedWords.contains(word.toLowerCase())) {
            return true;
        }
        return false;
    }

    public void start() {
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setSize(400,250);
        gameWindow.setVisible(true);
    }
}
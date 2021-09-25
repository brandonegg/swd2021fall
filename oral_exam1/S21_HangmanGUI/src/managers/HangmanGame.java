package managers;

import components.HangmanFrame;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

/**
 * HangmanGame manager, creates a hangman game window to play.
 */
public class HangmanGame {
    //Strings all stored in lower case for consistency
    private GuessButtonListener guessButtonListener;

    private HangmanFrame gameWindow;
    private String wordToGuess;
    private String progressString;
    private ArrayList<String> guessedWords;
    private ArrayList<Character> guessedLetters;
    private ArrayList<Character> incorrectGuessedLetters;

    /**
     * Initializes defaults and creates the Hangman game Frame.
     * @param word  Word to guess
     * @see     HangmanFrame
     */
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

    /**
     * Takes a new character, finds its location(s) in the word to guess string
     * and adds the letters to the progress string. Then the hangmanFrame is updated
     * with the new progress string.
     * @param newChar   New character to add
     * @see   HangmanFrame
     */
    public void updateProgressStr(Character newChar) {
        for (int i=0; i<wordToGuess.length(); i++) {
            if (wordToGuess.charAt(i) == newChar) {
                progressString = progressString.substring(0,(2*i)+1) + newChar + progressString.substring((2*i)+2);
            }
        }
        gameWindow.updateWordProgress(progressString);
    }

    /**
     * Ends the game, displays a pop up showing whether the player won or lost
     * based on playerWon parameter.
     * @param playerWon     True means end game on player wins condition, False means end game on player loses condition
     */
    public void endGame(boolean playerWon) {
        if (playerWon) {
            JOptionPane.showMessageDialog(gameWindow, "You won!");
        } else {
            JOptionPane.showMessageDialog(gameWindow, "You lose!");
        }
        gameWindow.dispatchEvent(new WindowEvent(gameWindow, WindowEvent.WINDOW_CLOSING));
    }

    /**
     * Make guess, called by guess button actionevent. Takes input field text
     * from hangmanframe and checks if letter guessed is correct.
     * @return  True represents successful guess (letter present), False represents letter not present
     * @see     HangmanFrame
     * @see     GuessButtonListener
     */
    public boolean makeGuess() {
        return makeGuess(gameWindow.getGuessText());
    }

    /**
     * Takes a string and checks whether it is the word, or the letter is present in the word.
     * Handles updating the stickfigure or word progress based on whether the guess was successful.
     * @param word  Letter/word the user guessed
     * @return  True represents successsful guess (letter present), False represents letter not present
     */
    public boolean makeGuess(String word) {
        String cleanedWord = word.toLowerCase().trim();
        gameWindow.sendAlert("");

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

                if (!gameWindow.drawNextPart()) {
                    endGame(false);
                }

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
                System.out.println("'"+cleanedWord + "' is not the word!");
                gameWindow.sendAlert("'"+cleanedWord + "' is not the word!");

                if (!gameWindow.drawNextPart()) {
                    endGame(false);
                }

                gameWindow.refreshGameFields();

                return false;
            }
        }

    }

    /**
     * Checks if the progress string is complete, all letters guessed
     * @return  True if all letters have been guessed, False if not.
     */
    public boolean checkCompletion() {
        if (progressString.contains("_")) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Checks if word has been guessed already
     * @param word  Word to check
     * @return      True if word has already been guessed, False if word hasn't been guessed yet
     */
    public boolean hasWordBeenGuessed(String word) {
        if (guessedWords.contains(word.toLowerCase())) {
            return true;
        }
        return false;
    }

    /**
     * Starts the game, called on HangmanGame when ready to start game and launch window.
     */
    public void start() {
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setSize(400,250);
        gameWindow.setVisible(true);
    }
}
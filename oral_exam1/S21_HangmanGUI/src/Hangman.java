import managers.HangmanGame;

import javax.swing.*;

/**
 * Main hangman class, run to play hangman game!
 */
public class Hangman {

    /**
     * Prompts user for word to guess, and creates a hangmangame based on this word
     * @param args  input args
     * @see   HangmanGame
     */
    public static void main(String[] args) {
        String wordToGuess = JOptionPane.showInputDialog("Input a word to guess (don't show guesser)");

        HangmanGame hangmanGame = new HangmanGame(wordToGuess);
        hangmanGame.start();
    }
}

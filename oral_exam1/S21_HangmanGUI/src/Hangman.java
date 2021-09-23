package src;

import managers.HangmanGame;

import javax.swing.*;

public class Hangman {

    public static void main(String[] args) {
        String wordToGuess = JOptionPane.showInputDialog("Input a word to guess (don't show guesser)");

        HangmanGame hangmanGame = new HangmanGame(wordToGuess);
        hangmanGame.start();
    }
}

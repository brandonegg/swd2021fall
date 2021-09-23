import javax.swing.JOptionPane;

public class Hangman {

    public static void main(String[] args) {
        String wordToGuess = JOptionPane.showInputDialog("Input a word to guess (don't show guesser)");

        HangmanGame hangmanGame = new HangmanGame(wordToGuess);
        hangmanGame.startGame();
    }
}

package managers;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuessButtonListener implements ActionListener {

    private final HangmanGame hangmanGame;

    public GuessButtonListener(HangmanGame hangmanGame) {
        this.hangmanGame = hangmanGame;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource().getClass().equals(JButton.class)) {
            hangmanGame.makeGuess();
        } else {
            hangmanGame.makeGuess(event.getActionCommand());
        }
    }
}

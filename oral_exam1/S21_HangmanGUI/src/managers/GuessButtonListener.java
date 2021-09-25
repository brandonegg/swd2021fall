package managers;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * GuessButtonListener is an ActionListener for handling the event the guess button is pressed
 * or enter is pressed on the GameFields input field.
 */
public class GuessButtonListener implements ActionListener {

    private final HangmanGame hangmanGame;

    /**
     * Constructor for gaining reference to parent HangmanGame object
     * @param hangmanGame   the hangmangame in which this ActionListener is listening for.
     */
    public GuessButtonListener(HangmanGame hangmanGame) {
        this.hangmanGame = hangmanGame;
    }

    /**
     * Calls either makeGuess() or makeGuess(letter) depending on whether event has
     * an actioncommand (aka return pressed) or not.
     * @param event     Passed by ActionListener
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource().getClass().equals(JButton.class)) {
            hangmanGame.makeGuess();
        } else {
            hangmanGame.makeGuess(event.getActionCommand());
        }
    }
}

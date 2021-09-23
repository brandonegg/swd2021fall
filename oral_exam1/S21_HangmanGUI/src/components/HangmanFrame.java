package components;

import managers.GuessButtonListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class HangmanFrame extends JFrame {

    private StickfigureComponent stickFigure;
    private GameFieldsPanel gameFields;
    private Graphics2D g2d;

    public HangmanFrame(int wordSize, GuessButtonListener guessButtonListener) {

        setSize(450,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Hangman");

        setLayout(new GridLayout(1,2));

        //Create stick figure frame, left side of window
        stickFigure = new StickfigureComponent();
        add(stickFigure);

        //Create input fields, right side of window
        gameFields = new GameFieldsPanel(wordSize, guessButtonListener);
        add(gameFields);

        setVisible(true);

    }

    public boolean drawNextPart() {
        return stickFigure.drawNextPart(stickFigure.getGraphics());
    }

    public void updateIncorrectLetters(ArrayList<Character> incorrectCharacters) {
        String strBldr = "";
        for (Character incorrectChar : incorrectCharacters) {
            strBldr += incorrectChar + ", ";
        }
        if (incorrectCharacters.size() > 0) {
            strBldr = strBldr.substring(0, strBldr.length()-2);
        }
        gameFields.updateLettersGuessed(strBldr);
    }

    public void sendAlert(String msg) {
        gameFields.updateInputResponseMessage(msg);
    }

    public void refreshGameFields() {
        gameFields.clearInputField();
    }

    public void updateWordProgress(String wordProgress) {
        gameFields.updateProgress(wordProgress);
    }

    public String getGuessText() {
        return gameFields.getInputField().toLowerCase().strip();
    }

}

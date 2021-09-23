package components;

import managers.GuessButtonListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class HangmanFrame extends JFrame {

    private StickfigureComponent stickFigure;
    private GameFieldsPanel gameFields;

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

    public void drawNextPart() {
        //Draws next part of stickman
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
        System.out.println(gameFields.getInputField());
        return gameFields.getInputField().toLowerCase().strip();
    }

}

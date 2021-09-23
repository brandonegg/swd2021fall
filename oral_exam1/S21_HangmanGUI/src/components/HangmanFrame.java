package components;

import javax.swing.*;
import java.awt.*;

public class HangmanFrame extends JFrame {

    private StickfigureFrame stickFigure;
    private GameFieldsFrame gameFields;

    public HangmanFrame() {
        setLayout(new FlowLayout());
        setTitle("Hangman");

        //Create stick figure frame, left side of window
        stickFigure = new StickfigureFrame();
        add(stickFigure);

        //Create input fields, right side of window
        gameFields = new GameFieldsFrame();
        add(gameFields);

    }

    public void drawNextPart() {
        //Draws next part of stickman
    }

}

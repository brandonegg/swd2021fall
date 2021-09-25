package components;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;

/**
 * StickFigureComponent is a JComponent which handles drawing the hangman
 * within the HangmanFrame window
 * @see HangmanFrame
 */
public class StickfigureComponent extends JComponent {

    ArrayList<Shape> bodyParts = new ArrayList<Shape>();

    /**
     * Creates each body part, adding them to an ordered arraylist to be drawn by drawNextPart()
     */
    public StickfigureComponent() {
        bodyParts.add(new Ellipse2D.Float(90, 75, 20, 20));
        bodyParts.add(new Line2D.Float(100, 95, 100, 150));
        bodyParts.add(new Line2D.Float(100, 105, 75, 95));
        bodyParts.add(new Line2D.Float(100, 105, 125, 95));
        bodyParts.add(new Line2D.Float(100, 150, 80, 170));
        bodyParts.add(new Line2D.Float(100, 150, 120, 170));
    }

    /**
     * Paint overridden from JComponent, called by HangmanFrame JFrame when set to visible
     * @param g     Graphics object passed by JFrame
     * @see   HangmanFrame
     */
    @Override
    public void paint(Graphics g) {
        g.setColor(Color.LIGHT_GRAY);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(5));
        drawStand(g2);
    }

    /**
     * Draws the stand for the hangman, created at start.
     * @param g2    Takes graphics2D object, sent from paint()
     * @see   HangmanFrame
     */
    public void drawStand(Graphics2D g2) {
        g2.draw(new Line2D.Float(100, 50, 100, 75));
        g2.draw(new Line2D.Float(100, 50, 150,50));
        g2.draw(new Line2D.Float(150, 50, 150, 200));
        g2.draw(new Line2D.Float(75, 200, 175, 200));
    }

    /**
     * Draws the next part in the bodyParts arraylist.
     * @param g     Graphics passed from HangmanFrame
     * @return  Returns true is a new part is successfully drawn, false if the last part was drawn (game lost).
     */
    public boolean drawNextPart(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3));
        g2.setColor(Color.ORANGE);

        g2.draw(bodyParts.remove(0));
        if (bodyParts.size() == 0) {
            return false;
        } else {
            return true;
        }
    }

}

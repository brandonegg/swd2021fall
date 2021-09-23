package components;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;

public class StickfigureComponent extends JComponent {

    ArrayList<Shape> bodyParts = new ArrayList<Shape>();

    public StickfigureComponent() {
        bodyParts.add(new Ellipse2D.Float(90, 75, 20, 20));
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.LIGHT_GRAY);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(5));
        drawStand(g2);

        g2.setStroke(new BasicStroke(3));
        g2.setColor(Color.ORANGE);
        drawNextPart(g2);
    }

    public void drawStand(Graphics2D g2) {
        g2.draw(new Line2D.Float(100, 50, 100, 75));
        g2.draw(new Line2D.Float(100, 50, 150,50));
        g2.draw(new Line2D.Float(150, 50, 150, 200));
        g2.draw(new Line2D.Float(75, 200, 175, 200));
    }

    public void drawNextPart(Graphics2D g2) {
        g2.draw(bodyParts.remove(0));
    }

}

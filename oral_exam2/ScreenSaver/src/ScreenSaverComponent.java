import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.Random;

public class ScreenSaverComponent extends JComponent implements KeyListener {

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        drawRandomLine(g2);
        repaint();
    }

    public void drawRandomLine(Graphics2D g2) {
        Random rand = new Random();
        BasicStroke selectedStroke = new BasicStroke(rand.nextInt(10)+1);
        Color selectedColor = new Color(rand.nextInt(255), rand.nextInt(255),rand.nextInt(255));
        Point2D startPoint = new Point2D.Double(Double.valueOf(rand.nextInt(this.getWidth())), Double.valueOf(rand.nextInt(this.getHeight())));
        Point2D endPoint = new Point2D.Double(Double.valueOf(rand.nextInt(this.getWidth())), Double.valueOf(rand.nextInt(this.getHeight())));

        g2.setColor(selectedColor);
        g2.setStroke(selectedStroke);
        g2.draw(new Line2D.Float(startPoint, endPoint));
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //unused
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //unused
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //unused
    }
}

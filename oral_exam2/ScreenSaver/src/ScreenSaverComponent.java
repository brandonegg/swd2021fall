import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Line2D;

public class ScreenSaverComponent extends JComponent implements KeyListener {

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.LIGHT_GRAY);
        Graphics2D g2 = (Graphics2D) g;

        g2.setStroke(new BasicStroke(5));
        g2.draw(new Line2D.Float(0, 0, 100, 75));
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

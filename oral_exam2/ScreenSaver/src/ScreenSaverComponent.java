import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ScreenSaverComponent extends JComponent implements KeyListener {

    private final int maxLines = 100;
    private final long refreshTimeSec =  1;
    private ArrayList<Line2D> linesToDraw;
    private ArrayList<Color> lineColors;
    private ArrayList<BasicStroke> lineStrokes;

    public ScreenSaverComponent() {
        linesToDraw = new ArrayList<Line2D>();
        lineColors = new ArrayList<Color>();
        lineStrokes = new ArrayList<BasicStroke>();
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        drawRandomLine(g2);
        try {
            TimeUnit.SECONDS.sleep(refreshTimeSec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        repaint();
    }

    public void drawRandomLine(Graphics2D g2) {
        if (linesToDraw.size() == maxLines) {
            linesToDraw.clear();
            lineColors.clear();
            lineStrokes.clear();
        }

        Random rand = new Random();
        BasicStroke selectedStroke = new BasicStroke(rand.nextInt(10)+1);
        Color selectedColor = new Color(rand.nextInt(255), rand.nextInt(255),rand.nextInt(255));
        Point2D startPoint = new Point2D.Double(Double.valueOf(rand.nextInt(this.getWidth())), Double.valueOf(rand.nextInt(this.getHeight())));
        Point2D endPoint = new Point2D.Double(Double.valueOf(rand.nextInt(this.getWidth())), Double.valueOf(rand.nextInt(this.getHeight())));

        Line2D lineToDraw = new Line2D.Float(startPoint, endPoint);

        linesToDraw.add(lineToDraw);
        lineColors.add(selectedColor);
        lineStrokes.add(selectedStroke);

        for (int i=0; i<linesToDraw.size(); i++) {
            g2.setColor(lineColors.get(i));
            g2.setStroke(lineStrokes.get(i));
            g2.draw(linesToDraw.get(i));
        }
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

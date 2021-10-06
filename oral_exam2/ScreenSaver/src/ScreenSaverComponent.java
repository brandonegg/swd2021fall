import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * ScreenSaverComponent is a JComponent which handles painting the random lines displayed on the ScreenSaverFrame.
 * @see JComponent
 * @see ScreenSaverFrame
 */
public class ScreenSaverComponent extends JComponent {

    /**
     * Maximum number of lines drawn to the screen
     */
    private final int maxLines = 100;
    /**
     * Time between new line draws (in milliseconds)
     */
    private final long refreshTimeMiliSec =  250;
    /**
     * List of lines Line2D objects to draw to screen
     */
    private ArrayList<Line2D> linesToDraw;
    /**
     * List of Colors, indexed in order with linesToDraw
     */
    private ArrayList<Color> lineColors;
    /**
     * List of BasicStroke, indexed in order with linesToDraw
     */
    private ArrayList<BasicStroke> lineStrokes;

    /**
     * Constructs an empty list for storing lines to draw, and their associated line colors, and stroke size.
     * These are used to store the random lines which are drawn after each refresh cycle.
     * This constructor is called and added to the ScreenSaverFrame.
     * @see ScreenSaverFrame
     */
    public ScreenSaverComponent() {
        linesToDraw = new ArrayList<Line2D>();
        lineColors = new ArrayList<Color>();
        lineStrokes = new ArrayList<BasicStroke>();
    }

    /**
     * Overrides paintComponent in the JComponent class. Called by JFrame when setVisible is true.
     * Method is also called whenever a repaint call is queued.
     * @param g Passed graphics object from paint call.
     */
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        drawRandomLines(g2);
        try {
            TimeUnit.MILLISECONDS.sleep(refreshTimeMiliSec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        repaint();
    }

    /**
     * Called by paintComponent, checks if max line limit has been reached, if so lines are reset. If limit
     * hasn't been reached, a new line is added and then this line plus all previous lines are drawn to screen.
     * @param g2    Graphics2D instance passed from paintComponent
     */
    public void drawRandomLines(Graphics2D g2) {
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
}

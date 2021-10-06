import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.util.Arrays;
import java.util.List;

/**
 * ScreenSaverFrame is a JFrame while also implementing the KeyListener structure. This class is the
 * main ScreenSaver window where ScreenSaverComponent is drawn onto, and key presses are listened to.
 * @see ScreenSaverComponent
 */
public class ScreenSaverFrame extends JFrame implements KeyListener {

    /**
     * An array of characters representing keys that when pressed will close the application.
     */
    private final Character[] quitAppKeys = {'q', '\u001B'};

    /**
     * Reference to local instance of screenSaverComponent
     */
    private ScreenSaverComponent screenSaverComponent;

    /**
     * Constructor which creates the frame object and displays it to screen. Also creates
     * an instance of screenSaverComponent to handle drawing of lines which begins when frame
     * is set to visible.
     * @see ScreenSaverComponent
     */
    public ScreenSaverFrame() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(false);

        screenSaverComponent = new ScreenSaverComponent();
        add(screenSaverComponent);

        this.addKeyListener(this);
        setVisible(true);
    }

    /**
     * Called whenever a key is pressed by Event Listener
     * @param e KeyEvent passed by KeyListener when keyTyped event occurs.
     * @see KeyListener
     */
    @Override
    public void keyTyped(KeyEvent e) {
        char key = e.getKeyChar();
        List<Character> keyList = Arrays.asList(quitAppKeys);

        System.out.println(key);

        if (keyList.contains(key)) {
            System.exit(0);
        }
    }

    /**
     * Unused required method for interface KeyListener
     * @param e
     */
    @Override
    public void keyPressed(KeyEvent e) {
        //Unused
    }

    /**
     * Unused required method for interface KeyListener
     * @param e
     */
    @Override
    public void keyReleased(KeyEvent e) {
        //Unused
    }
}

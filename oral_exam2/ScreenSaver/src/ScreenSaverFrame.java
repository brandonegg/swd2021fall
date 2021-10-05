import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.util.Arrays;
import java.util.List;

public class ScreenSaverFrame extends JFrame implements KeyListener {

    private Character[] quitAppKeys = {'q'};

    private ScreenSaverComponent screenSaverComponent;

    public ScreenSaverFrame() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(false);
        setVisible(true);

        screenSaverComponent = new ScreenSaverComponent();
        add(screenSaverComponent);

        this.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        char key = e.getKeyChar();
        List<Character> keyList = Arrays.asList(quitAppKeys);

        if (keyList.contains(key)) {
            System.exit(0);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //Unused
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //Unused
    }
}

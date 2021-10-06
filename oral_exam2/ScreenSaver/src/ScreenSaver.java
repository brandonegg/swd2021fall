import java.awt.*;

/**
 * Main class for running the ScreenSaver application,
 * runs an instance of ScreenSaverFrame in fullscreen
 * @see  ScreenSaverFrame
 */
public class ScreenSaver {

    /**
     * Creates an instance of ScreenSaverFrame and runs this window in full screen.
     * @param args  input arguments
     * @see   ScreenSaverFrame
     */
    public static void main(String args[]) {
        GraphicsEnvironment graphics = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = graphics.getDefaultScreenDevice();

        ScreenSaverFrame ssFrame = new ScreenSaverFrame();
        device.setFullScreenWindow(ssFrame);
    }
}

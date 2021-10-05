import java.awt.*;

public class ScreenSaver {

    public static void main(String args[]) {
        GraphicsEnvironment graphics = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = graphics.getDefaultScreenDevice();

        ScreenSaverFrame ssFrame = new ScreenSaverFrame();
        device.setFullScreenWindow(ssFrame);
    }
}

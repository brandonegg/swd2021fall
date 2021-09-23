package components;

import javax.swing.*;
import java.awt.*;

public class StickfigureComponent extends JComponent {

    public StickfigureComponent() {}

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.green);
        g.fillRect(30,30,100,100);
    }

}

import java.awt.*;

public class Oval extends java.applet.Applet {
    public void paint(Graphics screen) {
        setBackground(Color.white);
        screen.setColor(Color.black);
        for (int i = 0; i <= 200; i += 20) {
            screen.drawLine(0, i, 200, i);
            screen.drawLine(i, 0, i, 200);
        }
        screen.setColor(Color.red);
        screen.fillOval(30, 30, 160, 160);
    }
}

import java.awt.*;
import java.util.*;

public class Watch extends javax.swing.JApplet {
    private Color butterscotch = new Color(255, 204, 102);
    private String lastTime = "";

    public void init() {
        setBackground(Color.black);
    }

    public void paint(Graphics screen) {
        Graphics2D screen2D = (Graphics2D)screen;
        Font type = new Font("Monospaced", Font.BOLD, 20);
        screen2D.setFont(type);
        GregorianCalendar day = new GregorianCalendar();
        String time = day.getTime().toString();
        screen2D.setColor(Color.black);
        screen2D.drawString(lastTime, 5, 25);
        screen2D.setColor(butterscotch);
        screen2D.drawString(time, 5, 25);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // do nothing
        }
        lastTime = time;
        repaint();
    }
}

import java.awt.*;
import java.util.*;

public class NewWatch extends javax.swing.JApplet {
    private Color butterscotch = new Color(255, 204, 102);
    private String lastTime = "";
    Color back;

    public void init() {
        String in = getParameter("background");
        back = Color.black;
        if (in != null) {
            try {
                back = Color.decode(in);
            } catch (NumberFormatException e) {
                showStatus("Bad parameter " + in);
            }
        }
        setBackground(back);
    }

    public void paint(Graphics screen) {
        Graphics2D screen2D = (Graphics2D)screen;
        Font type = new Font("Monospaced", Font.BOLD, 20);
        screen2D.setFont(type);
        GregorianCalendar day = new GregorianCalendar();
        String time = day.getTime().toString();
        screen2D.setColor(back);
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

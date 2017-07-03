import java.awt.*;
import java.util.*;

public class OldWatch extends java.applet.Applet {
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
        Font type = new Font("Monospaced", Font.BOLD, 20);
        screen.setFont(type);
        Date day = new Date();
        String time = day.toString();
        screen.setColor(back);
        screen.drawString(lastTime, 5, 25);
        screen.setColor(butterscotch);
        screen.drawString(time, 5, 25);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // do nothing
        }
        lastTime = time;
        repaint();
    }
}
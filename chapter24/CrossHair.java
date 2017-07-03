import java.awt.*;

public class CrossHair extends java.applet.Applet {
    String mark = "+";

    public void paint(Graphics screen) {
        Dimension appletWindow = size();
        int height = appletWindow.height;
        int width = appletWindow.width;
        screen.drawString(mark, width/2, height/2);
    }
}

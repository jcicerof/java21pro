import java.awt.*;

public class Plot extends java.applet.Applet {
    Label statLabel = new Label("Current Statistics:");
    Graph stats = new Graph();
    
    public void init() {
        BorderLayout border = new BorderLayout();
        setLayout(border);
        add("North", statLabel);
        add("Center", stats);
    }
}

class Graph extends java.awt.Canvas {
    int[] point = { 1, 10, 3, 5, 8, 7, 2, 2, 5, 9 };

    public void paint(Graphics screen) {
        for (int i = 0; i < 10; i++) {
            Color blueHue = new Color(0, 0, 255 - (i*20));
            screen.setColor(blueHue);
            screen.fillRect(20, i * 20, point[i] * 20, 17);
        }
    }
}

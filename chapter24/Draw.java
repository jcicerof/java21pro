import java.awt.*;

public class Draw extends java.applet.Applet {
    Button erase = new Button("Erase");
    DrawPanel canvas;

    public void init() {
        canvas = new DrawPanel(getImage(getCodeBase(), "faulkner.jpg"));
        BorderLayout bord = new BorderLayout();
        setLayout(bord);
        add(canvas, "Center");
        Panel commandPanel = new Panel();
        commandPanel.add(erase);
        add(commandPanel, "South");
    }

    public boolean action(Event evt, Object obj) {
        if (evt.target == erase) {
            canvas.numPoints = -1;
            canvas.repaint();
        }
        return true;
    }
}

class DrawPanel extends Panel {
    Image picture;
    int[] drawX = new int[1000];
    int[] drawY = new int[1000];
    int numPoints = -1;

    DrawPanel(Image inputImage) {
        picture = inputImage;
    }

    public void paint(Graphics screen) {
        screen.drawImage(picture, 0, 0, this);
        screen.setColor(Color.black);
        for (int i = 0; i <= numPoints; i++) {
            screen.fillOval(drawX[i]-3, drawY[i]-3, 6, 6);
        }
    }

    public void update(Graphics screen) {
        paint(screen);
    }

    public boolean mouseDown(Event evt, int x, int y) {
        if (numPoints < 1000) {
            numPoints++;
            drawX[numPoints] = x;
            drawY[numPoints] = y;
        }
        repaint();
        return true;
    }

    public boolean mouseDrag(Event evt, int x, int y) {
        mouseDown(evt, x, y);
        return true;
    }
}

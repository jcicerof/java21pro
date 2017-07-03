import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SoLong extends JFrame {
    public SoLong() {
        super("So Long");
        setSize(425, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        SoLongPane sl = new SoLongPane();
        Container content = getContentPane();
        content.add(sl);
        setVisible(true);
    }

    public static void main(String[] arguments) {
        SoLong frame = new SoLong();
    }

}

class SoLongPane extends JPanel {
    public void paintComponent(Graphics comp) {
        Graphics comp2D = (Graphics2D)comp;
        Font f = new Font("Monospaced", Font.BOLD, 18);
        FontMetrics fm = getFontMetrics(f);
        comp2D.setFont(f);
        String s = "So long, and thanks for all the fish.";
        int x = (getSize().width - fm.stringWidth(s)) / 2;
        int y = getSize().height / 2;
        comp2D.drawString(s, x, y);
    }
}


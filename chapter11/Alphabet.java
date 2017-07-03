import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Alphabet extends JFrame {
    JButton a = new JButton("Alibi");
    JButton b = new JButton("Burglar");
    JButton c = new JButton("Corpse");
    JButton d = new JButton("Deadbeat");
    JButton e = new JButton("Evidence");
    JButton f = new JButton("Fugitive");

    Alphabet() {
        super("Alphabet");
        setSize(360, 120);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel pane = new JPanel();
        FlowLayout lm = new FlowLayout(FlowLayout.LEFT);
        pane.setLayout(lm);
        pane.add(a);
        pane.add(b);
        pane.add(c);
        pane.add(d);
        pane.add(e);
        pane.add(f);
        setContentPane(pane);
        setVisible(true);
    }

    public static void main(String[] arguments) {
        JFrame frame = new Alphabet();
        frame.show();
    }
}

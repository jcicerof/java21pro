import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Border extends JFrame {
    JButton north = new JButton("North");
    JButton south = new JButton("South");
    JButton east = new JButton("East");
    JButton west = new JButton("West");
    JButton center = new JButton("Center");

    Border() {
        super("Border");
        setSize(240, 280);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel pane = new JPanel();
        pane.setLayout(new BorderLayout());
        pane.add("North", north);
        pane.add("South", south);
        pane.add("East", east);
        pane.add("West", west);
        pane.add("Center", center);
        setContentPane(pane);
    }

    public static void main(String[] arguments) {
        JFrame frame = new Border();
        frame.show();
    }
}

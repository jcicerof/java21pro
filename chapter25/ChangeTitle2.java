import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class ChangeTitle2 extends JFrame implements ActionListener {
    JButton b1 = new JButton("Rosencrantz");
    JButton b2 = new JButton("Guildenstern");

    public ChangeTitle2() {
        super("Title Bar");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        b1.setMnemonic('R');
        b2.setMnemonic('G');
        b1.setToolTipText("Change the title to Rosencrantz");
        b2.setToolTipText("Change the title to Guildenstern");
        b1.addActionListener(this);
        b2.addActionListener(this);
        JPanel pane = new JPanel();
        pane.add(b1);
        pane.add(b2);
        setContentPane(pane);
        pack();
        setVisible(true);
    }

    public static void main(String[] arguments) {
        JFrame frame = new ChangeTitle2();
    }

    public void actionPerformed(ActionEvent evt) {
        Object source = evt.getSource();
        if (source == b1)
            setTitle("Rosencrantz");
        else if (source == b2)
            setTitle("Guildenstern");
        repaint();
    }
}
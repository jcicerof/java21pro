import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class SelectItem extends JFrame implements ItemListener {
    BorderLayout bord = new BorderLayout();
    JTextField result = new JTextField(27);
    JComboBox pick = new JComboBox();

    public SelectItem() {
        super("Select Item");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pick.addItemListener(this);
        pick.addItem("Navigator");
        pick.addItem("Internet Explorer");
        pick.addItem("Opera");
        pick.setEditable(false);
        result.setHorizontalAlignment(SwingConstants.CENTER);
        result.setEditable(false);
        JPanel pane = new JPanel();
        pane.setLayout(bord);
        pane.add(result, "South");
        pane.add(pick, "Center");
        setContentPane(pane);
        pack();
        setVisible(true);
    }

    public static void main(String[] arguments) {
        JFrame frame = new SelectItem();
    }

    public void itemStateChanged(ItemEvent evt) {
        Object source = evt.getSource();
        if (source == pick) {
            Object newPick = evt.getItem();
            result.setText(newPick.toString() + " is the selection.");
        }
        repaint();
    }
}

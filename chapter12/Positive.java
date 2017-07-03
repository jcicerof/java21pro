import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Positive extends JFrame implements FocusListener {
    JTextField[] value = new JTextField[3];

    public Positive() {
        super("Enter Numbers");
        setSize(300, 60);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container pane = getContentPane();
        FlowLayout flow = new FlowLayout();
        pane.setLayout(flow);
        for (int i = 0; i < 3; i++) {
            value[i] = new JTextField("0", 5);
            value[i].addFocusListener(this);
            pane.add(value[i]);
        }
        setContentPane(pane);
        setVisible(true);
    }

    public static void main(String[] arguments) {
        JFrame frame = new Positive();
    }

    public void focusGained(FocusEvent evt) {
        checkValue(evt.getSource());
    }

    public void focusLost(FocusEvent evt) {
        checkValue(evt.getSource());
    }

    void checkValue(Object source) {
        JTextField field = (JTextField)source;
        try {
            int value = Integer.parseInt(field.getText());
            if (value < 0) {
                value = value * -1;
                field.setText("" + value);
            }
        } catch (NumberFormatException exc) {
            field.setText("0");
        }
    }
}

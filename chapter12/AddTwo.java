import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class AddTwo extends JFrame implements ActionListener {
    JTextField one = new JTextField("0", 5);
    JLabel plus = new JLabel("+");
    JTextField two = new JTextField("0", 5);
    JButton equals = new JButton("=");
    JTextField result = new JTextField("0", 5);

    public AddTwo() {
        super("Add Two Numbers");
        setSize(400, 60);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container pane = getContentPane();
        FlowLayout flow = new FlowLayout();
        pane.setLayout(flow);
        equals.addActionListener(this);
        pane.add(one);
        pane.add(plus);
        pane.add(two);
        pane.add(equals);
        pane.add(result);
        setContentPane(pane);
        setVisible(true);
    }

    public static void main(String[] arguments) {
        AddTwo frame = new AddTwo();
    }

    public void actionPerformed(ActionEvent evt) {
        try {
            int value1 = Integer.parseInt(one.getText());
            int value2 = Integer.parseInt(two.getText());
            result.setText("" + (value1 + value2));
        } catch (NumberFormatException exc) {
            one.setText("0");
            two.setText("0");
            result.setText("0");
        }
    }
}

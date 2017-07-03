import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class WellAdjusted extends JFrame implements AdjustmentListener {
    JTextField value = new JTextField("50", 30);
    JScrollBar bar = new JScrollBar(SwingConstants.HORIZONTAL,
        50, 10, 0, 100);

    public WellAdjusted() {
        super("Well Adjusted");
        setSize(350, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        bar.addAdjustmentListener(this);
        value.setHorizontalAlignment(SwingConstants.CENTER);
        value.setEditable(false);
        JPanel pane = new JPanel();
        pane.setLayout(new BorderLayout());
        pane.add(value, "Center");
        pane.add(bar, "South");
        setContentPane(pane);
    }

    public static void main(String[] arguments) {
        JFrame frame = new WellAdjusted();
        frame.show();
    }

    public void adjustmentValueChanged(AdjustmentEvent evt) {
        Object source = evt.getSource();
        if (source == bar) {
            int newValue = bar.getValue();
            value.setText("" + newValue);
        }
        repaint();
    }
}

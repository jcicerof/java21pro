import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.accessibility.*;

public class Combo extends JFrame {
    JComboBox job = new JComboBox();

    public Combo() {
        super("Combo");
        setSize(160, 190);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container pane = getContentPane();
        FlowLayout flo = new FlowLayout();
        job.addItem("Butcher");
        job.addItem("Baker");
        job.addItem("Candlestick maker");
        job.addItem("Fletcher");
        job.addItem("Fighter");
        job.addItem("Technical writer");
        job.setEditable(true);
        AccessibleContext ac = job.getAccessibleContext();
        ac.setAccessibleDescription(
           "Select a profession from a combo box.");
        ac.setAccessibleName("Profession");
        pane.setLayout(flo);
        pane.add(job);
        setContentPane(pane);
        setVisible(true);
        ComboSpy spy = new ComboSpy(job);
    }

    public static void main(String[] arguments) {
        Combo app = new Combo();
    }
}

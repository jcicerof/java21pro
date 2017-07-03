import java.awt.event.*;
import javax.swing.*; 

public class Slider extends JFrame {

    public Slider() {
        super("Slider");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JSlider pickNum = new JSlider(JSlider.HORIZONTAL, 0, 30, 5);
        pickNum.setMajorTickSpacing(10);
        pickNum.setMinorTickSpacing(1);
        pickNum.setPaintTicks(true);
        pickNum.setPaintLabels(true);
        JPanel pane = new JPanel();
        pane.add(pickNum);

        setContentPane(pane);
    }

    public static void main(String[] args) {
        Slider frame = new Slider();
        frame.pack();
        frame.setVisible(true);
    }
}

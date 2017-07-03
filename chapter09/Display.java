import javax.swing.*;

public class Display extends JFrame {
    public Display() {
        super("Display");
        Display ds = new Display();
        JLabel hello = new JLabel("Hello");
        JPanel pane = new JPanel();
        pane.add(hello);
        setContentPane(pane);
        pack();
        setVisible(true);
    }

    public static void main(String[] arguments) {
        Display ds = new Display();
    }
}

import javax.swing.*;

public class Icons extends JFrame {
    JButton[] buttons = new JButton[24];

    public Icons() {
        super("Icons");
        setSize(335, 318);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel pane = new JPanel();
        ImageIcon icon = new ImageIcon("3dman.gif");
        for (int i = 0; i < 24; i++) {
            buttons[i] = new JButton(icon);
            pane.add(buttons[i]);
        }
        setContentPane(pane);
        show();
    }

    public static void main(String[] arguments) {
        Icons ike = new Icons();
    }
}

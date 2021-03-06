import javax.swing.*;

public class Buttons extends JFrame {
    JButton abort = new JButton("Abort");
    JButton retry = new JButton("Retry");
    JButton fail = new JButton("Fail");
    
    public Buttons() {
        super("Buttons");
        setSize(80, 140);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel pane = new JPanel();
        pane.add(abort);
        pane.add(retry);
        pane.add(fail);
        setContentPane(pane);
}

    public static void main(String[] arguments) {
        Buttons rb = new Buttons();
        rb.show();
    }
}

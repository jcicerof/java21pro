import javax.swing.JWindow;

public class SimpleWindow extends JWindow {
    public SimpleWindow() {
        super();
        setBounds(250, 225, 300, 150);
    }

    public static void main(String[] arguments) {
        SimpleWindow sw = new SimpleWindow();
        sw.setVisible(true);
        for (int i = 0; i < 10000; i++)
            System.out.print(i + " ");
        sw.setVisible(false);
        System.exit(0);
    }

}

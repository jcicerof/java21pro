import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class MouseTest extends JFrame implements MouseListener {
    JLabel whichButton = new JLabel("Click a mouse button");

    public MouseTest() {
        super("Mouse Test");
        setSize(400, 325);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addMouseListener(this);
        JPanel pane = new JPanel();
        pane.add(whichButton);
        setContentPane(pane);
        pack();
        setVisible(true);
    }

    public void mouseClicked(MouseEvent evt){
        int button=evt.getModifiers();

        switch(button) {
            case MouseEvent.BUTTON1_MASK:
                whichButton.setText("Left Button");
                break;
            case MouseEvent.BUTTON2_MASK:
                whichButton.setText("Middle Button");
                break;
            case MouseEvent.BUTTON3_MASK:
                whichButton.setText("Right Button");
         }
    }

    public void mousePressed(MouseEvent evt) {
        // do nothing
    }

    public void mouseReleased(MouseEvent evt) {
        // do nothing
    }

    public void mouseEntered(MouseEvent evt) {
        // do nothing
    }

    public void mouseExited(MouseEvent evt) {
        // do nothing
    }

    public static void main(String[] arguments) {
        MouseTest frame = new MouseTest();
    }
}



import java.awt.*;
import javax.swing.*;

public class Crisis extends JFrame {
    JButton panicButton = new JButton("Panic");
    JButton dontPanicButton = new JButton("Don't Panic");
    JButton blameButton = new JButton("Blame Others");
    JButton mediaButton = new JButton("Notify the Media");
    JButton saveButton = new JButton("Save Yourself");

    public Crisis() {
        super("Crisis");
        setSize(308, 128);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container pane = getContentPane();
        FlowLayout flo = new FlowLayout();
        // set keyboard mnemonics for buttons
        panicButton.setMnemonic('P');
        dontPanicButton.setMnemonic('D');
        blameButton.setMnemonic('B');
        mediaButton.setMnemonic('N');
        saveButton.setMnemonic('S');
        // set tool tip text
        saveButton.setToolTipText("Move over, Women and Children: "
            + "That's My Lifeboat!");
        // add components to frame
        pane.setLayout(flo);
        pane.add(panicButton);
        pane.add(dontPanicButton);
        pane.add(blameButton);
        pane.add(mediaButton);
        pane.add(saveButton);
        setVisible(true);
    }

    public static void main(String[] arguments) {
        Crisis cr = new Crisis();
    }
}

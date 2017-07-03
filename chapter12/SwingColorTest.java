import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SwingColorTest extends JFrame {
    SwingColorControls RGBcontrols, HSBcontrols;
    JPanel swatch;

    public SwingColorTest() {
        super("Color Test");
        setSize(400, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel pane = new JPanel();
        GridLayout grid = new GridLayout(1, 3, 5, 15);
        pane.setLayout(grid);
        swatch = new JPanel();
        swatch.setBackground(Color.black);
        String[] rgbLabels = { "Red", "Green", "Blue" };
        RGBcontrols = new SwingColorControls(this, rgbLabels);
        String[] hsbLabels = { "Hue", "Saturation", "Brightness" };
        HSBcontrols = new SwingColorControls(this, hsbLabels);
        pane.add(swatch);
        pane.add(RGBcontrols);
        pane.add(HSBcontrols);
        setContentPane(pane);
        pack();
        setVisible(true);
    }

    public static void main(String[] arguments) {
        JFrame frame = new SwingColorTest();
    }

    void update(SwingColorControls control) {
        Color c;
        // get string values from text fields, convert to ints
        int[] value = new int[3];
        for (int i = 0; i < 3; i++) {
            value[i] = Integer.parseInt(control.setting[i].getText());
            if ( (value[i] < 0) || (value[i] > 255) ) {
                value[i] = 0;
                control.setting[i].setText("" + value[i]);
            }
        }
        if (control == RGBcontrols) {
            // RGB has changed, update HSB
            c = new Color(value[0], value[1], value[2]);

            // convert RGB values to HSB values
            float[] hsbValues = new float[3];
            float[] HSB = Color.RGBtoHSB(value[0], value[1], value[2],
                hsbValues);
            HSB[0] *= 360;
            HSB[1] *= 100;
            HSB[2] *= 100;

            // reset HSB fields
            for (int i = 0; i < 3; i++) {
               HSBcontrols.setting[i].setText(String.valueOf( (int)HSB[i]) );
            }
        } else {
            // HSB has changed, update RGB
            c = Color.getHSBColor( (float)value[0] / 360,
                (float)value[1] / 100, (float)value[2] / 100 );

            // reset RGB fields
            RGBcontrols.setting[0].setText( String.valueOf(c.getRed()) );
            RGBcontrols.setting[1].setText( String.valueOf(c.getGreen()) );
            RGBcontrols.setting[2].setText( String.valueOf(c.getBlue()) );
        }

        // update swatch
        swatch.setBackground(c);
        swatch.repaint();
    }
}

class SwingColorControls extends JPanel
    implements ActionListener, FocusListener {

    SwingColorTest frame;
    JTextField[] setting = new JTextField[3];

    SwingColorControls(SwingColorTest parent, String[] label) {

        frame = parent;
        GridLayout cGrid = new GridLayout(3, 2, 10, 10);
        setLayout(cGrid);
        for (int i = 0; i < 3; i++) {
            setting[i] = new JTextField("0");
            setting[i].addFocusListener(this);
            setting[i].addActionListener(this);
            JLabel settingLabel = new JLabel(label[i], JLabel.RIGHT);
            add(settingLabel);
            add(setting[i]);
        }
        setVisible(true);
    }

    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() instanceof JTextField)
            frame.update(this);
    }

    public void focusLost(FocusEvent evt) {
        frame.update(this);
    }

    public void focusGained(FocusEvent evt) { }

}

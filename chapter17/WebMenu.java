import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class WebMenu extends JApplet implements ActionListener {
    WebButton[] choices = new WebButton[3];

    public void init() {
        choices[0] = new WebButton("Obscure Store",
            "http://www.obscurestore.com/");
        choices[1] = new WebButton("Need to Know",
            "http://www.ntk.net/");
        choices[2] = new WebButton("Bleat",
            "http://www.lileks.com/bleats");

        FlowLayout flo = new FlowLayout();
        Container pane = getContentPane();
        pane.setLayout(flo);
        for (int i = 0; i < choices.length; i++) {
            choices[i].addActionListener(this);
            pane.add(choices[i]);
        }
        setContentPane(pane);
    }

    public void actionPerformed(ActionEvent evt) {
        WebButton clicked = (WebButton)evt.getSource();
        try {
            URL load = new URL(clicked.address);
            getAppletContext().showDocument(load);
        } catch (MalformedURLException e) {
            showStatus("Bad URL:" + clicked.address);
        }
    }
}

class WebButton extends JButton {
    String address;

    WebButton(String iLabel, String iAddress) {
        super(iLabel);
        address = iAddress;
    }
}

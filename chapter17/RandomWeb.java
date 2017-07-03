import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RandomWeb extends JApplet implements ActionListener {
    WebButton[] choices = new WebButton[10];

    public void init() {
        FlowLayout flo = new FlowLayout();
        getContentPane().setLayout(flo);
        for (int i = 0; i < 10; i++) {
            StringBuffer randomDomain = new StringBuffer();
            for (int j = 0; j < 3; j++) {
                // Create a random number from 0 to 25
                double tempValue = Math.random() * 26 - 1;
                int randomNumber = (int) Math.floor( tempValue ) + 1;
                int firstLetter = (int)'a';
                char letter = (char) (firstLetter + randomNumber);
                randomDomain.append(letter);
            }
            String address = new String("http://www." + randomDomain.toString() +
                ".com/");
            choices[i] = new WebButton("WWW." + randomDomain.toString().toUpperCase() +
                ".COM", address);
            choices[i].addActionListener(this);
            getContentPane().add(choices[i]);
        }
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






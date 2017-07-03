import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GetAddress extends JFrame 
    implements ActionListener {

    JTextField name = new JTextField(27);
    JTextField address1 = new JTextField(27);
    JTextField address2 = new JTextField(27);
    JTextField city = new JTextField(13);
    JTextField state = new JTextField(4);
    JTextField zip = new JTextField(7);
    JButton saveButton = new JButton("Save");
    JButton exitButton = new JButton("Exit");

    public GetAddress() {
        super("Enter Your Address");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        FlowLayout flow = new FlowLayout(FlowLayout.RIGHT);
        FlowLayout center = new FlowLayout();
        GridLayout grid = new GridLayout(5, 1);
        Container pane = getContentPane();
        pane.setLayout(grid);

        // set up frame
        JPanel row1 = new JPanel();
        row1.setLayout(flow);
        JLabel nameLabel = new JLabel("Name: ");
        nameLabel.setLabelFor(name);
        row1.add(nameLabel);
        row1.add(name);
        pane.add(row1);

        JPanel row2 = new JPanel();
        row2.setLayout(flow);
        JLabel address1Label = new JLabel("Address 1: ");
        address1Label.setLabelFor(address1);
        row2.add(address1Label);
        row2.add(address1);
        pane.add(row2);

        JPanel row3 = new JPanel();
        row3.setLayout(flow);
        JLabel address2Label = new JLabel("Address 2: ");
        address2Label.setLabelFor(address2);
        row3.add(address2Label);
        row3.add(address2);
        pane.add(row3);

        JPanel row4 = new JPanel();
        row4.setLayout(center);
        JLabel cityLabel = new JLabel("City: ");
        cityLabel.setLabelFor(city);
        row4.add(cityLabel);
        row4.add(city);
        JLabel stateLabel = new JLabel("State: ");
        stateLabel.setLabelFor(state);
        row4.add(stateLabel);
        row4.add(state);
        JLabel zipLabel = new JLabel("ZIP: ");
        zipLabel.setLabelFor(zip);
        row4.add(zipLabel);
        row4.add(zip);
        pane.add(row4);

        JPanel row5 = new JPanel();
        row5.setLayout(flow);
        saveButton.addActionListener(this);
        saveButton.setMnemonic('s');
        row5.add(saveButton);
        exitButton.addActionListener(this);
        exitButton.setMnemonic('x');
        row5.add(exitButton);
        pane.add(row5);

        setContentPane(pane);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent evt) {
        Object source = evt.getSource();
        if (source == saveButton) {
            String inName = "Name: " + name.getText();
            String inAddress1 = "Address 1: " + address1.getText();
            String inAddress2 = "Address 2: " + address2.getText();
            String inCity = "City: " + city.getText();
            String inState = "State: " + state.getText();
            String inZip = "Zip: " + zip.getText();
            try {
                File data = new File("address.txt");
                FileWriter fw = new FileWriter(data);
                BufferedWriter out = new BufferedWriter(fw);
                out.write(inName, 0, inName.length());
                out.newLine();
                out.write(inAddress1, 0, inAddress1.length());
                out.newLine();
                out.write(inAddress2, 0, inAddress2.length());
                out.newLine();
                out.write(inCity, 0, inCity.length());
                out.newLine();
                out.write(inState, 0, inState.length());
                out.newLine();
                out.write(inZip, 0, inZip.length());
                out.newLine();
                out.close();
                saveButton.setEnabled(false);
            } catch (IOException e) {
                System.out.println("Error -- " + e.toString());
            } catch (SecurityException se) {
                System.out.println("Error -- " + se.toString());
            }
        } else if (source == exitButton)
            System.exit(0);
    }

    public static void main(String[] arguments) {
        GetAddress ga = new GetAddress();
    }
}

        
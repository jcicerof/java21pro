import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;

public class PeopleBook extends JFrame implements ActionListener{
    JTextField name = new JTextField(40);
    JTextField address1 = new JTextField(40);
    JTextField address2 = new JTextField(40);
    JTextField phone = new JTextField(40);
    JTextField email = new JTextField(40);
    JButton saveButton = new JButton("Save");
    
    public PeopleBook() {
        super("Add People");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container pane = getContentPane();
        GridLayout grid = new GridLayout(6, 1);
        pane.setLayout(grid);
        JPanel[] panel = new JPanel[6];
        FlowLayout floRight = new FlowLayout(FlowLayout.RIGHT);
        for (int i = 0; i < panel.length; i++) {
            panel[i] = new JPanel();
            panel[i].setLayout(floRight);
        }
        JLabel nameLabel = new JLabel("Name: ");
        panel[0].add(nameLabel);
        panel[0].add(name);
        JLabel address1Label = new JLabel("Address Line 1: ");
        panel[1].add(address1Label);
        panel[1].add(address1);
        JLabel address2Label = new JLabel("Address Line 2: ");
        panel[2].add(address2Label);
        panel[2].add(address2);
        JLabel phoneLabel = new JLabel("Phone: ");
        panel[3].add(phoneLabel);
        panel[3].add(phone);
        JLabel emailLabel = new JLabel("E-mail: ");
        panel[4].add(emailLabel);
        panel[4].add(email);
        saveButton.addActionListener(this);
        panel[5].add(saveButton);
        for (int i = 0; i < panel.length; i++)
            pane.add(panel[i]);
        setContentPane(pane);
        pack();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == saveButton) {
            storePerson();
        }
    }
    
    private void storePerson() {
        String datasource = "jdbc:JDataConnect://127.0.0.1/Presidents";
        try {
            Class.forName("JData2_0.sql.$Driver");
            Connection conn = DriverManager.getConnection(
                datasource, "", "");
            PreparedStatement pstmt = conn.prepareStatement(
                "INSERT INTO Contacts VALUES(?, ?, ?, ?, ?)");
            pstmt.setString( 1, stripQuotes(name.getText()) );
            pstmt.setString( 2, stripQuotes(address1.getText()) );
            pstmt.setString( 3, stripQuotes(address2.getText()) );           
            pstmt.setString( 4, stripQuotes(phone.getText()) );
            pstmt.setString( 5, stripQuotes(email.getText()) );
            int rows = pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, rows + " rows updated.");
            name.setText("");
            address1.setText("");
            address2.setText("");
            phone.setText("");
            email.setText("");
            conn.close();
        } catch (SQLException sqe) {
            System.out.println("SQL Error: " + sqe.getMessage());
        } catch (ClassNotFoundException cnfe) {
            System.out.println(cnfe.getMessage());
        }
    }

    private String stripQuotes(String input) {
        StringBuffer output = new StringBuffer();
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) != '\"') {
                output.append(input.charAt(i));
            }
        }
        return output.toString();
    }

    public static void main(String[] arguments) {
        new PeopleBook();
    }      
}
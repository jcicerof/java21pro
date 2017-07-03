import java.io.*;
import java.util.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import org.apache.xmlrpc.*;

public class NumberClient extends JFrame implements ActionListener {
    JTextField field1 = new JTextField(7);
    JTextField field2 = new JTextField(7);
    JButton multiply = new JButton("Multiply");
    JTextField result = new JTextField(40);    

    public NumberClient() {
        super("Number Client");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container pane = getContentPane();
        GridLayout grid = new GridLayout(4, 1);
        pane.setLayout(grid);
        // build panel 1
        JPanel panel1 = new JPanel();
        JLabel label1 = new JLabel("Number 1: ");
        panel1.add(label1);
        panel1.add(field1);
        pane.add(panel1);
        // build panel 2
        JPanel panel2 = new JPanel();
        JLabel label2 = new JLabel("Number 2: ");
        panel2.add(label2);
        panel2.add(field2);
        pane.add(panel2);
        // build button panel
        JPanel panel3 = new JPanel();
        multiply.addActionListener(this);
        panel3.add(multiply);
        pane.add(panel3);
        // build result panel
        JPanel panel4 = new JPanel();
        JLabel label4 = new JLabel("Result: " );
        panel4.add(label4);
        panel4.add(result);
        pane.add(panel4);
        // build content pane
        setContentPane(pane);
        pack();
        setVisible(true);
    }
        
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == multiply) {
            try {
                double d1 = Double.parseDouble(field1.getText());
                double d2 = Double.parseDouble(field2.getText());
                double res = getResult(d1, d2);
                result.setText("" + res);
            } catch (NumberFormatException nfe) {
                result.setText("Error: Bad input");
            } catch (IOException ioe) {
                result.setText("IO Exception: " + ioe.getMessage());
            } catch (XmlRpcException xre) {
                result.setText("XML-RPC Exception: " + xre.getMessage());
            }
        }
    }
    
    public double getResult(double d1, double d2)
        throws IOException, XmlRpcException {
            
        // Create the client
        XmlRpcClient client = new XmlRpcClient(
            "http://localhost:5565/");
        // Create the parameters for the request
        Vector params = new Vector();
        params.addElement(new Double(d1));
        params.addElement(new Double(d2));
        // Send the request and get the response
        Double result = (Double) client.execute("number.multiplyNumbers", params);
        return result.doubleValue();
    }
    
    public static void main(String[] arguments) {
        new NumberClient();
    }
}

import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
import javax.swing.*;

public class PageData extends JFrame implements ActionListener,
    Runnable {

    Thread runner;
    String[] headers = { "Content-Length", "Content-Type",
        "Date", "Public", "Expires", "Last-Modified",
        "Server" };
            
    URL page;
    JTextField url;
    JLabel[] headerLabel = new JLabel[7];
    JTextField[] header = new JTextField[7];
    JButton readPage, clearPage, quitLoading;
    JLabel status;

    public PageData() {
        super("Page Data");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel pane = new JPanel();
        pane.setLayout(new GridLayout(10, 1));
        
        JPanel first = new JPanel();
        first.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JLabel urlLabel = new JLabel("URL:");
        url = new JTextField(22);
        urlLabel.setLabelFor(url);
        first.add(urlLabel);
        first.add(url);
        pane.add(first);
        
        JPanel second = new JPanel();
        second.setLayout(new FlowLayout());
        readPage = new JButton("Read Page");
        clearPage = new JButton("Clear Fields");
        quitLoading = new JButton("Quit Loading");
        readPage.setMnemonic('r');
        clearPage.setMnemonic('c');
        quitLoading.setMnemonic('q');
        readPage.setToolTipText("Begin Loading the Web Page");
        clearPage.setToolTipText("Clear All Header Fields Below");
        quitLoading.setToolTipText("Quit Trying to Load the Web Page");
        readPage.setEnabled(true);
        clearPage.setEnabled(false);
        quitLoading.setEnabled(false);
        readPage.addActionListener(this);
        clearPage.addActionListener(this);
        quitLoading.addActionListener(this);
        second.add(readPage);
        second.add(clearPage);
        second.add(quitLoading);
        pane.add(second);
 
        JPanel[] row = new JPanel[7];
        for (int i = 0; i < 7; i++) {
            row[i] = new JPanel();
            row[i].setLayout(new FlowLayout(FlowLayout.RIGHT));
            headerLabel[i] = new JLabel(headers[i]+":");
            header[i] = new JTextField(22);
            headerLabel[i].setLabelFor(header[i]);
            row[i].add(headerLabel[i]);
            row[i].add(header[i]);
            pane.add(row[i]);
        }

        JPanel last = new JPanel();
        last.setLayout(new FlowLayout(FlowLayout.LEFT));
        status = new JLabel("Enter a URL address to check.");
        last.add(status);
        pane.add(last);
        
        setContentPane(pane);
    }

    public void actionPerformed(ActionEvent evt) {
        Object source = evt.getSource();
        if (source == readPage) {
            try {
                page = new URL(url.getText());
                if (runner == null) {
                    runner = new Thread(this);
                    runner.start();
                }
                quitLoading.setEnabled(true);
                readPage.setEnabled(false);
            }
            catch (MalformedURLException e) {
                status.setText("Bad URL: " + page);
            }
        } else if (source == clearPage) {
            for (int i = 0; i < 7; i++)
                header[i].setText("");
            quitLoading.setEnabled(false);
            readPage.setEnabled(true);
            clearPage.setEnabled(false);
        } else if (source == quitLoading) {
            runner = null;
            url.setText("");
            quitLoading.setEnabled(false);
            readPage.setEnabled(true);
            clearPage.setEnabled(false);
        }
    }

    public void run() {
        URLConnection conn = null;
        try {
            conn = this.page.openConnection();
            conn.connect();
            status.setText("Connection opened ...");
            for (int i = 0; i < 7; i++)
                header[i].setText(conn.getHeaderField(headers[i]));
            quitLoading.setEnabled(false);
            clearPage.setEnabled(true);
            status.setText("Done");
            runner = null;
        }
        catch (IOException e) {
            status.setText("IO Error:" + e.getMessage());
        }                                              
    }

    public static void main(String[] arguments) {
        PageData frame = new PageData();
        frame.pack();
        frame.setVisible(true);
    }
}

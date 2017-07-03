import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;

public class GetFile {
    public static void main(String[] arguments) {
        if (arguments.length == 1) {
            PageFrame page = new PageFrame(arguments[0]);
            page.show();
        } else
            System.out.println("Usage: java GetFile url");
    }
}

class PageFrame extends JFrame {
    JTextArea box = new JTextArea("Getting data ...");
    URL page;

    public PageFrame(String address) {
        super(address);
        setSize(600, 300);
        JScrollPane pane = new JScrollPane(box);
        getContentPane().add(pane);
        WindowListener l = new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                System.exit(0);
            }
        };
        addWindowListener(l);

        try {
            page = new URL(address);
            getData(page);
        } catch (MalformedURLException e) {
            System.out.println("Bad URL: " + address);
        }
    }

    void getData(URL url) {
        URLConnection conn = null;
        InputStreamReader in;
        BufferedReader data;
        String line;
        StringBuffer buf = new StringBuffer();
        try {
            conn = this.page.openConnection();
            conn.connect();
            box.setText("Connection opened ...");

            in = new InputStreamReader(conn.getInputStream());
            data = new BufferedReader(in);

            box.setText("Reading data ...");
            while ((line = data.readLine()) != null)
                buf.append(line + "\n");

            box.setText(buf.toString());
        } catch (IOException e) {
            System.out.println("IO Error:" + e.getMessage());
        }
    }

}

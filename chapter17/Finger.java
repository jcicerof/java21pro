import java.io.*;
import java.net.*;
import java.util.*;

public class Finger {
    public static void main(String[] arguments) {
        String user;
        String host;
        if ((arguments.length == 1) && (arguments[0].indexOf("@") > -1)) {
            StringTokenizer split = new StringTokenizer(arguments[0],
                "@");
            user = split.nextToken();
            host = split.nextToken();
        } else {
            System.out.println("Usage: java Finger user@host");
            return;
        }
        try {
            Socket digit = new Socket(host, 79);
            digit.setSoTimeout(20000);
            PrintStream out = new PrintStream(digit.getOutputStream());
            out.print(user + "\015\012");
            BufferedReader in = new BufferedReader(
                new InputStreamReader(digit.getInputStream()));
            boolean eof = false;
            while (!eof) {
                String line = in.readLine();
                if (line != null)
                    System.out.println(line);
                else
                    eof = true;
            }
            digit.close();
        } catch (IOException e) {
            System.out.println("IO Error:" + e.getMessage());
        }
    }
}

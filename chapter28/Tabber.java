import java.io.*;
import java.util.regex.*;

public class Tabber {
    public static void main(String[] arguments) {
        Pattern tabs = Pattern.compile("\t");
        try {
            FileReader file = new FileReader("tabdata.txt");
            BufferedReader buff = new BufferedReader(file);
            boolean eof = false;
            while (!eof) {
                String line = buff.readLine();
                if (line == null)
                    eof = true;
                else {
                    String tokens[] = line.split("\t", 3);
                    if (tokens.length > 2) {
                        String name = tokens[0];
                        String url = tokens[1];
                        String topic = tokens[2];
                        System.out.println("Name: " + name + "\n"
                            + "URL: " + url + "\n"
                            + "Topic: " + topic + "\n");
                    }
                }
            }
            buff.close();
        } catch (IOException e) {
            System.out.println("Error -- " + e.toString());
        }
    }
}
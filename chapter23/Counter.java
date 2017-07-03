package counter;

import java.io.*;
import java.util.*;

public class Counter {
    private int count;
    private String filepath;

    public Counter(String inFilepath) {
        count = 0;
        filepath = inFilepath;
    }

    public int getCount() {
        try {
            File countFile = new File(filepath);
            FileReader file = new FileReader(countFile);
            BufferedReader buff = new BufferedReader(file);
            String current = buff.readLine();
            count = Integer.parseInt(current);
            buff.close();
        } catch (IOException e) {
            // do nothing
        } catch (NumberFormatException nfe) {
            // do nothing
        }
        return count;
    }

    public void setCount(int newCount) {
        count = newCount;
        try {
            File countFile = new File(filepath);
            FileWriter file = new FileWriter(countFile);
            BufferedWriter buff = new BufferedWriter(file);
            String output = "" + newCount;
            buff.write(output, 0, output.length());
            buff.close();
        } catch (IOException e) {
            // do nothing
        }
    }
}

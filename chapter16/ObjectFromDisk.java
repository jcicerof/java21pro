import java.io.*;
import java.util.*;

public class ObjectFromDisk {
    public static void main(String[] arguments) {
        try {
            FileInputStream fi = new FileInputStream(
                "message.obj");
            ObjectInputStream oi = new ObjectInputStream(fi);
            Message mess = (Message) oi.readObject();
            System.out.println("Message:\n");
            System.out.println("From: " + mess.from);
            System.out.println("To: " + mess.to);
            System.out.println("Date: " + mess.when + "\n");
            for (int i = 0; i < mess.lineCount; i++)
                System.out.println(mess.text[i]);
            oi.close();
        } catch (Exception e) {
            System.out.println("Error -- " + e.toString());
        }
    }
}

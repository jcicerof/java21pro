import java.io.*;
import java.util.*;

public class NewObjectToDisk {
    public static void main(String[] arguments) {
        try {
            Class messClass = Message.class;
            Message mess = (Message)messClass.newInstance();
            String author = "Sam Wainwright, London";
            String recipient = "George Bailey, Bedford Falls";
            String[] letter = { "Mr. Gower cabled you need cash. Stop.",
                "My office instructed to advance you up to twenty-five",
                "thousand dollars. Stop. Hee-haw and Merry Christmas." };
            Date now = new Date();
            mess.writeMessage(author, recipient, now, letter);
                FileOutputStream fo = new FileOutputStream(
                    "NewMessage.obj");
                ObjectOutputStream oo = new ObjectOutputStream(fo);
                oo.writeObject(mess);
                oo.close();
                System.out.println("Object created successfully.");
        } catch (IOException e) {
            System.out.println("Error -- " + e.toString());
        } catch (InstantiationException i) {
            System.out.println("Error creating class -- " + i.toString());
        } catch (IllegalAccessException l) {
            System.out.println("No access -- " + l.toString());
        }
    }
}

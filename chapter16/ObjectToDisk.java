import java.io.*;
import java.util.*;

public class ObjectToDisk {
    public static void main(String[] arguments) {
        Message mess = new Message();
        String author = "Sam Wainwright, London";
        String recipient = "George Bailey, Bedford Falls";
        String[] letter = { "Mr. Gower cabled you need cash. Stop.",
            "My office instructed to advance you up to twenty-five",
            "thousand dollars. Stop. Hee-haw and Merry Christmas." };
        Date now = new Date();
        mess.writeMessage(author, recipient, now, letter);
        try {
            FileOutputStream fo = new FileOutputStream(
                "Message.obj");
            ObjectOutputStream oo = new ObjectOutputStream(fo);
            oo.writeObject(mess);
            oo.close();
            System.out.println("Object created successfully.");
        } catch (IOException e) {
            System.out.println("Error -- " + e.toString());
        }
    }
}

class Message implements Serializable {
    int lineCount;
    String from, to;
    Date when;
    String[] text;

    void writeMessage(String inFrom,
        String inTo,
        Date inWhen,
        String[] inText) {

        text = new String[inText.length];
        for (int i = 0; i < inText.length; i++)
            text[i] = inText[i];
        lineCount = inText.length;
        to = inTo;
        from = inFrom;
        when = inWhen;
    }
}

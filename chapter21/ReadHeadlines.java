import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import java.io.*;

public class ReadHeadlines extends DefaultHandler {

    public static void main(String[] arguments) {
        if (arguments.length > 0) {
            ReadHeadlines read = new ReadHeadlines(arguments[0]);
        } else {
            System.out.println("Usage: java ReadHeadlines filename");
        }
    }

    public ReadHeadlines(String libFile) {
        File input = new File(libFile);
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setValidating(true);
        try {
            SAXParser sax = factory.newSAXParser();
            sax.parse(input, new HeadlineHandler() );
        } catch (ParserConfigurationException pce) {
            System.out.println("Could not create that parser.");
            System.out.println(pce.getMessage());
        } catch (SAXException se) {
            System.out.println("Problem with the SAX parser.");
            System.out.println(se.getMessage());
        } catch (IOException ioe) {
            System.out.println("Error reading file.");
            System.out.println(ioe.getMessage());
        }
    }
}

class HeadlineHandler extends DefaultHandler {
    static int READING_TITLE = 2;
    static int READING_LINK = 3;
    static int READING_DESCRIPTION = 4;
    static int READING_GUID = 5;
    static int READING_NOTHING = 0;
    boolean readingItems = false;
    int currentActivity;
    String headline = "";

    HeadlineHandler() {
        super();
    }

    public void startElement(String uri, String localName,
        String qName, Attributes attributes) {
    
        currentActivity = READING_NOTHING;
        if (qName.equals("item"))
            readingItems = true;
        if (qName.equals("link"))
            currentActivity = READING_LINK;
        if (qName.equals("description"))
            currentActivity = READING_DESCRIPTION;
        if (qName.equals("guid"))
            currentActivity = READING_GUID;
        // The check for readingItems makes sure the title inside
        // the channel element is ignored.
        if ( (readingItems) & (qName.equals("title")) )
            currentActivity = READING_TITLE;
    }

    public void characters(char[] ch, int start, int length) {
        String value = new String(ch, start, length);
        // The call to trim() prevents the whitespace around an
        // element from being added.
        if (currentActivity == READING_TITLE)
            headline = headline + value.trim();
   }

   public void endElement(String uri, String localName, String qName) {
       if (qName.equals("item")) {
           System.out.println(headline);
           headline = "";
       }
    }                    
}
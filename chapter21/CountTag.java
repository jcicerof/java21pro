import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import java.io.*;

public class CountTag extends DefaultHandler {

    public static void main(String[] arguments) {
        if (arguments.length > 1) {
            CountTag ct = new CountTag(arguments[0], arguments[1]);
        } else {
            System.out.println("Usage: java CountTag filename tagName");
        }
    }

    CountTag(String xmlFile, String tagName) {
        File input = new File(xmlFile);
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setValidating(false);
        try {
            SAXParser sax = factory.newSAXParser();
            CountTagHandler cth = new CountTagHandler(tagName);
            sax.parse(input, cth);
            System.out.println("The " + cth.tag + " tag appears "
                + cth.count + " times.");           
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

class CountTagHandler extends DefaultHandler {
    String tag;
    int count = 0;

    CountTagHandler(String tagName) {
        super();
        tag = tagName;
    }

    public void startElement(String uri, String localName,
        String qName, Attributes attributes) {
    
        if (qName.equals(tag))
            count++;
    }
}

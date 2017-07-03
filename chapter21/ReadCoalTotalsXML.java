import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import java.io.*;

public class ReadCoalTotalsXML extends DefaultHandler {

    public static void main(String[] arguments) {
        ReadCoalTotalsXML read = new ReadCoalTotalsXML();
    }

    ReadCoalTotalsXML() {
        String coalFile = "CoalTotals.xml";
        File input = new File(coalFile);
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setValidating(true);
        try {
            SAXParser sax = factory.newSAXParser();
            sax.parse(input, new CoalTotalsXMLHandler() );
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

class CoalTotalsXMLHandler extends DefaultHandler {
    static int READING_FIPS = 1;
    static int READING_COUNTRY = 2;
    static int READING_YEAR = 3;
    static int READING_ANTHRACITE_PRODUCTION = 4;
    static int READING_NOTHING = 0;
    int currentActivity = READING_NOTHING;
    CoalTotalsRecord coalRecord = new CoalTotalsRecord();

    CoalTotalsXMLHandler() {
        super();
    }

    public void startElement(String uri, String localName,
        String qName, Attributes attributes) {
    
        if (qName.equals("fips"))
            currentActivity = READING_FIPS;
        else if (qName.equals("country"))
            currentActivity = READING_COUNTRY;
        else if (qName.equals("year"))
            currentActivity = READING_YEAR;
        else if (qName.equals("anthraciteProduction"))
            currentActivity = READING_ANTHRACITE_PRODUCTION;
        else
            currentActivity = READING_NOTHING;
    }

    public void characters(char[] ch, int start, int length) {
        String value = new String(ch, start, length);
        if (currentActivity == READING_FIPS)
            coalRecord.fips = value;
        if (currentActivity == READING_COUNTRY)
            coalRecord.country = value;
        if (currentActivity == READING_YEAR)
            coalRecord.year = value;
        if (currentActivity == READING_ANTHRACITE_PRODUCTION)
            coalRecord.anthraciteProduction = value;
   }

   public void endElement(String uri, String localName, String qName) {
       if (qName.equals("record")) {
           System.out.println("\nFIPS: " + coalRecord.fips);
           System.out.println("Country: " + coalRecord.country);
           System.out.println("Year: " + coalRecord.year);
           System.out.println("Anthracite Production: "
               + coalRecord.anthraciteProduction);
           coalRecord = new CoalTotalsRecord();
       }
    }                    
}

class CoalTotalsRecord {
    String fips;
    String country;
    String year;
    String anthraciteProduction;
}

import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import java.io.*;

public class ReadLibrary extends DefaultHandler {

    public static void main(String[] arguments) {
        if (arguments.length > 0) {
            ReadLibrary read = new ReadLibrary(arguments[0]);
        } else {
            System.out.println("Usage: java ReadLibrary filename");
        }
    }

    ReadLibrary(String libFile) {
        File input = new File(libFile);
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setValidating(true);
        try {
            SAXParser sax = factory.newSAXParser();
            sax.parse(input, new LibraryHandler() );
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

class LibraryHandler extends DefaultHandler {
    static int READING_TITLE = 1;
    static int READING_AUTHOR = 2;
    static int READING_PUBLISHER = 3;
    static int READING_PUBLICATION_DATE = 4;
    static int READING_SUBJECT = 5;
    static int READING_REVIEW = 6;
    static int READING_NOTHING = 0;
    int currentActivity = READING_NOTHING;
    Book libraryBook = new Book();

    LibraryHandler() {
        super();
    }

    public void startElement(String uri, String localName,
        String qName, Attributes attributes) {
    
        if (qName.equals("Title"))
            currentActivity = READING_TITLE;
        else if (qName.equals("Author"))
            currentActivity = READING_AUTHOR;
        else if (qName.equals("Publisher"))
            currentActivity = READING_PUBLISHER;
        else if (qName.equals("PublicationDate"))
            currentActivity = READING_PUBLICATION_DATE;
        else if (qName.equals("Subject"))
            currentActivity = READING_SUBJECT;
        else if (qName.equals("Review"))
            currentActivity = READING_REVIEW;

        if (currentActivity == READING_PUBLICATION_DATE) {
            libraryBook.isbn = attributes.getValue("isbn");
            libraryBook.edition = attributes.getValue("edition");
        }
    }

    public void characters(char[] ch, int start, int length) {
        String value = new String(ch, start, length);
        if (currentActivity == READING_TITLE)
            libraryBook.title = value;
        if (currentActivity == READING_AUTHOR)
            libraryBook.author = value;
        if (currentActivity == READING_PUBLISHER)
            libraryBook.publisher = value;
        if (currentActivity == READING_PUBLICATION_DATE)
            libraryBook.publicationDate = value;
        if (currentActivity == READING_SUBJECT)
            libraryBook.subject = value;
        if (currentActivity == READING_REVIEW)
            libraryBook.review = value;
   }

   public void endElement(String uri, String localName, String qName) {
       if (qName.equals("Book")) {
           System.out.println("\nTitle: " + libraryBook.title);
           System.out.println("Author: " + libraryBook.author);
           System.out.println("Publisher: " + libraryBook.publisher);
           System.out.println("Publication Date: "
               + libraryBook.publicationDate);
           System.out.println("Edition: " + libraryBook.edition);
           System.out.println("ISBN: " + libraryBook.isbn);
           System.out.println("Review: " + libraryBook.review);
           libraryBook = new Book();
       }
    }                    
}

class Book {
    String title;
    String author;
    String publisher;
    String publicationDate;
    String edition;
    String isbn;
    String subject;
    String review;
}

import java.io.IOException;
import org.apache.xmlrpc.WebServer;
import org.apache.xmlrpc.XmlRpc;

public class NumberServer {
    public static void main(String[] arguments) {
        if (arguments.length < 1) {
            System.out.println("Usage: java NumberServer [port]");
            System.exit(0);
         }
         try {
             startServer(arguments[0]);
         } catch (IOException ioe) {
             System.out.println("Server error: " +
                 ioe.getMessage());
         }
    }

    public static void startServer(String portString) throws IOException {
        // Start the server
        int port = Integer.parseInt(portString);
        System.out.println("Starting Number server ...");
        WebServer server = new WebServer(port);

        // Register the handler
        NumberHandler num = new NumberHandler();
        server.addHandler( "number", num);
        System.out.println("Accepting requests ...");
    }
}
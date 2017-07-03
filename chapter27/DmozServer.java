import java.io.IOException;
import org.apache.xmlrpc.WebServer;
import org.apache.xmlrpc.XmlRpc;

public class DmozServer {
    public static void main(String[] arguments) {
        if (arguments.length < 1) {
            System.out.println("Usage: java DmozServer [port]");
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
        System.out.println("Starting Dmoz server ...");
        WebServer server = new WebServer(port);

        // Register the handler
        DmozHandler odp = new DmozHandler();
        server.addHandler( "dmoz", odp);
        System.out.println("Accepting requests ...");
    }
}

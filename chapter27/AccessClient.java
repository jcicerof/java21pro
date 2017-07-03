import java.io.*;
import java.util.*;
import java.net.*;
import org.apache.xmlrpc.*;

public class AccessClient {
    String url;
    String title;
    String description;

    public static void main(String arguments[]) {
        AccessClient client = new AccessClient();
        try {
            Vector response = client.getRandomSite();
            // Report the results
            if (response.size() > 0) {
                client.url = response.get(1).toString();
                client.title = response.get(2).toString();
                client.description = response.get(3).toString();
                System.out.println("URL: " + client.url
                    + "\nTitle: " + client.title
                    + "\nDescription: " + client.description);
            }
        } catch (IOException ioe) {
            System.out.println("IO Exception: " + ioe.getMessage());
            ioe.printStackTrace();
        } catch (XmlRpcException xre) {
            System.out.println("XML-RPC Exception: " + xre.getMessage());
        }
    }

    public Vector getRandomSite()
      throws IOException, XmlRpcException {

            // Create the client
            XmlRpcClient client = new XmlRpcClient(
                "http://localhost:3144/");
            // Create the parameters for the request
            Vector params = new Vector();
            // Send the request and get the response
            Vector result = (Vector) client.execute("dmoz.getRandomSite", params);
            return result;
    }
}

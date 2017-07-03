import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.*;

public class QuoteData {
    private String ticker;

    public QuoteData(String inTicker) {
        ticker = inTicker;
    }

    private String retrieveQuote() {
        StringBuffer buf = new StringBuffer();
        try {
            URL page = new URL("http://quote.yahoo.com/d/quotes.csv?s=" + ticker
               + "&f=sl1d1t1c1ohgv&e=.csv");
            String line;
            URLConnection conn = page.openConnection();
            conn.connect();
            InputStreamReader in= new InputStreamReader(conn.getInputStream());
            BufferedReader data = new BufferedReader(in);
            while ((line = data.readLine()) != null) {
                buf.append(line + "\n");
            }
        } catch (MalformedURLException mue) {
            System.out.println("Bad URL: " + mue.getMessage());
        } catch (IOException ioe) {
            System.out.println("IO Error:" + ioe.getMessage());
        }
        return buf.toString();
    }

    private void storeQuote(String data) {
        StringTokenizer tokens = new StringTokenizer(data, ",");
        String[] fields = new String[9];
        for (int i = 0; i < fields.length; i++) {
            fields[i] = stripQuotes(tokens.nextToken());
        }
        String datasource = "jdbc:odbc:QuoteData";
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection conn = DriverManager.getConnection(
                datasource, "", "");
            PreparedStatement pstmt = conn.prepareStatement(
                "INSERT INTO Stocks VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
            pstmt.setString(1, fields[0]);
            pstmt.setString(2, fields[1]);
            pstmt.setString(3, fields[2]);           
            pstmt.setString(4, fields[4]);
            pstmt.setString(5, fields[5]);
            pstmt.setString(6, fields[6]);
            pstmt.setString(7, fields[7]);
            pstmt.setString(8, fields[8]);
            pstmt.executeUpdate();
            conn.close();
        } catch (SQLException sqe) {
            System.out.println("SQL Error: " + sqe.getMessage());
        } catch (ClassNotFoundException cnfe) {
            System.out.println(cnfe.getMessage());
        }
    }

    private String stripQuotes(String input) {
        StringBuffer output = new StringBuffer();
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) != '\"') {
                output.append(input.charAt(i));
            }
        }
        return output.toString();
    }

    public static void main(String[] arguments) {
        if (arguments.length < 1) {
            System.out.println("Usage: java QuoteData tickerSymbol");
            System.exit(0);
        }
        QuoteData qd = new QuoteData(arguments[0]);
        String data = qd.retrieveQuote();
        qd.storeQuote(data);
    }      
}


import java.sql.*;
import java.util.*;

public class AccessHandler {
    public Vector getRandomSite() {
        Connection conn = getMySqlConnection();
        Vector response = new Vector();
        try {
            int rand = (int)Math.floor(1000 * Math.random());
            Statement st = conn.createStatement();
            ResultSet rec = st.executeQuery(
                "SELECT * FROM sites WHERE (ID=" +
                    rand + ")");
            if (rec.next()) {
                response.addElement("ok");
                response.addElement(rec.getString("url"));
                response.addElement(rec.getString("title"));
                response.addElement(rec.getString("description"));
            } else {
                response.addElement("database error: no records found");
            }
        } catch (SQLException sqe) {
            response.addElement("database error: " + sqe.getMessage());
        }
        return response;
    }

    private Connection getMySqlConnection() {
        Connection conn = null;
        String data = "jdbc:odbc:DmozDatabase";
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            conn = DriverManager.getConnection(
                data, "", "");
        } catch (SQLException s) {
            System.out.println("SQL Error: " + s.toString() + " "
                + s.getErrorCode() + " " + s.getSQLState());
        } catch (Exception e) {
            System.out.println("Error: " + e.toString()
                + e.getMessage());
        }
        return conn;
    }
}

import java.sql.*;
import java.util.*;

public class DmozHandler {
    public Vector getRandomSite() {
        Connection conn = getMySqlConnection();
        Vector response = new Vector();
        try {
            Statement st = conn.createStatement();
            ResultSet rec = st.executeQuery(
                "SELECT * FROM cooldata ORDER BY RAND() LIMIT 1");
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
        String data = "jdbc:mysql://localhost/cadenhead?user=stu&password=sm50";
        try {
            Class.forName("org.gjt.mm.mysql.Driver");
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

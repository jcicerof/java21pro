import java.sql.*;
 
public class OilTotals {
    public static void main(String[] arguments) {
        char quote = (char)34;
        String data = "jdbc:odbc:WorldEnergy";
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection conn = DriverManager.getConnection(
                data, "", "");
            Statement st = conn.createStatement();
            ResultSet rec = st.executeQuery(
                "SELECT * " +
                "FROM " + quote + "Country Oil Totals " + quote +
                "WHERE " +
                "(Country='" + arguments[0] + "') " +
                "ORDER BY Year");
            System.out.println("COUNTRY\tYEAR\tPRODUCTION\t" +
                "REFINED\tIMPORTS\tEXPORTS");
            while(rec.next()) {
                System.out.println(rec.getString(1) +  "\t"
                        + rec.getString(2) + "\t"
                        + rec.getString(3) + "\t"
                        + rec.getString(4) + "\t"
                        + rec.getString(5) + "\t"
                        + rec.getString(6));
            }
            st.close();
        } catch (SQLException s) {
            System.out.println("SQL Error: " + s.toString() + " "
                + s.getErrorCode() + " " + s.getSQLState());
        } catch (Exception e) {
            System.out.println("Error: " + e.toString()
                + e.getMessage());
        }
    }
}

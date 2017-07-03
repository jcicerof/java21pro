import java.sql.*;
 
public class CoalTotals {
    public static void main(String[] arguments) {
        String data = "jdbc:odbc:WorldEnergy";
       try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection conn = DriverManager.getConnection(
                data, "", "");
            Statement st = conn.createStatement();
            ResultSet rec = st.executeQuery(
                "SELECT * " +
                "FROM Coal " +
                "WHERE " +
                "(Country='" + arguments[0] + "') " +
                "ORDER BY Year");
            System.out.println("FIPS\tCOUNTRY\t\tYEAR\t" +
                "ANTHRACITE PRODUCTION");
            while(rec.next()) {
                System.out.println(rec.getString(1) +  "\t"
                        + rec.getString(2) + "\t\t"
                        + rec.getString(3) + "\t"
                        + rec.getString(4));
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

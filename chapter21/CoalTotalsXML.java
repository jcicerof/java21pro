import java.io.*;
import java.sql.*;

public class CoalTotalsXML {
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
            
            FileWriter fw = new FileWriter("CoalTotals.xml");
            BufferedWriter out = new BufferedWriter(fw);
            writeLine(out, "<?xml version=\"1.0\"?>");
            writeLine(out, "<!DOCTYPE Library SYSTEM \"coalData.dtd\">");
            writeLine(out, "<coalData>");
            while(rec.next()) {
                writeLine(out, "  <record>");
                writeLine(out, "    <fips>" + rec.getString(1) + "</fips>");
                writeLine(out, "      <country>" + rec.getString(2) + "</country>");
                writeLine(out, "      <year>" + rec.getString(3) + "</year>");
                writeLine(out, "      <anthraciteProduction>" + rec.getString(4)
                    + "</anthraciteProduction>");
                writeLine(out, "  </record>");
            }
            writeLine(out, "</coalData>");
            out.close();
            st.close();
        } catch (IOException e) {
            System.out.println("Error -- " + e.toString());
        } catch (SecurityException se) {
            System.out.println("Error -- " + se.toString());
        } catch (SQLException s) {
            System.out.println("SQL Error: " + s.toString() + " "
                + s.getErrorCode() + " " + s.getSQLState());
        } catch (Exception e) {
            System.out.println("Error: " + e.toString()
                + e.getMessage());
        }
    }

    static void writeLine(BufferedWriter out, String line) throws IOException {
        out.write(line, 0, line.length());
        out.newLine();
    }
}
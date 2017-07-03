import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class GuestBook extends HttpServlet {
    String name = "Not provided";
    String emailAddress = "Not provided";
    String url = "Not provided";
    String comment;

    public void doPost(HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException {

        name = req.getParameter("name");
        emailAddress = req.getParameter("email");
        url = req.getParameter("url");
        comment = req.getParameter("comment");
        ServletOutputStream out = res.getOutputStream();
        res.setContentType("text/html");
        if (comment != null) {
            addEntry(name, emailAddress, url, comment);
            out.println("<p>Your entry has been added to the guestbook.");
            out.println("<p><a href=\"/servlet/GuestBook\">View Entries</a>");
        } else {
            viewEntries(res);
            out.println("<h1>Add a Guestbook Entry</h1>");
            out.println("<form method=\"POST\" action=\"/servlet/GuestBook\">");
            out.println("  <p>Your Name: <input type=\"text\" name=\"name\" size=\"30\"></p>");
            out.println("  <p>Your E-mail Address: <input type=\"text\" name=\"email\" size=\"30\"></p>");
            out.println("  <p>Your Home Page: <input type=\"text\" name=\"url\" size=\"50\"></p>");
            out.println("  <p>Your Comment:</p>");
            out.println("  <p><textarea rows=\"4\" name=\"comment\" cols=\"40\"></textarea></p>");
            out.println("  <p><input type=\"submit\" value=\"Submit\" name=\"B1\">");
            out.println("  <input type=\"reset\" value=\"Reset\" name=\"B2\"></p>");
            out.println("</form>");
        }
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException {

        doPost(req, res);
    }

    void addEntry(String name, String email, String url, String comment) {
        try {
            ServletContext servletContext = getServletContext();
	    String filename = servletContext.getRealPath("guestbook.txt");
            // Create output stream
            FileWriter fw = new FileWriter(filename, true);
            BufferedWriter book = new BufferedWriter(fw);
            
            book.write("<p>Name: " + name + "<br>");
            book.newLine();
            book.write("E-mail: " + emailAddress + "<br>");
            book.newLine();
            book.write("Web Site: <a href=\"" + url + "\">" + url + "</a><br>");
            book.newLine();
            book.write("Comment");
            book.write("<p>" + comment);
            book.newLine();
            book.write("<hr>");
            book.newLine();
            book.close();
        } catch (IOException e) {
            System.out.println("Error -- " + e.toString());
        } catch (SecurityException se) {
            System.out.println("Error -- " + se.toString());
        }
    }

    void viewEntries(HttpServletResponse res) {
        try {
            ServletContext servletContext = getServletContext();
            String filename = servletContext.getRealPath("guestbook.txt");
            // Create input stream
            ServletOutputStream out = res.getOutputStream();
            res.setContentType("text/html");
            out.println("<h1>Guestbook</h1>");
            FileReader fr = new FileReader(filename);
            BufferedReader book = new BufferedReader(fr);

            try {
                String line = "None.";
                while (line != null) {
                    line = book.readLine();
                    if (line != null)
                        out.println(line);
                }
            } catch (IOException ioe) {
                // end of file reached
            }
            book.close();
        } catch (IOException e) {
            System.out.println("Error -- " + e.toString());
        } catch (SecurityException se) {
            System.out.println("Error -- " + se.toString());
        }
    }           
}

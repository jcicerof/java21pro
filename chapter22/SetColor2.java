import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class SetColor2 extends HttpServlet {
    String pageColor = "#FFFFFF";
    String textColor = "#000000";
        
    public void doPost(HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException {

        String colorParameter = req.getParameter("color");
        String textParameter = req.getParameter("text");
        if ((colorParameter != null) & (textParameter != null)) {
            Cookie colorCookie = new Cookie("color", colorParameter);
            colorCookie.setMaxAge(31536000);
            res.addCookie(colorCookie);
            pageColor = colorParameter;
            Cookie textCookie = new Cookie("text", colorParameter);
            textCookie.setMaxAge(31536000);
            res.addCookie(textCookie);
            textColor = textParameter;
        } else
            retrieveColors(req.getCookies());
        ServletOutputStream out = res.getOutputStream();
        res.setContentType("text/html");
        out.println("<html>");
        out.println("<body bgcolor=\"" + pageColor + "\">");
        out.println("<head><title>The U.S. Constitution</title></head>");
        out.println("<font color=\"" + textColor + "\">");
        out.println("<h1>The U.S. Constitution</h1>");
        displayFile("constitution.html", out);
        out.println("<h5>Choose new colors</h5>");
        out.println("<form action=\"SetColor2\" method=\"POST\">");
        out.println("Page: <input type=\"text\" name=\"color\" value=\"" +
           pageColor + "\" SIZE=40>");
        out.println("Text: <input type=\"text\" name=\"text\" value=\"" +
           textColor + "\" SIZE=40>");
        out.println("<p><input type=\"submit\" value=\"Change Colors\">");
        out.println("</form>");
        out.println("</font>");
        out.println("</body>");
        out.println("</html>");
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException {

        doPost(req, res);
    }

    void retrieveColors(Cookie[] cookies) {
        for (int i = 0; i < cookies.length; i++) {
            String cookieName = cookies[i].getName();
            if (cookieName.equals("color"))
                pageColor = cookies[i].getValue();
            if (cookieName.equals("text"))
                textColor = cookies[i].getValue();
        }
    }

    void displayFile(String pageName, ServletOutputStream out) {
        try {
            ServletContext servletContext = getServletContext();
	    String filename = servletContext.getRealPath(pageName);
            FileReader file = new FileReader(filename);
            BufferedReader buff = new BufferedReader(file);
            boolean eof = false;
            while (!eof) {
                String line = buff.readLine();
                if (line == null)
                   eof = true;
                else
                   out.println(line);
            }
            buff.close();
        } catch (IOException e) {
            log("Error -- " + e.toString());
        }
    }
}

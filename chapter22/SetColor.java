import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class SetColor extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException {

        String pageColor;
        String colorParameter = req.getParameter("color");
        if (colorParameter != null) {
            Cookie colorCookie = new Cookie("color", colorParameter);
            colorCookie.setMaxAge(31536000);
            res.addCookie(colorCookie);
            pageColor = colorParameter;
        } else {
            pageColor = retrieveColor(req.getCookies());
        }
        ServletOutputStream out = res.getOutputStream();
        res.setContentType("text/html");
        out.println("<html>");
        out.println("<body bgcolor=\"" + pageColor + "\">");
        out.println("<head><title>The U.S. Constitution</title></head>");
        out.println("<h1>The U.S. Constitution</h1>");
        displayFile("constitution.html", out);
        out.println("<h5>Choose a new color</h5>");
        out.println("<form action=\"SetColor\" method=\"POST\">");
        out.println("<input type=\"text\" name=\"color\" value=\"" +
           pageColor + "\" SIZE=40>");
        out.println("<p><input type=\"submit\" value=\"Change Color\">");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException {

        doPost(req, res);
    }

    String retrieveColor(Cookie[] cookies) {
        String inColor = "#FFFFFF";
        for (int i = 0; i < cookies.length; i++) {
            String cookieName = cookies[i].getName();
            if (cookieName.equals("color")) {
                inColor = cookies[i].getValue();
            }
        }
        return inColor;
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

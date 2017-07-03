import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class Rot13 extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException {

        String text = req.getParameter("text");
        String translation = translate(text);
        res.setContentType("text/html");
        ServletOutputStream out = res.getOutputStream();
        out.println("<html>");
        out.println("<body>");
        out.println("<head><title>ROT-13 Translator</title></head>");
        out.println("<h1>ROT-13 Translator</h1>");
        out.println("<p>Text to translate:");
        out.println("<form action=\"Rot13\" method=\"POST\">");
        out.println("<textarea name=\"text\" ROWS=8 COLS=55>");
        out.println(translation);
        out.println("</textarea>");
        out.println("<p><input type=\"submit\" value=\"translate\">");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException {

        doPost(req, res);
    }

    String translate(String input) {
        StringBuffer output = new StringBuffer();
        if (input != null) {
            for (int i = 0; i < input.length(); i++) {
                char inChar = input.charAt(i);
                if ((inChar >= 'A') & (inChar <= 'Z')) {
                    inChar += 13;
                    if (inChar > 'Z')
                        inChar -= 26;
                }
                if ((inChar >= 'a') & (inChar <= 'z')) {
                    inChar += 13;
                    if (inChar > 'z')
                        inChar -= 26;
                }
                output.append(inChar);
            }
        }
        return output.toString();
    }
}

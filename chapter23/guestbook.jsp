<%@ page import="java.util.*,java.io.*" %>
<html>
<head>
<title>Visitors Who Signed our Guestbook</title>
</head>
<body>
<h3>Visitors Who Signed our Guestbook</h3>
<%
String id = request.getParameter("id");
boolean noSignatures = true;
try {
    String filename = application.getRealPath(id + ".gbf");
    FileReader file = new FileReader(filename);
    BufferedReader buff = new BufferedReader(file);
    boolean eof = false;
    while (!eof) {
        String entry = buff.readLine();
        if (entry == null)
            eof = true;
        else {
            StringTokenizer entryData = new StringTokenizer(entry, "^");
            String name = (String) entryData.nextElement();
            String email = (String) entryData.nextElement();
            String url = (String) entryData.nextElement();
            String entryDate = (String) entryData.nextElement();
            String ip = (String) entryData.nextElement();
            String comments = (String) entryData.nextElement();
            out.print("<p>From: " + name);
            if (!email.equals("None"))
                out.println(" <" + email + "><br>");
            else
                out.println("<br>");
            if (!url.equals("None"))
                out.println("Home Page: <a href=\"" + url + "\">" + url + "</a><br>");
            out.println("Date: " + entryDate + "<br>");
            out.println("IP: " + ip);
            out.println("<blockquote>");
            out.println("<p>" + comments);
            out.println("</blockquote>");
            noSignatures = false;
        }
    }
    buff.close();
} catch (IOException e) {
    out.println("<p>This guestbook could not be read because of an error.");
    log("Guestbook Error: " + e.toString());
}
if (noSignatures)
    out.println("<p>No one has signed our guestbook yet.");
%>
<h3>Sign Our Guestbook</h3>
<form method="POST" action="guestbookpost.jsp">
  <table border="0" cellpadding="5" cellspacing="0" width="100%">
    <tr>
      <td width="15%" valign="top" align="right">Your Name:</td>
      <td width="50%"><input type="text" name="name" size="40"></td>
    </tr>
    <tr>
      <td width="15%" valign="top" align="right">Your E-mail Address:</td>
      <td width="50%"><input type="text" name="email" size="40"></td>
    </tr>
    <tr>
      <td width="15%" valign="top" align="right">Your Home Page:</td>
      <td width="50%"><input type="text" name="url" size="40"></td>
    </tr>
    <tr>
      <td width="15%" valign="top" align="right">Your Comments:</td>
      <td width="50%"><textarea rows="6" name="comments" cols="40"></textarea></td>
    </tr>
  </table>
  <p align="center"><input type="submit" value="Submit" name="B1">
  <input type="reset" value="Reset" name="Reset"></p>
<input type="hidden" name="id" value="<%= id %>">
</form>
</body>
</html>

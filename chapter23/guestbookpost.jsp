<%@ page import="java.util.*,java.io.*,guestbook.*" %>
<html>
<head>
<title>Thank You For Signing Our Guestbook</title>
</head>
<body>
<h3>Thank You For Signing Our Guestbook</h3>
<%
String id = request.getParameter("id");
String[] entryFields = { "name", "email", "url", "comments" };
String[] entry = new String[4];
for (int i = 0; i < entryFields.length; i++) {
    entry[i] = Guestbook.filterString(request.getParameter(entryFields[i]));
}
Date now = new Date();
String entryDate = now.toString();
String ip = request.getRemoteAddr();
%>

<p>Your entry looks like this:
<p>From: <%= entry[0] %><%= (!entry[1].equals("None") ? "<"+entry[1]+">" : "") %><br>
<% if (!entry[2].equals("None")) { %>
Home Page: <a href="<%= entry[2] %>"><%= entry[2] %></a><br>
<% } %>
Date: <%= entryDate %><br>
IP: <%= ip %>
<blockquote>
<p><%= entry[3] %>
</blockquote>

<%
try {
    boolean append = true;
    String filename = application.getRealPath(id + ".gbf");
    FileWriter fw = new FileWriter(filename, append);
    BufferedWriter fileOut = new BufferedWriter(fw);
    String newEntry = entry[0] + "^" + entry[1] + "^" + entry[2] + "^"
        + entryDate + "^" + ip + "^" + entry[3];
    fileOut.write(newEntry, 0, newEntry.length());
    fileOut.newLine();
    fileOut.close();
} catch (IOException e) {
    out.println("<p>This guestbook could not be updated because of an error.");
    log("Guestbook Error: " + e.toString());
}
%>

<p><a href="guestbook.jsp?id=<%= id %>">View the Guestbook</a>
</body>

</html>

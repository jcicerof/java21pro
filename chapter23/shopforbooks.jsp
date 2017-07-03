<html>
<head>
<title>Shop for Books</title>
</head>
<body>
<h2 align="Left">Favorite Books</h2>
<%
String[] bookTitle = { "Catch-22", "Something Happened",
    "Good as Gold" };
String[] isbn = { "0684833395", "0684841215", "0684839741" };
String amazonLink = "http://www.amazon.com/exec/obidos/ASIN/";
String bnLink = "http://shop.bn.com/booksearch/isbnInquiry.asp?isbn=";

String store = request.getParameter("store");
if (store == null) {
    store = "Amazon";
}
for (int i = 0; i < bookTitle.length; i++) {
    if (store.equals("Amazon"))
        out.println("<li><a href=\"" + amazonLink + isbn[i] + "\">" +
            bookTitle[i] + "</a>");
    else
        out.println("<li><a href=\"" + bnLink + isbn[i] + "\">" +
            bookTitle[i] + "</a>");
}
%>
<p>Preferred Bookstore:
<form action="shopforbooks.jsp" method="POST">
<p><input type="radio" value="Amazon" <%= (store.equals("Amazon") ? " checked" : "") %>
name="store"> Amazon.Com
<p><input type="radio" value="BN" <%= (store.equals("BN") ? " checked" : "") %>
name="store"> Barnes & Noble
<p><input type="submit" value="Change Store">
</form>
</body>
</html>

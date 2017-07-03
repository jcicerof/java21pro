<%
String agent = request.getHeader("User-Agent");
if (agent.indexOf("MSIE") > -1) {
%>
<h1>Welcome, Internet Explorer users!</h1>
<% } else { %>
<h1>Welcome, non-Internet Explorer users!</h1>
<% } %>

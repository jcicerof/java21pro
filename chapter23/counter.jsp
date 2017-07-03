<%@ page import="counter.*" %>
<html>
<head>
<title>Counter Example</title>
</head>
<body>
<h1>JSP Stats</h1>
<%! Counter visits; %>
<%! int count; %>

<%
visits = new Counter(application.getRealPath("counter.dat"));
count = visits.getCount() + 1;
%>

<p>This page has been loaded <%= count %> times. 

<% visits.setCount(count); %>
</body>
</html>

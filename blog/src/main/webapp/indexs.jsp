<%@ page language="java" contentType="text/html; charset=utf-8" %>
<html>
<body>
<%
	if (session.getAttribute("username") == null){
		response.sendRedirect("/blog/login.jsp");
	}
%>
<h2>Hello Worlds!</h2>
<!-- jsp内置了session -->
<h3>username: <%= session.getAttribute("username") %></h3>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="HongZe.web.User"%>
<%@ page import="HongZe.web.School"%>
<%
User user = (User) request.getAttribute("user");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Information</title>
</head>
<body>
	<h1>
		Hello,<%=user.name%>!
	</h1>
	<p>
		School Name: <span style="color: red"> <%=user.school.name%></span>
	</p>
	<p>
		School Address: <span style="color: red"> <%=user.school.address%></span>
	</p>
</body>
</html>
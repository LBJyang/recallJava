<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Hello World! - JSP</title>
</head>
<body>
	<%-- JSP comment --%>
	<h1>hello world!</h1>
	<p>
		<%
		out.println("Your IP Address is:");
		%>
		<span style="color: red"> <%=request.getRemoteAddr()%></span>
	</p>
</body>
</html>
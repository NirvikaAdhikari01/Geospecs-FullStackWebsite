<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/stylesheets/LoginOptions.css">
</head>
<body>
    <div class="container">
        <h2>Login</h2>
        <div class="options">
            <a href="<%=request.getContextPath()%>/pages/loginAdmin.jsp">Admin</a>
            <a href="<%=request.getContextPath()%>/pages/LoginCustomer.jsp">Customer</a>
        </div>
    </div>
</body>
</html>

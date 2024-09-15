<%@page import="Utils.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="CustomerHeader.jsp" %>

<%
Integer CustomerId = (Integer) userSession.getAttribute("CustomerId");
	%>
	

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Customer Profile</title>
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/stylesheets/EditAdminProfile.css">
</head>
<body>
<div class="edit-admin-container">

        <h2>Edit Admin Profile</h2>
        <form action="<%=contextPath%>/EditCustomerProfileServlet" method="post">
        	<input type="hidden" id="CustomerId" name="CustomerId" value="<%=CustomerId%>">
        	<div class="form-group">
                <label for="firstName">First Name:</label>
                <input type="text" id="firstName" name="firstName" required>
            </div>
            <div class="form-group">
                <label for="lastName">Last Name:</label>
                <input type="text" id="lastName" name="lastName" required>
            </div>
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" required>
            </div>
            <div class="form-group">
                <label for="number">Phone Number:</label>
                <input type="tel" id="number" name="number" pattern="[0-9]{10}" required>
                <small>Format: 10 digits without spaces or special characters</small>
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required>
            </div>
            <button type="submit">Update</button>
        </form>
        <%
		String errMsg = (String) request.getAttribute(StringUtils.MESSAGE_ERROR);
		String successMsg = (String) request.getAttribute(StringUtils.MESSAGE_SUCCESS);

		if (errMsg != null) {
			// print
		%>
		<h4 class="error-msg">
			<%
			out.println(errMsg);
			%>
		</h4>
		<%
		}

		if (successMsg != null) {
		// print
		%>
		<h4 class="success-msg">
			<%
			out.println(successMsg);
			%>
		</h4>
		<%
		}
		%>
        </div>
</body>
</html>
        
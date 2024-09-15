<%@page import="Utils.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	String contextPath = request.getContextPath();
	String errMsg = (String) request.getAttribute(StringUtils.MESSAGE_ERROR);
	String successMsg = (String) request.getAttribute(StringUtils.MESSAGE_SUCCESS);
	String username = (String) request.getAttribute(StringUtils.USERNAME);
	String successParam = request.getParameter(StringUtils.SUCCESS);
	%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Customer Login</title>
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/stylesheets/loginAdmin.css">
</head>
<body>
    <div class="container">
        <h2>Customer Login</h2>
        <form action="<%=contextPath%>/LoginCustomerServlet" method="post">
            <div class="form-group">
                <label for="username">Email:</label>
                <input type="text" id="username" name="username" required>
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required>
            </div>
            <button type="submit">Login</button>
        </form>
        <%
		if (errMsg != null) {
		%>
		<p class="error-msg">
			<%
			out.println(errMsg);
			%>
		</p>
		<%
		}

		if (successParam != null && successParam.equals(StringUtils.TRUE)) {
		%>
		<h2 class="success-msg">Registration Successful!</h2>
		<%
		}
		%>
	<p>Don't have an account? <a href="<%=contextPath%>/pages/RegisterCustomer.jsp">Register here</a></p>
    </div>
</body>
</html>
    
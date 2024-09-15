<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String contextPath = request.getContextPath();
	
	// Get the session and request Objects
	HttpSession userSession = request.getSession();
	String username = (String) userSession.getAttribute("email");
	%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>GEOSPECS</title>
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/stylesheets/CustomerHeader.css">
</head>
<body>
    <header>
        <div class="container">
            <h1>Customer Panel</h1>
            <nav>
                <ul>
                    <li><a href="<%=contextPath%>/pages/Index.jsp">Home</a></li>
                    <li><a href="<%=contextPath%>/pages/Cart.jsp">Cart</a></li>
                    <li><a href="<%=contextPath%>/pages/Order.jsp">Orders</a></li>
                    <li><a href="<%=contextPath%>/pages/ProfileCustomer.jsp">Profile</a></li>
                    <li><form action="<%
                    	
                    	if (username != null) {
                    		out.print(contextPath + "/LogoutServlet");
                    	} else {
                    		out.print(contextPath + "/pages/LoginOptions.jsp");
                    	}
                    	
                    	%>" method="post">
                    		<input type="submit" value="<%
                    		
                    		if (username != null) {
                    			out.print("Logout");
                    		} else {
                    			out.print("Login");
                    		}
                    		%>"/>
                    	</form></li>
                </ul>
            </nav>
        </div>
    </header>
</body>
</html>

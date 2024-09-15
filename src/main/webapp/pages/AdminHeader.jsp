<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String contextPath = request.getContextPath();
	
	// Get the session and request Objects
	HttpSession userSession = request.getSession();
	String username = (String) userSession.getAttribute("admin");
	%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Panel</title>
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/stylesheets/AdminHeader.css">
</head>
<body>
    <header>
        <div class="container">
            <h1>Admin Panel</h1>
            <nav>
                <ul>
                    <li><a href="AddProduct.jsp">Add Product</a></li>
                    <li><a href="ManageProducts.jsp">Manage Product</a></li>
                    <li><a href="UserOrders.jsp">All User Orders</a></li>
                    <li><a href="ProfileAdmin.jsp">Profile</a></li>
                    <li><form action="<%
                    	
                    	if (username != null) {
                    		out.print(contextPath + "/LogoutServlet");
                    	} else {
                    		out.print(contextPath + "/pages/loginAdmin.jsp");
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

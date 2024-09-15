<%@page import="Utils.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%@ include file="AdminHeader.jsp" %>

<%@ page import="java.sql.*" %>

<%  
        Integer adminId = (Integer) userSession.getAttribute("adminId");
        if (adminId == null) {
            response.sendRedirect(request.getContextPath() + "/pages/Index.jsp");
        } %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Manage Products</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/stylesheets/ManageProducts.css">
</head>
<body>
    <div class="container">
        <h2>Manage Products</h2>
        <table>
            <thead>
                <tr>
                    <th>Product ID</th>
                    <th>Product Name</th>
                    <th>Description</th>
                    <th>Price</th>
                    <th>Stock</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <!-- Java code to fetch products from the database -->
                <%
                try {
                	
                	Class.forName(StringUtils.DRIVER_NAME);
                    // Establish database connection
                    String url = (StringUtils.LOCALHOST_URL);
                    String Dusername = (StringUtils.LOCALHOST_USERNAME);
                    String password = (StringUtils.LOCALHOST_PASSWORD);
                    Connection conn = DriverManager.getConnection(url, Dusername, password);

                    // Query to fetch all products
                    String query = "SELECT * FROM product";
                    PreparedStatement pstmt = conn.prepareStatement(query);
                    ResultSet rs = pstmt.executeQuery();

                    // Check if any products are retrieved
                    if (!rs.isBeforeFirst()) {
                        out.println("<tr><td colspan=\"6\">No products found</td></tr>");
                    } else {
                        // Loop through the result set and display each product row
                        while (rs.next()) {
                %>
                            <tr>
                                <td><%= rs.getInt("product_id") %></td>
                                <td><%= rs.getString("product_name") %></td>
                                <td><%= rs.getString("description") %></td>
                                <td><%= rs.getDouble("price") %></td>
                                <td><%= rs.getInt("stock") %></td>
                                <td>
                                    <!-- Form for editing product -->
			                        <a href="UpdateProducts.jsp?id=<%= rs.getInt("product_id") %>">Edit</a>

			                        <!-- Form for deleting product -->
			                        <form action="../DeleteServlet" method="post">
			                            <input type="hidden" name="id" value="<%= rs.getInt("product_id") %>">
			                            <button type="submit">Delete</button>
			                        </form>
                                </td>
                            </tr>
                <%
                        }
                    }

                    // Close resources
                    rs.close();
                    pstmt.close();
                    conn.close();
                } catch (SQLException e) {
                    out.println("Database error: " + e.getMessage());
                    e.printStackTrace();
                }
                %>
            </tbody>
        </table>
    </div>
</body>
</html>

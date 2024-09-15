<%@page import="Utils.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.sql.*" %>
<%@ include file="CustomerHeader.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Home</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/stylesheets/index.css">
</head>
<body>
<main class="product-display">
<h1>Available Products</h1>

<!-- Search Form -->
        <div class="search-container">
	        <form action="<%= request.getContextPath() %>/SearchServlet" method="post">
	            <input type="text" name="searchQuery" placeholder="Search products...">
	            <button type="submit">Search</button>
	        </form>
        </div>
    <div class="container">
        
        
        
        <div class="products-container"> 
            <!-- Retrieve products from the database -->
            <%
                Connection conn = null;
                PreparedStatement pstmt = null;
                ResultSet rs = null;
                try {
                    Class.forName(StringUtils.DRIVER_NAME);
                    conn = DriverManager.getConnection(StringUtils.LOCALHOST_URL, StringUtils.LOCALHOST_USERNAME, StringUtils.LOCALHOST_PASSWORD);
                    
                    // Check if a search query parameter is provided
                    String searchQuery = request.getParameter("searchQuery");
                    String query;
                    if (searchQuery != null && !searchQuery.isEmpty()) {
                        // If search query is provided, filter products based on the query
                        query = "SELECT * FROM product WHERE product_name LIKE ? OR price LIKE ?";
                        pstmt = conn.prepareStatement(query);
                        pstmt.setString(1, "%" + searchQuery + "%");
                        pstmt.setString(2, "%" + searchQuery + "%");
                    } else {
                        // If no search query is provided, fetch all products
                        query = "SELECT * FROM product";
                        pstmt = conn.prepareStatement(query);
                    }
                    
                    rs = pstmt.executeQuery();

                    // Display products
                    while (rs.next()) {
            %>
                        <div class="product">
                            <img src="<%= request.getContextPath() + "/resources/images/product/" + rs.getString("imageUrl") %>" alt="<%= rs.getString("product_name") %>">
                            <h3><%= rs.getString("product_name") %></h3>
                            <p><%= rs.getString("description") %></p>
                            <p>Price: $<%= rs.getDouble("price") %></p>
                            <!-- Add to cart form -->
                            <form action="<%=request.getContextPath()%>/CartServlet" method="post" >
                                <input type="hidden" name="product_id" value="<%= rs.getInt("product_id") %>">
                                <input type="hidden" name="user_id" value="${sessionScope.CustomerId}">
                                <button type="submit">Add to Cart</button>
                            </form>
                        </div>
            <%
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    // Close resources
                    try {
                        if (rs != null) rs.close();
                        if (pstmt != null) pstmt.close();
                        if (conn != null) conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            %>
        </div>
    </div>
</main>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.*"%>
<%@page import="Utils.StringUtils"%>
<%@ include file="CustomerHeader.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Your Orders</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/stylesheets/ViewOrder.css">
</head>
<body>
<main class="order-container">
    <h1>Your Orders</h1>
    <div class="order-items">
        <% 
        Integer customerId = (Integer) userSession.getAttribute("CustomerId");
        if (customerId == null) {
            response.sendRedirect(request.getContextPath() + "/pages/LoginCustomer.jsp");
        }
            Connection conn = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            try {
                Class.forName(StringUtils.DRIVER_NAME);
                conn = DriverManager.getConnection(StringUtils.LOCALHOST_URL, StringUtils.LOCALHOST_USERNAME, StringUtils.LOCALHOST_PASSWORD);
                
                String query = "SELECT * FROM orders WHERE customerId = ?";
                pstmt = conn.prepareStatement(query);
                pstmt.setInt(1, customerId); // Replace customerId with the actual customer ID
                
                rs = pstmt.executeQuery();
                
                while (rs.next()) {
                    int orderId = rs.getInt("orderId");
                    int productId = rs.getInt("product_id");
                    int quantity = rs.getInt("quantity");
                    double totalAmount = rs.getDouble("totalAmount");
                    Timestamp orderDate = rs.getTimestamp("orderDate");
        %>
                    <div class="order-item">
                        <div class="details">
                            <h3>Order ID: <%= orderId %></h3>
                            <p>Product ID: <%= productId %></p>
                            <p>Quantity: <%= quantity %></p>
                            <p>Total Amount: $<%= totalAmount %></p>
                            <p>Order Date: <%= orderDate %></p>
                        </div>
                    </div>
        <%      }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
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
</main>
</body>
</html>

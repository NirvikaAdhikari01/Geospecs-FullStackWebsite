<%@page import="Utils.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*, javax.naming.*" %>
<%@ include file="AdminHeader.jsp" %>
<%  
        Integer adminId = (Integer) userSession.getAttribute("adminId");
        if (adminId == null) {
            response.sendRedirect(request.getContextPath() + "/pages/Index.jsp");
        } %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>All Customer Orders</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/stylesheets/ViewOrder.css">
</head>
<body>
<main class="order-container">
    <h1>Your Orders</h1>
    <div class="order-items">
        <% 
            
            Connection conn = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            try {
                Class.forName(StringUtils.DRIVER_NAME);
                conn = DriverManager.getConnection(StringUtils.LOCALHOST_URL, StringUtils.LOCALHOST_USERNAME, StringUtils.LOCALHOST_PASSWORD);
                
                String query = "SELECT * FROM orders";
                pstmt = conn.prepareStatement(query);
               
                
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

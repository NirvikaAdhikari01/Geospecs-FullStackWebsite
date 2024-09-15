<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.*"%>
<%@page import="Utils.StringUtils"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="CustomerHeader.jsp" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Cart</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/stylesheets/Cart.css">
</head>
<body>
<main class="cart-container">
    <h1>Your Cart</h1>
    <div class="cart-items">
        <%  
        Integer customerId = (Integer) userSession.getAttribute("CustomerId");
        if (customerId == null) {
            response.sendRedirect(request.getContextPath() + "/pages/LoginCustomer.jsp");
        }
            Connection conn = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            double grandTotal = 0.0;
            try {
                Class.forName(StringUtils.DRIVER_NAME);
                conn = DriverManager.getConnection(StringUtils.LOCALHOST_URL, StringUtils.LOCALHOST_USERNAME, StringUtils.LOCALHOST_PASSWORD);
                
                String query = "SELECT c.product_id, c.quantity, p.product_name, p.description, p.price FROM cart c INNER JOIN product p ON c.product_id = p.product_id WHERE c.customerId = ?";
                pstmt = conn.prepareStatement(query);
                pstmt.setInt(1, customerId); // Set the customer ID in the query
                
                rs = pstmt.executeQuery();
                
                while (rs.next()) {
                    int productId = rs.getInt("product_id");
                    int quantity = rs.getInt("quantity");
                    String productName = rs.getString("product_name");
                    String description = rs.getString("description");
                    double price = rs.getDouble("price");
                    double totalPrice = price * quantity; // Calculate total price for each product
                    grandTotal += totalPrice; // Accumulate total price for grand total
        %>
                    <div class="cart-item">
                        <div class="details">
                            <h3><%= productName %></h3>
                            <p><%= description %></p>
                            <p>Price: $<%= price %></p>
                            <p>Quantity: <%= quantity %></p>
                            <p>Total Price: $<%= totalPrice %></p> <!-- Display total price for each product -->
                            <form action="<%=request.getContextPath()%>/UpdateCartServlet" method="post">
                                <input type="hidden" name="customerId" value="<%= customerId %>">
                                <input type="hidden" name="productId" value="<%= productId %>">
                                <input type="number" name="quantity" value="<%= quantity %>" min="1">
                                <button type="submit">Update Quantity</button>
                            </form>
                            <form action="<%=request.getContextPath()%>/RemoveFromCartServlet" method="post">
                                <input type="hidden" name="customerId" value="<%= customerId %>">
                                <input type="hidden" name="productId" value="<%= productId %>">
                                <button type="submit">Remove</button>
                            </form>
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
    <div class="grand-total">
        <h2>Grand Total: $<%= grandTotal %></h2> <!-- Display grand total -->
    </div>
    <div class="checkout-button">
        <form action="<%=request.getContextPath()%>/CheckOutServlet" method="post">
            <button type="submit">Checkout</button>
        </form>
    </div>
</main>
</body>
</html>

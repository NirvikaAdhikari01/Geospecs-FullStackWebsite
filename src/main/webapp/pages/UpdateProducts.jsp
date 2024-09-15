<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="AdminHeader.jsp" %>
<%@ page import="java.sql.*" %>
<%@ page import="Utils.StringUtils" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Product</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/stylesheets/UpdateProduct.css">
</head>
<body>
    <div class="container">
        <h2>Update Product</h2>
        <%-- Retrieve product ID from request parameter --%>
        <%
        String productId = request.getParameter("id");
        if (productId != null) {
            try (Connection conn = DriverManager.getConnection(StringUtils.LOCALHOST_URL, StringUtils.LOCALHOST_USERNAME, StringUtils.LOCALHOST_PASSWORD)) {
                String query = "SELECT * FROM product WHERE product_id = ?";
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setString(1, productId);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    // Retrieve product details from the result set
                    String productName = rs.getString("product_name");
                    String description = rs.getString("description");
                    double price = rs.getDouble("price");
                    int stock = rs.getInt("stock");
        %>
                    <form action="../UpdateProductServlet" method="post" enctype="multipart/form-data">
					    <input type="hidden" name="productId" value="<%= productId %>">
					    <div class="form-group">
					        <label for="productName">Product Name:</label>
					        <input type="text" id="productName" name="productName" value="<%= productName %>" required>
					    </div>
					    <div class="form-group">
					        <label for="description">Description:</label>
					        <textarea id="description" name="description" rows="4" required><%= description %></textarea>
					    </div>
					    <div class="form-group">
					        <label for="price">Price:</label>
					        <input type="number" id="price" name="price" value="<%= price %>"  required>
					    </div>
					    <div class="form-group">
					        <label for="stock">Stock:</label>
					        <input type="number" id="stock" name="stock" value="<%= stock %>" required>
					    </div>
					    <div class="form-group">
					        <label for="image">Product Image:</label>
					        <input type="file" id="image" name="image">
					    </div>
					    <button type="submit">Update Product</button>
					</form>
        <%          
                } else {
                    out.println("Product not found");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                out.println("Database error: " + e.getMessage());
            }
        } else {
            out.println("Product ID not provided");
        }
        %>
    </div>
</body>
</html>

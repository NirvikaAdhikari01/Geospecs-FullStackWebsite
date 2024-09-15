<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="AdminHeader.jsp" %>
<%  
        Integer adminId = (Integer) userSession.getAttribute("adminId");
        if (adminId == null) {
            response.sendRedirect(request.getContextPath() + "/pages/Index.jsp");
        } %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Product</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/stylesheets/AddProduct.css">
</head>
<body>
    <div class="add-product-container">
        <h2>Add Product</h2>
        <form action="<%=contextPath%>/AddProductServlet" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label for="productName">Product Name:</label>
                <input type="text" id="productName" name="productName" required>
            </div>
            <div class="form-group">
                <label for="description">Description:</label>
                <textarea id="description" name="description"></textarea>
            </div>
            <div class="form-group">
                <label for="price">Price:</label>
                <input type="text" id="price" name="price" required>
            </div>
            <div class="form-group">
                <label for="stock">Stock:</label>
                <input type="number" id="stock" name="stock" value="0" required>
            </div>
            <div class="form-group">
                <label for="imageUrl">Image URL:</label>
                <input type="file" id="imageUrl" name="imageUrl">
            </div>
            <button type="submit">Add Product</button>
        </form>
    </div>
</body>
</html>

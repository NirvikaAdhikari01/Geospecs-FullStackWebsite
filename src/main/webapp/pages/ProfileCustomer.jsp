<%@page import="Utils.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="CustomerHeader.jsp" %>
<%@ page import="java.sql.*" %>
<%
    //HttpSession userSession = request.getSession();
    Integer CustomerId = (Integer) userSession.getAttribute("CustomerId");
    
    
    
    if (CustomerId == null) {
        response.sendRedirect(request.getContextPath() + "/pages/LoginCustomer.jsp");
    }

    // Establish database connection
    String url = (StringUtils.LOCALHOST_URL);
    String Dusername = (StringUtils.LOCALHOST_USERNAME);
    String password = (StringUtils.LOCALHOST_PASSWORD);
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    try {
        Class.forName(StringUtils.DRIVER_NAME);
        conn = DriverManager.getConnection(url, Dusername, password);

        // Query to fetch admin details based on adminId
        String query = "SELECT firstName, lastName, email, number FROM customer WHERE customerId = ?";
        pstmt = conn.prepareStatement(query);
        pstmt.setInt(1, CustomerId);

        rs = pstmt.executeQuery();
        if (rs.next()) {
            String firstName = rs.getString("firstName");
            String lastName = rs.getString("lastName");
            String Email = rs.getString("email");
            String Number = rs.getString("number");

            // Set adminName and adminEmail as request attributes
            request.setAttribute("firstName", firstName);
            request.setAttribute("lastName", lastName);
            request.setAttribute("Email", Email);
            request.setAttribute("Number", Number);
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
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Customer Profile</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/stylesheets/ProfileAdmin.css">
</head>
<body>
    <div class="container">
        <h2>Customer Profile</h2>
        <div class="profile-details">
            <p><strong>First Name:</strong> <%= request.getAttribute("firstName") %></p>
            <p><strong>Last Name:</strong> <%= request.getAttribute("lastName") %></p>
            <p><strong>Email:</strong> <%= request.getAttribute("Email") %></p>
            <p><strong>Number:</strong> <%= request.getAttribute("Number") %></p>
            <!-- Add more profile details here as needed -->
        </div>
        <form action="<%=request.getContextPath()%>/pages/EditCustomerProfile.jsp">
            <button type="submit">Edit Profile</button>
        </form>
    </div>
</body>
</html>
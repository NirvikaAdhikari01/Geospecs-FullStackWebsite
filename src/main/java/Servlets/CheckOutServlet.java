package Servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Utils.StringUtils;

/**
 * Servlet implementation class CheckOutServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/CheckOutServlet" })
public class CheckOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckOutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Retrieve customerId from session or request parameter
        int customerId = 1; // Replace with your logic to get customerId
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            Class.forName(StringUtils.DRIVER_NAME);
            conn = DriverManager.getConnection(StringUtils.LOCALHOST_URL, StringUtils.LOCALHOST_USERNAME, StringUtils.LOCALHOST_PASSWORD);
            
            // Retrieve cart items for the customer
            String retrieveQuery = "SELECT product_id, quantity FROM cart WHERE customerId = ?";
            pstmt = conn.prepareStatement(retrieveQuery);
            pstmt.setInt(1, customerId);
            ResultSet rs = pstmt.executeQuery();
            
            // Insert cart items into the order table
            String insertQuery = "INSERT INTO orders (customerId, product_id, quantity) VALUES (?, ?, ?)";
            pstmt = conn.prepareStatement(insertQuery);
            while (rs.next()) {
                pstmt.setInt(1, customerId);
                pstmt.setInt(2, rs.getInt("product_id"));
                pstmt.setInt(3, rs.getInt("quantity"));
                pstmt.executeUpdate();
            }
            
            // Clear the cart for the customer
            String clearCartQuery = "DELETE FROM cart WHERE customerId = ?";
            pstmt = conn.prepareStatement(clearCartQuery);
            pstmt.setInt(1, customerId);
            pstmt.executeUpdate();
            
            // Redirect to a thank you page or display a success message
            response.sendRedirect("UserOrder.jsp");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            // Handle errors
            response.sendRedirect("error_page.jsp");
        } finally {
            // Close resources
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }	
     }
        
}

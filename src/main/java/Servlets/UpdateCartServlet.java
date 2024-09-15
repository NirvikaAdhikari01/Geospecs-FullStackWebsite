package Servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Utils.StringUtils;

/**
 * Servlet implementation class UpdateCartServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/UpdateCartServlet" })
public class UpdateCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String customerId_in_Str = request.getParameter("customerId");
        String productId_in_Str = request.getParameter("productId");
        String quantity_in_Str = request.getParameter("quantity");
        
        System.out.println(quantity_in_Str);
        
        int CustomerId = Integer.parseInt(customerId_in_Str);
        int ProductId = Integer.parseInt(productId_in_Str);
        int quantity = Integer.parseInt(quantity_in_Str);
        
        System.out.println(CustomerId);
        System.out.println(ProductId);
        System.out.println(quantity);
        
        String jdbcUrl = StringUtils.LOCALHOST_URL;
        String username = StringUtils.LOCALHOST_USERNAME;
        String password = StringUtils.LOCALHOST_PASSWORD;

        try {
            Class.forName(StringUtils.DRIVER_NAME);
            Connection conn = DriverManager.getConnection(jdbcUrl, username, password);

            String query = "UPDATE cart SET quantity = ? WHERE customerId = ? AND product_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, quantity);
            pstmt.setInt(2, CustomerId);
            pstmt.setInt(3, ProductId);
            pstmt.executeUpdate();

            conn.close();

            response.sendRedirect(request.getContextPath() + "/pages/Cart.jsp");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            // Handle exceptions here
        }
	}

}

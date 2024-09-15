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
 * Servlet implementation class RemoveFromCartServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/RemoveFromCartServlet" })
public class RemoveFromCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveFromCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String Customer_id_in_Str = request.getParameter("customerId");
        String Product_id_in_Str = request.getParameter("productId");
        
        System.out.println(Customer_id_in_Str);
		System.out.println(Product_id_in_Str);
		
		int CustomerId = Integer.parseInt(Customer_id_in_Str);
		int ProductId = Integer.parseInt(Product_id_in_Str);
		
		
		String sql = "DELETE FROM cart WHERE customerId = ? AND product_id = ?";

        
        try (Connection conn = DriverManager.getConnection(StringUtils.LOCALHOST_URL, StringUtils.LOCALHOST_USERNAME, StringUtils.LOCALHOST_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, CustomerId);
            stmt.setInt(2, ProductId);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                response.sendRedirect(request.getContextPath() + "/pages/Cart.jsp"); 
            } else {
                request.setAttribute("errorMessage", "Error");
                request.getRequestDispatcher(request.getContextPath() + "/pages/Cart.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException("SQL error when removing item from cart", e);
        }
	}

}

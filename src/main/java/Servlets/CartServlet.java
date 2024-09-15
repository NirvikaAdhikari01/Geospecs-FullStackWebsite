package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DatabaseController.DataBaseController;
import Utils.StringUtils;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/CartServlet" })
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	DataBaseController dbController = new DataBaseController();
    
    public CartServlet() {
        super();
        
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String productIdStr = request.getParameter("product_id");
		String CustomerIdStr = request.getParameter("user_id");
		// Retrieve parameters from the request
		
		System.out.println(productIdStr);
		System.out.println(CustomerIdStr);
		
		int productId = Integer.parseInt(productIdStr);
		int customerId = Integer.parseInt(CustomerIdStr);
        
        int result = dbController.AddToCart(customerId, productId);
        
        if (result > 0) {
			// IF successfully added to cart
			
			response.sendRedirect(request.getContextPath() + "/pages/Cart.jsp");
			
		} else {
			// IF the user has't logged in 
			request.setAttribute(StringUtils.MESSAGE_ERROR, "Please Check if you have logged in or the item alredy exist in the cart");
			request.getRequestDispatcher(StringUtils.HOME).forward(request, response);
		}
	}

}

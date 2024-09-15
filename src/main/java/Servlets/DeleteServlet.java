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
 * Servlet implementation class DeleteServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/DeleteServlet" })
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	DataBaseController dbController = new DataBaseController();
	
    public DeleteServlet() {
        super();
       
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String Prodcut_id_in_Str = request.getParameter("id");
		
		System.out.println(Prodcut_id_in_Str);
		
		int ProductId = Integer.parseInt(Prodcut_id_in_Str);
		
		System.out.println(ProductId);
		
		// Call DBController to register the student
				int result = dbController.DeleteProduct(ProductId);
				
				if (result > 0) {
					
					response.sendRedirect(request.getContextPath() + StringUtils.MANAGE_URL);
				} else {
					
					
				}
		
	}

}

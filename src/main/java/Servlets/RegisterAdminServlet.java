package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DatabaseController.DataBaseController;
import Model.RegisterAdminModel;
import Utils.StringUtils;


/**
 * Servlet implementation class RegisterAdminServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/RegisterAdminServlet" })
public class RegisterAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	DataBaseController dbController = new DataBaseController();
	
    public RegisterAdminServlet() {
        super();
      
        
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String AdminName = request.getParameter("username");
		String Email = request.getParameter("email");
		String Password = request.getParameter("password");
		
		System.out.println(AdminName);
		System.out.println(Email);
		System.out.println(Password);
		
		
		RegisterAdminModel registerAdminModel = new RegisterAdminModel (AdminName, Email, Password);
		// Call DBController to register the student
		int result = dbController.registerAdmin(registerAdminModel);
		
		if (result > 0) {
			request.setAttribute(StringUtils.MESSAGE_SUCCESS, StringUtils.MESSAGE_SUCCESS_REGISTER);
			response.sendRedirect(request.getContextPath() + StringUtils.PAGE_URL_LOGIN_ADMIN+ "?success=true");
		} else {
			
			request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_UNIQUE);
			request.getRequestDispatcher(StringUtils.PAGE_URL_REGISTER_ADMIN).forward(request, response);
		}
	}

}

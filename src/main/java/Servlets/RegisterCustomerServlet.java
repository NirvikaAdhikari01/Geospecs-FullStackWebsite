package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DatabaseController.DataBaseController;
import Model.RegisterCustomerModel;
import Utils.StringUtils;

/**
 * Servlet implementation class RegisterCustomerServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/RegisterCustomerServlet" })
public class RegisterCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	DataBaseController dbController = new DataBaseController();
	
    public RegisterCustomerServlet() {
        super();
       
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String FirstName = request.getParameter("firstName");
		String LastName = request.getParameter("lastName");
		String Email = request.getParameter("email");
		String Number = request.getParameter("number");
		String Password = request.getParameter("password");
		
		System.out.println(FirstName);
		System.out.println(LastName);
		System.out.println(Email);
		System.out.println(Number);
		System.out.println(Password);
		
		RegisterCustomerModel registerCustomerModel = new RegisterCustomerModel (FirstName, LastName, Email, Number, Password);
		
		
		// Call DBController to register the Customer
		int result = dbController.registerCustomer(registerCustomerModel);
		
		if (result > 0) {
			request.setAttribute(StringUtils.MESSAGE_SUCCESS, StringUtils.MESSAGE_SUCCESS_REGISTER);
			response.sendRedirect(request.getContextPath() + StringUtils.PAGE_URL_LOGIN_CUSTOMER+ "?success=true");
		} else {
			
			request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_UNIQUE);
			request.getRequestDispatcher(StringUtils.PAGE_URL_REGISTER_CUSTOMER).forward(request, response);
		}
		
	}

}

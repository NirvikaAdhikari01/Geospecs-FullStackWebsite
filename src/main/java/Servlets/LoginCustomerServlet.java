package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DatabaseController.DataBaseController;
import Model.LoginCustomerModel;
import Utils.StringUtils;

/**
 * Servlet implementation class LoginCustomerServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/LoginCustomerServlet" })
public class LoginCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	DataBaseController dbController = new DataBaseController();
	
    public LoginCustomerServlet() {
        super();
        
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Extract username and password from the request parameters
        String Email = request.getParameter(StringUtils.USERNAME);
        String password = request.getParameter(StringUtils.PASSWORD);
		
        // Create a LoginModel object to hold user credentials
        LoginCustomerModel loginCustomerModel = new LoginCustomerModel(Email, password);
        
        // Call DBController to validate login credentials
        int loginResult = dbController.getCustomerLoginInfo(loginCustomerModel);
        
        if (loginResult > 0) {
			// Sucessful login 
			
        	// Creating session and putting adminId
			HttpSession session = request.getSession();
			session.setAttribute("CustomerId", loginResult);
			session.setAttribute("email", Email);
			
			
			Cookie userCookie = new Cookie("email",Email);
			userCookie.setMaxAge(30*60);
			response.addCookie(userCookie);
			
			
			response.sendRedirect(request.getContextPath() + "/pages/Index.jsp");
			
		}else {
			// WRONG PASSWORD OR USERNAME
			request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_WRONG_USERNAME_PASSWORD);
			request.getRequestDispatcher(StringUtils.PAGE_URL_LOGIN_CUSTOMER).forward(request, response);
			
		}
		
	}

}

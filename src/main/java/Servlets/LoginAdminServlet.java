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
import Model.LoginAdminModel;
import Utils.StringUtils;


/**
 * Servlet implementation class LoginAdminServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/LoginAdminServlet" })
public class LoginAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	DataBaseController dbController = new DataBaseController();
	
    public LoginAdminServlet() {
        super();
        
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Extract username and password from the request parameters
        String userName = request.getParameter(StringUtils.USERNAME);
        String password = request.getParameter(StringUtils.PASSWORD);
		
        // Create a LoginModel object to hold user credentials
        LoginAdminModel loginAdminModel = new LoginAdminModel(userName, password);
        
        // Call DBController to validate login credentials
        int loginResult = dbController.getAdminLoginInfo(loginAdminModel);
        
        if (loginResult > 0) {
			// Sucessful login 
			
        	// Creating session and putting adminId
			HttpSession userSession = request.getSession();
			userSession.setAttribute("adminId", loginResult);
			userSession.setAttribute("admin", userName);
			
			
			Cookie userCookie = new Cookie("admin",userName);
			userCookie.setMaxAge(30*60);
			response.addCookie(userCookie);
			
			
			response.sendRedirect(request.getContextPath() + "/pages/ManageProducts.jsp");
			
		}else {
			// WRONG PASSWORD OR USERNAME
			request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_WRONG_USERNAME_PASSWORD);
			request.getRequestDispatcher(StringUtils.PAGE_URL_LOGIN_ADMIN).forward(request, response);
			
		}
	}

}

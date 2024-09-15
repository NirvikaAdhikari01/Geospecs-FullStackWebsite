package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DatabaseController.DataBaseController;
import Model.EditAdminProfileModel;
import Utils.StringUtils;

/**
 * Servlet implementation class EditAdminProfileServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/EditAdminProfileServlet" })
public class EditAdminProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	DataBaseController dbController = new DataBaseController();
	/**
     * @see HttpServlet#HttpServlet()
     */
    public EditAdminProfileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String AdminIdStr = request.getParameter("adminId");
		String AdminName = request.getParameter("username");
		String Email = request.getParameter("email");
		String Password = request.getParameter("password");
		
		
		int adminId = Integer.parseInt(AdminIdStr);
		
		System.out.println(adminId);
		System.out.println(AdminName);
		System.out.println(Email);
		System.out.println(Password);
		
		
		EditAdminProfileModel editAdminModel = new EditAdminProfileModel (adminId, AdminName, Email, Password);
		// Call DBController to register the adimin
		int result = dbController.EditAdmin(editAdminModel);
		
		if (result > 0) {
			request.setAttribute(StringUtils.MESSAGE_SUCCESS, StringUtils.MESSAGE_SUCCESS_REGISTER);
			response.sendRedirect(request.getContextPath() + StringUtils.PAGE_URL_LOGIN_ADMIN+ "?success=true");
		} else {
			
			request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_UNIQUE);
			request.getRequestDispatcher(StringUtils.PAGE_URL_EDIT_ADMIN).forward(request, response);
		}
	
		
		
	}

}

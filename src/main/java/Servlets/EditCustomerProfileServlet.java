package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DatabaseController.DataBaseController;
import Model.EditCustomerProfileModel;
import Utils.StringUtils;

/**
 * Servlet implementation class EditCustomerProfileServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/EditCustomerProfileServlet" })
public class EditCustomerProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	DataBaseController dbController = new DataBaseController();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditCustomerProfileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String CustomerIdStr = request.getParameter("CustomerId");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String Email = request.getParameter("email");
		String Number = request.getParameter("number");
		String Password = request.getParameter("password");
		
		int CustomerId = Integer.parseInt(CustomerIdStr);
		
		System.out.println(CustomerId);
		System.out.println(firstName);
		System.out.println(lastName);
		System.out.println(Email);
		System.out.println(Number);
		System.out.println(Password);
		
		
		EditCustomerProfileModel editCustomerModel = new EditCustomerProfileModel (CustomerId, firstName, lastName, Email, Number, Password);
		
		int result = dbController.EditCustomer(editCustomerModel);
		
		
		if (result > 0) {
			request.setAttribute(StringUtils.MESSAGE_SUCCESS, StringUtils.MESSAGE_SUCCESS_REGISTER);
			response.sendRedirect(request.getContextPath() + StringUtils.PAGE_URL_LOGIN_CUSTOMER+ "?success=true");
		} else {
			
			request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_UNIQUE);
			request.getRequestDispatcher(StringUtils.PAGE_URL_EDIT_CUSTOMER).forward(request, response);
		}
		
	}

}

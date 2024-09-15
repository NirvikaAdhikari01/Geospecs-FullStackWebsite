package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import DatabaseController.DataBaseController;
import Model.UpdateProductModel;
import Utils.StringUtils;

/**
 * Servlet implementation class UpdateProductServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/UpdateProductServlet" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50)
public class UpdateProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	DataBaseController dbController = new DataBaseController();
   
    public UpdateProductServlet() {
        super();
       
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String product_id_in_Str = request.getParameter("productId");
		String product_name = request.getParameter("productName");
		String description = request.getParameter("description");
		String price_in_Str = request.getParameter("price");
		String stock_in_Str = request.getParameter("stock");
		System.out.println("Hello");
		Part imagePart = request.getPart("image");
		
		System.out.println(product_id_in_Str);
		System.out.println(product_name);
		System.out.println(description);
		System.out.println(price_in_Str);
		System.out.println(stock_in_Str);
		System.out.println(imagePart);
		
		int ProductId = Integer.parseInt(product_id_in_Str);
		int price = Integer.parseInt(price_in_Str);
		int stock = Integer.parseInt(stock_in_Str);
		
		
		System.out.println(ProductId);
		System.out.println(price);
		System.out.println(stock);
		
		UpdateProductModel newproduct = new UpdateProductModel(ProductId, product_name, description, price, stock, imagePart);
		
		// Call DBController to register the student
		int result = dbController.UpdateProduct(newproduct);
		
		if (result == 1) {
			
			// Get the image file name from the student object (assuming it was extracted earlier)
			String fileName = newproduct.getImageUrl();

			// Check if a filename exists (not empty or null)
			if (!fileName.isEmpty() && fileName != null) {
			  // Construct the full image save path by combining the directory path and filename
			  String savePath = StringUtils.IMAGE_DIR_PRODUCT;
			  imagePart.write(savePath + fileName);  // Save the uploaded image to the specified path
			  response.sendRedirect(request.getContextPath() + StringUtils.MANAGE_URL);
			}
		}else if (result == 0) {
			
		} else {
			
		}
		
	}

}
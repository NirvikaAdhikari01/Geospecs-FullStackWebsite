package DatabaseController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.AddProductModel;
import Model.EditAdminProfileModel;
import Model.EditCustomerProfileModel;
import Model.LoginAdminModel;
import Model.LoginCustomerModel;
import Model.RegisterAdminModel;
import Model.RegisterCustomerModel;
import Model.UpdateProductModel;
import Utils.StringUtils;



public class DataBaseController {
	

	/**
	 * Establishes a connection to the database using pre-defined credentials and
	 * driver information.
	 * 
	 * @return A `Connection` object representing the established connection to the
	 *         database.
	 * @throws SQLException           if a database access error occurs.
	 * @throws ClassNotFoundException if the JDBC driver class is not found.
	 */
	public Connection getConnection() throws SQLException, ClassNotFoundException {

		// Load the JDBC driver class specified by the StringUtils.DRIVER_NAME constant
		Class.forName(StringUtils.DRIVER_NAME);

		// Create a connection to the database using the provided credentials
		return DriverManager.getConnection(StringUtils.LOCALHOST_URL, StringUtils.LOCALHOST_USERNAME,
				StringUtils.LOCALHOST_PASSWORD);
		}
	
	public int registerAdmin (RegisterAdminModel admin) {

		try(Connection con = getConnection()){
			PreparedStatement stmt = con.prepareStatement(StringUtils.REGISTER_ADMIN);
			
			stmt.setString(1, admin.getAdmin_name());
			stmt.setString(2, admin.getEmail());
			stmt.setString(3, admin.getPassword());
			
			
			// Execute the update statement and store the number of affected rows
			int result = stmt.executeUpdate();
			// Check if the update was successful (i.e., at least one row affected)
			if (result > 0) {
				return 1; // Registration successful
			} else {
				return 0; // Registration failed (no rows affected)
				}

			} catch (ClassNotFoundException | SQLException ex) {
			// Print the stack trace for debugging purposes
				ex.printStackTrace();
				return -1; // Internal error
			}
	}
	
	
	public int EditAdmin (EditAdminProfileModel newadmin) {

		try(Connection con = getConnection()){
			PreparedStatement stmt = con.prepareStatement(StringUtils.UPDATE_ADMIN);
			
			stmt.setString(1, newadmin.getAdmin_name());
			stmt.setString(2, newadmin.getEmail());
			stmt.setString(3, newadmin.getPassword());
			stmt.setInt(4, newadmin.getAdmin_id());
			
			
			// Execute the update statement and store the number of affected rows
			int result = stmt.executeUpdate();
			// Check if the update was successful (i.e., at least one row affected)
			if (result > 0) {
				return 1; // Registration successful
			} else {
				return 0; // Registration failed (no rows affected)
				}

			} catch (ClassNotFoundException | SQLException ex) {
			// Print the stack trace for debugging purposes
				ex.printStackTrace();
				return -1; // Internal error
			}
	}
	
	
	public int registerCustomer (RegisterCustomerModel customer) {

		try(Connection con = getConnection()){
			PreparedStatement stmt = con.prepareStatement(StringUtils.REGISTER_CUSTOMER);
			
			stmt.setString(1, customer.getFirst_name());
			stmt.setString(2, customer.getLast_name());
			stmt.setString(3, customer.getEmail());
			stmt.setString(4, customer.getNumber());
			stmt.setString(5, customer.getPassword());
			
			
			// Execute the update statement and store the number of affected rows
			int result = stmt.executeUpdate();
			// Check if the update was successful (i.e., at least one row affected)
			if (result > 0) {
				return 1; // Registration successful
			} else {
				return 0; // Registration failed (no rows affected)
				}

			} catch (ClassNotFoundException | SQLException ex) {
			// Print the stack trace for debugging purposes
				ex.printStackTrace();
				return -1; // Internal error
			}
	}
	
	
	public int EditCustomer (EditCustomerProfileModel newcustomer) {

		try(Connection con = getConnection()){
			PreparedStatement stmt = con.prepareStatement(StringUtils.UPDATE_CUSTOMER);
			
			stmt.setString(1, newcustomer.getFirst_name());
			stmt.setString(2, newcustomer.getLast_name());
			stmt.setString(3, newcustomer.getEmail());
			stmt.setString(4, newcustomer.getNumber());
			stmt.setString(5, newcustomer.getPassword());
			stmt.setInt(6, newcustomer.getCustomerId());
			
			
			// Execute the update statement and store the number of affected rows
			int result = stmt.executeUpdate();
			// Check if the update was successful (i.e., at least one row affected)
			if (result > 0) {
				return 1; // Update successful
			} else {
				return 0; // Update failed (no rows affected)
				}

			} catch (ClassNotFoundException | SQLException ex) {
			// Print the stack trace for debugging purposes
				ex.printStackTrace();
				return -1; // Internal error
			}
	}
	
	
	public int getAdminLoginInfo(LoginAdminModel loginAdminModel) {
		// Try-catch block to handle potential SQL or ClassNotFound exceptions
		try (Connection con = getConnection()){
			// Prepare a statement using the predefined query for login check
			PreparedStatement stmt = getConnection().prepareStatement(StringUtils.QUERY_LOGIN_ADMIN_CHECK);

			// Set the username in the first parameter of the prepared statement
			stmt.setString(1, loginAdminModel.getUsername());
			stmt.setString(2, loginAdminModel.getPassword());
			
			ResultSet rs = stmt.executeQuery();
			

			if(rs.next()) {
				/// IF the admin name and password matches return admin id 
				return rs.getInt("adminId");
			}else {
				return 0;
			}
			
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return -1;
			
			}
	
	}
	

	public int getCustomerLoginInfo(LoginCustomerModel loginCustomerModel) {
		// Try-catch block to handle potential SQL or ClassNotFound exceptions
		try (Connection con = getConnection()){
			// Prepare a statement using the predefined query for login check
			PreparedStatement stmt = getConnection().prepareStatement(StringUtils.QUERY_LOGIN_CUSTOMER_CHECK);

			// Set the username in the first parameter of the prepared statement
			stmt.setString(1, loginCustomerModel.getEmail());
			stmt.setString(2, loginCustomerModel.getPassword());
			
			ResultSet rs = stmt.executeQuery();
			

			if(rs.next()) {
				/// IF the admin name and password matches return admin id 
				return rs.getInt("customerId");
			}else {
				return 0;
			}
			
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return -1;
			
			}
	
	}
	
	
	public int AddProduct (AddProductModel product) {

		try(Connection con = getConnection()){
			PreparedStatement stm = con.prepareStatement(StringUtils.ADD_PRODUCT);
			
			stm.setString(1, product.getProduct_name());
			stm.setString(2, product.getDescription());
			stm.setInt(3, product.getPrice());
			stm.setInt(4, product.getStock());
			stm.setString(5, product.getImageUrl());
			
			
			// Execute the update statement and store the number of affected rows
			int result = stm.executeUpdate();
			// Check if the update was successful (i.e., at least one row affected)
			if (result > 0) {
				return 1; // Sucessfully added
			} else {
				return 0; // Failde
				}

			} catch (ClassNotFoundException | SQLException ex) {
			// Print the stack trace for debugging purposes
				ex.printStackTrace();
				return -1; 
			}
	}
	
	
	public int UpdateProduct (UpdateProductModel newproduct) {

		try(Connection con = getConnection()){
			PreparedStatement stm = con.prepareStatement(StringUtils.UPDATE_PRODUCT);
			
			stm.setString(1, newproduct.getProduct_name());
			stm.setString(2, newproduct.getDescription());
			stm.setInt(3, newproduct.getPrice());
			stm.setInt(4, newproduct.getStock());
			stm.setString(5, newproduct.getImageUrl());
			stm.setInt(6, newproduct.getProductId());
			
			
			// Execute the update statement and store the number of affected rows
			int result = stm.executeUpdate();
			// Check if the update was successful (i.e., at least one row affected)
			if (result > 0) {
				return 1; // Sucessfully added
			} else {
				return 0; // Failde
				}

			} catch (ClassNotFoundException | SQLException ex) {
			// Print the stack trace for debugging purposes
				ex.printStackTrace();
				return -1; 
			}
	}
	
	
	
	public int DeleteProduct (int ProductId) {

		try(Connection con = getConnection()){
			PreparedStatement stm = con.prepareStatement(StringUtils.DELETE_PRODUCT);
			
			stm.setInt(1, ProductId);
			
			
			// Execute the update statement and store the number of affected rows
			int result = stm.executeUpdate();
			// Check if the update was successful (i.e., at least one row affected)
			if (result > 0) {
				return 1; // Sucessfully added
			} else {
				return 0; // Failde
				}

			} catch (ClassNotFoundException | SQLException ex) {
			// Print the stack trace for debugging purposes
				ex.printStackTrace();
				return -1; 
			}
	}
	
	
	
	public int AddToCart (int CustomerId, int ProductId) {

		try(Connection con = getConnection()){
			PreparedStatement stm = con.prepareStatement(StringUtils.ADD_TO_CART);
			
			stm.setInt(1, CustomerId );
			stm.setInt(2, ProductId);
			
			
			// Execute the update statement and store the number of affected rows
			int result = stm.executeUpdate();
			// Check if the update was successful (i.e., at least one row affected)
			if (result > 0) {
				return 1; // Sucessfully added
			} else {
				return 0; // Failde
				}

			} catch (ClassNotFoundException | SQLException ex) {
			// Print the stack trace for debugging purposes
				ex.printStackTrace();
				return -1; 
			}
	}
	
	
	
}

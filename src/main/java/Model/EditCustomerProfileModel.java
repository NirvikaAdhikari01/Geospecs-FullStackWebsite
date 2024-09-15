package Model;

public class EditCustomerProfileModel {
	
	private int CustomerId;
	private String first_name;
	private String last_name;
	private String email;
	private String number;
	private String password;
	
	
	
	
	public EditCustomerProfileModel(int CustomerId, String first_name, String last_name, String email, String number, String password) {
		super();
		this.CustomerId = CustomerId;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.number = number;
		this.password = password;
		
	}




	public int getCustomerId() {
		return CustomerId;
	}




	public void setCustomerId(int customerId) {
		CustomerId = customerId;
	}




	public String getFirst_name() {
		return first_name;
	}




	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}




	public String getLast_name() {
		return last_name;
	}




	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}




	public String getEmail() {
		return email;
	}




	public void setEmail(String email) {
		this.email = email;
	}




	public String getNumber() {
		return number;
	}




	public void setNumber(String number) {
		this.number = number;
	}




	public String getPassword() {
		return password;
	}




	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}

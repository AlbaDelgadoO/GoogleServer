package es.deusto.sd.strava.dto;

public class LoginDTO {
	
	private String email;
	private String pass;
	
	// Constructor
	public LoginDTO() {	}
	
	// Constructor with parameters
	public LoginDTO(String email, String pass) {
		super();
		this.email = email;
		this.pass = pass;
	}
	
	// Getters and Setters
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPass() {
		return pass;
	}
	
	public void setPass(String pass) {
		this.pass = pass;
	}
}

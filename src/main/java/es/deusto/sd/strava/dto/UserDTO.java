package es.deusto.sd.strava.dto;

public class UserDTO {
	
	private String name;
	private String birthdate;
	private String height;
	private String weight;
	private int maxHR;
	private int restHR;
	private String email;
	
	// Constructor
	public UserDTO() {	}
	
	// Constructor with parameters
	public UserDTO(String name, String birthdate, String height, String weight, int maxHR, int restHR, String email) {
		super();
		this.name = name;
		this.birthdate = birthdate;
		this.height = height;
		this.weight = weight;
		this.maxHR = maxHR;
		this.restHR = restHR;
		this.email = email;
	}

	// Getters and Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public int getMaxHR() {
		return maxHR;
	}

	public void setMaxHR(int maxHR) {
		this.maxHR = maxHR;
	}

	public int getRestHR() {
		return restHR;
	}

	public void setRestHR(int restHR) {
		this.restHR = restHR;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}

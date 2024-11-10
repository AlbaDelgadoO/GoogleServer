package es.deusto.sd.strava.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDTO {
	
    @JsonProperty("name")
    private String name;
    
    @JsonProperty("email")
    private String email;
    
    @JsonProperty("password")
    private String password;
    
    @JsonProperty("birthdate")
    private String birthdate;
    
    @JsonProperty("weight")
    private String weight;
    
    @JsonProperty("height")
    private String height;
    
    @JsonProperty("maxHR")
    private Integer maxHR;
    
    @JsonProperty("restHR")
    private Integer restHR;
	
	// Constructor
	public UserDTO() {	}
	
	// Constructor with parameters
	public UserDTO(String name, String birthdate, String height, String weight, Integer maxHR, Integer restHR, String email, String password) {
		super();
		this.name = name;
		this.birthdate = birthdate;
		this.height = height;
		this.weight = weight;
		this.maxHR = maxHR;
		this.restHR = restHR;
		this.email = email;
		this.password = password;
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

	public Integer getMaxHR() {
		return maxHR;
	}

	public void setMaxHR(Integer maxHR) {
		this.maxHR = maxHR;
	}

	public Integer getRestHR() {
		return restHR;
	}

	public void setRestHR(Integer restHR) {
		this.restHR = restHR;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	@Override
	public String toString() {
		return "UserDTO [name=" + name + ", email=" + email + ", password=" + password + ", birthdate=" + birthdate
				+ ", weight=" + weight + ", height=" + height + ", maxHR=" + maxHR + ", restHR=" + restHR + "]";
	}
}

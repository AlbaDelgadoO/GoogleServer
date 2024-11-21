package es.deusto.sd.strava.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import es.deusto.sd.strava.dto.UserDTO;

public class User {

	private String name;
	private String birthdate;
	private String height;
	private String weight;
	private Integer maxHR;
	private Integer restHR;
	private String email;
	private String password;
	private String accountType;
	private List<TrainingSession> trainingSessions = new ArrayList<>();
	
	// Constructor without parameters
	public User() {}
	
	// Constructor with parameters
	public User(String name, String birthdate, String height, String weight, Integer maxHR, Integer restHR, String email, String password, String accountType) {
		this.name = name;
		this.birthdate = birthdate;
		this.height = height;
		this.weight = weight;
		this.maxHR = maxHR;
		this.restHR = restHR;
		this.email = email;
		this.password = password;
	}
	
    public User(UserDTO userDTO) {
        this.name = userDTO.getName();
        this.email = userDTO.getEmail();
        this.birthdate = userDTO.getBirthdate();
        this.weight = userDTO.getWeight();
        this.height = userDTO.getHeight();
        this.maxHR = userDTO.getMaxHR();
        this.restHR = userDTO.getRestHR();
        this.password = userDTO.getPassword();
        this.accountType = userDTO.getAccountType();
    }

	//  Getters and setters
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
	
	public String getAccountType() {
		return accountType;
	}
	
	public void setAccountType(String accountType) {
        this.accountType = accountType;
	}

	public List<TrainingSession> getTrainingSessions() {
		return trainingSessions;
	}

	public void setTrainingSessions(List<TrainingSession> trainingSessions) {
		this.trainingSessions = trainingSessions;
	}

	// hashCode and equals
	@Override
	public int hashCode() {
		return Objects.hash(email, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email) && 
			   Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", birthdate=" + birthdate + ", height=" + height + ", weight=" + weight
				+ ", maxHR=" + maxHR + ", restHR=" + restHR + ", email=" + email + ", password=" + password + ", accountType=" + accountType + "]";
	}
	
}
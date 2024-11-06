/**
 * This code is based on solutions provided by ChatGPT 4o and 
 * adapted using GitHub Copilot. It has been thoroughly reviewed 
 * and validated to ensure correctness and that it is free of errors.
 */
package es.deusto.sd.strava.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {

	private String name;
	private String birthdate;
	private String height;
	private String weight;
	private int maxHR;
	private int restHR;
	private String email;
	private List<TrainingSession> trainingSessions = new ArrayList<>();
	
	// Constructor without parameters
	public User() {}
	
	// Constructor with parameters
	public User(String name, String birthdate, String height, String weight, int maxHR, int restHR, String email) {
		this.name = name;
		this.birthdate = birthdate;
		this.height = height;
		this.weight = weight;
		this.maxHR = maxHR;
		this.restHR = restHR;
		this.email = email;
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
}
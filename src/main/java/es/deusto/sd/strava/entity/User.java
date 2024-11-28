package es.deusto.sd.strava.entity;

import java.util.ArrayList;

import java.util.List;
import java.util.Objects;

import es.deusto.sd.strava.dto.UserDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String birthdate;

    @Column(nullable = false)
    private String height;

    @Column(nullable = false)
    private String weight;

    private Integer maxHR;
    private Integer restHR;

    @Column(nullable = false)
    private String accountType;

    // One-to-Many relationship with TrainingSession
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TrainingSession> trainingSessions = new ArrayList<>();

    // Many-to-Many relationship with Challenge
    @ManyToMany
    @JoinTable(
        name = "user_challenges", // Tabla intermedia
        joinColumns = @JoinColumn(name = "user_id"), // Clave foránea hacia "User"
        inverseJoinColumns = @JoinColumn(name = "challenge_id") // Clave foránea hacia "Challenge"
    )
    private List<Challenge> acceptedChallenges = new ArrayList<>();
    
	// Constructor without parameters
	public User() {}
	
	// Constructor with parameters
	public User(String name, String birthdate, String height, String weight, Integer maxHR, Integer restHR, String email, String accountType) {
		this.name = name;
		this.birthdate = birthdate;
		this.height = height;
		this.weight = weight;
		this.maxHR = maxHR;
		this.restHR = restHR;
		this.email = email;
		this.accountType = accountType;
	}
	
    public User(UserDTO userDTO) {
        this.name = userDTO.getName();
        this.email = userDTO.getEmail();
        this.birthdate = userDTO.getBirthdate();
        this.weight = userDTO.getWeight();
        this.height = userDTO.getHeight();
        this.maxHR = userDTO.getMaxHR();
        this.restHR = userDTO.getRestHR();
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
				+ ", maxHR=" + maxHR + ", restHR=" + restHR + ", email=" + email + ", accountType=" + accountType + "]";
	}
	
}
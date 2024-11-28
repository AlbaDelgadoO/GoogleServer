package es.deusto.sd.strava.entity;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "challenges")
public class Challenge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date endDate;

    private Double targetDistance;
    private Double targetTime;

    @Column(nullable = false)
    private String sport;

    // Many-to-Many relationship with User
    @ManyToMany(mappedBy = "acceptedChallenges")
    private List<User> acceptedUsers;

	// Constructor without parameters
	public Challenge() {
	}
	
	public Challenge(String name, Date startDate, Date endDate, Double targetDistance, Double targetTime, String sport) {
	    this.name = name;
	    this.startDate = startDate;
	    this.endDate = endDate;
	    this.targetDistance = targetDistance;
	    this.targetTime = targetTime;
	    this.sport = sport;

        if (this.targetDistance == null && this.targetTime == null) {
                throw new IllegalArgumentException("Challenge must have either targetDistance or targetTime.");
            }

        if (this.targetDistance != null) {
            if (this.targetDistance <= 0) {
                throw new IllegalArgumentException("Target distance must be greater than 0.");
            }
        }

        if (this.targetTime != null) {
            if (this.targetTime <= 0) {
                throw new IllegalArgumentException("Target time must be greater than 0.");
            }
        }

        if (this.startDate == null || this.endDate == null) {
            throw new IllegalArgumentException("Start date and end date must not be null.");
        }

        if (this.startDate.after(this.endDate)) {
            throw new IllegalArgumentException("Start date must be before end date.");
        }

        if (this.sport == null || this.sport.isEmpty()) {
            throw new IllegalArgumentException("Sport must not be null or empty.");
        }
    }

	// Getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Double getTargetDistance() {
		return targetDistance;
	}

	public void setTargetDistance(Double targetDistance) {
		this.targetDistance = targetDistance;
	}

	public Double getTargetTime() {
		return targetTime;
	}

	public void setTargetTime(Double targetTime) {
		this.targetTime = targetTime;
	}

	public String getSport() {
		return sport;
	}

	public void setSport(String sport) {
		this.sport = sport;
	}
	
	public List<User> getAcceptedUsers() {
		return acceptedUsers;
	}

	public void setAcceptedUsers(List<User> acceptedUsers) {
		this.acceptedUsers = acceptedUsers;
	}

	// hashCode and equals
	@Override
    public int hashCode() {
        return Objects.hash(name, startDate, endDate);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Challenge))
            return false;
        Challenge other = (Challenge) obj;
        return Objects.equals(name, other.name) &&
               Objects.equals(startDate, other.startDate) &&
               Objects.equals(endDate, other.endDate);
    }
}
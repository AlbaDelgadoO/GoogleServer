package es.deusto.sd.strava.entity;
import java.util.Date;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "training_sessions")
public class TrainingSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String sport;

    @Column(nullable = false)
    private double distanceKm;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @Column(nullable = false)
    private double startTime;

    @Column(nullable = false)
    private double durationMin;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

	// Constructor without parameters
	public TrainingSession() {
	}
	
	// Constructor with parameters
	public TrainingSession(String title, String sport, double distanceKm, Date startDate, double startTime,
			double durationMin) {
		super();
		this.title = title;
		this.sport = sport;
		this.distanceKm = distanceKm;
		this.startDate = startDate;
		this.startTime = startTime;
		this.durationMin = durationMin;
	}
	
	// Getters and setters
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
        this.title = title;
    }
	
	public String getSport() {
		return sport;
	}

	public void setSport(String sport) {
		this.sport = sport;
	}

	public double getDistanceKm() {
		return distanceKm;
	}

	public void setDistanceKm(float distanceKm) {
		this.distanceKm = distanceKm;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public double getStartTime() {
		return startTime;
	}

	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	public double getDurationMin() {
		return durationMin;
	}

	public void setDurationMin(int durationMin) {
		this.durationMin = durationMin;
	}

	// hashCode and equals
	@Override
    public int hashCode() {
        return Objects.hash(title, startDate, startTime);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof TrainingSession))
            return false;
        TrainingSession other = (TrainingSession) obj;
        return Objects.equals(title, other.title) &&
               Objects.equals(startDate, other.startDate) &&
               Objects.equals(startTime, other.startTime);
    }
}
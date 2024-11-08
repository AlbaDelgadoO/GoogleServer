package es.deusto.sd.strava.entity;
import java.util.Date;
import java.util.Objects;

public class TrainingSession {
    private String title;
    private String sport;
    private double distanceKm;
    private Date startDate;
    private double startTime;
    private double durationMin;

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
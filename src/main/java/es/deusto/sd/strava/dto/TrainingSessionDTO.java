package es.deusto.sd.strava.dto;

import java.util.Date;

import es.deusto.sd.strava.entity.TrainingSession;

public class TrainingSessionDTO {
    private String title;
    private String sport;
    private double distance;
    private Date startDate;
    private double startTime;
    private double duration;

    // Constructor
    
    public TrainingSessionDTO() { }
    
    public TrainingSessionDTO(String title, String sport, double distance, Date startDate, double startTime, double duration) {
        this.title = title;
        this.sport = sport;
        this.distance = distance;
        this.startDate = startDate;
        this.startTime = startTime;
        this.duration = duration;
    }

    public TrainingSessionDTO(TrainingSession session) {
		// TODO Auto-generated constructor stub
    	this.title = session.getTitle();
        this.sport = session.getSport();
        this.distance = session.getDistanceKm();
        this.startDate = session.getStartDate();
        this.startTime = session.getStartTime();
        this.duration = session.getDurationMin();
	}

	// Getters y Setters
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

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
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

	public void setStartTime(double startTime) {
		this.startTime = startTime;
	}

	public double getDuration() {
		return duration;
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}
}

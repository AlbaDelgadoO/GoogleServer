package es.deusto.sd.strava.dto;

import java.util.Date;

import es.deusto.sd.strava.entity.Challenge;

public class ChallengeDTO {
	private String name;
	private Date startDate;
	private Date endDate;
	private Double targetDistance;
	private Double targetTime;
	private String sport;

    // Constructor
    public ChallengeDTO() { }
    
    // Constructor with parameters
	public ChallengeDTO(String name, Date startDate, Date endDate, Double targetDistance, Double targetTime,
			String sport) {
		this.name = name;
		this.startDate = (Date) startDate;
		this.endDate = (Date) endDate;
		this.targetDistance = targetDistance;
		this.targetTime = targetTime;
		this.sport = sport;
	}

	public ChallengeDTO(Challenge challenge) {
	    this.name = challenge.getName();  
	    this.startDate = challenge.getStartDate();  
	    this.endDate = challenge.getEndDate();  
	    this.targetDistance = challenge.getTargetDistance();
	    this.targetTime = challenge.getTargetTime();
	    this.sport = challenge.getSport(); 	
	}

	// Getters and Setters
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

	public double getTargetDistance() {
		return targetDistance;
	}

	public void setTargetDistance(Double targetDistance) {
		this.targetDistance = targetDistance;
	}

	public double getTargetTime() {
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
}

package es.deusto.sd.strava.dto;

import java.util.Date;

import es.deusto.sd.strava.entity.Challenge;

public class ChallengeDTO {
	private String name;
	private Date startDate;
	private Date endDate;
	private double targetDistance;
	private double targetTime;
	private String sport;

    // Constructor
    public ChallengeDTO() { }
    
    // Constructor with parameters
	public ChallengeDTO(String name, Date startDate, Date endDate, double targetDistance, double targetTime,
			String sport) {
		this.name = name;
		this.startDate = (Date) startDate;
		this.endDate = (Date) endDate;
		this.targetDistance = targetDistance;
		this.targetTime = targetTime;
		this.sport = sport;
	}

	public ChallengeDTO(String string, String string2, String string3, double d, double e, String string4) {
		// TODO Auto-generated constructor stub
	}

	public ChallengeDTO(Challenge challenge) {
		// TODO Auto-generated constructor stub
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

	public void setTargetDistance(float targetDistance) {
		this.targetDistance = targetDistance;
	}

	public double getTargetTime() {
		return targetTime;
	}

	public void setTargetTime(float targetTime) {
		this.targetTime = targetTime;
	}

	public String getSport() {
		return sport;
	}

	public void setSport(String sport) {
		this.sport = sport;
	}
}

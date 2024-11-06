/**
 * This code is based on solutions provided by ChatGPT 4o and 
 * adapted using GitHub Copilot. It has been thoroughly reviewed 
 * and validated to ensure correctness and that it is free of errors.
 */
package es.deusto.sd.strava.entity;

import java.util.Date;
import java.util.Objects;

public class Challenge {
	private String name;
	private Date startDate;
	private Date endDate;
	private double targetDistance;
	private double targetTime;
	private String sport;

	// Constructor without parameters
	public Challenge() {
	}

	// Constructor with parameters
	public Challenge(String name, Date startDate, Date endDate, double targetDistance, double targetTime, String sport) {
		super();
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.targetDistance = targetDistance;
		this.targetTime = targetTime;
		this.sport = sport;
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

	// hashCode and equals
	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Challenge other = (Challenge) obj;
		return Objects.equals(name, other.name);
	}
}
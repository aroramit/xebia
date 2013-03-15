package com.xebia.calories.entities;

/**
 * Represents the duration of an exercise routine
 * @author Amit
 */
public class Duration {

	private final Long durationInSeconds;
	
	public Duration(Long duration) {
		this.durationInSeconds = duration;
	}
	
	public Long getValue() {
		return this.durationInSeconds;
	}
	
	public Long getValueInMinutes() {
		return this.durationInSeconds / 60;
	}
	
	@Override
	public boolean equals(Object arg0) {
		return arg0 instanceof Duration
			&& this.durationInSeconds.equals(((Duration) arg0).durationInSeconds);
	}
	
	@Override
	public int hashCode() {
		int hashCode = 5;
		return hashCode * 31 + (int) (this.durationInSeconds ^ (this.durationInSeconds >>> 32));
	}
	
	@Override
	public String toString() {
		return this.getValueInMinutes() + " mins";
	}
}

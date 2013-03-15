package com.xebia.calories.entities;

/**
 * Represents the calories expended
 * @author Amit
 */
public class Calories {

	private final Double valueInKCal;
	
	public Calories(Double caloriesInKCal) {
		this.valueInKCal = caloriesInKCal;
		if (this.valueInKCal < 0.) {
			throw new IllegalArgumentException("Illegal value for calories burned");
		}
	}
	
	public Double getValueInKCalories() {
		return this.valueInKCal;
	}
	
	@Override
	public String toString() {
		return this.valueInKCal + " kCal";
	}
}

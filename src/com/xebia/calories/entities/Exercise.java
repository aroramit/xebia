package com.xebia.calories.entities;

/**
 * Types of exercises
 * @author Amit
 */
public enum Exercise {

	SWIMMING(10.) {
		@Override
		public boolean isWaterBased() {
			return true;
		}
	},
	RUNNING(20.),
	CYCLING(10.),
	JUMPING(10.),
	SKIPPING(15.),
	COMPOUND(15.);
	
	public boolean isWaterBased() {
		return false;
	}
	
	private Exercise(double kCalPerMinute) {
		this.kCalPerMinute = kCalPerMinute;
	}
	
	public double getKCalPerUnit() {
		return this.kCalPerMinute;
	}
	
	private double kCalPerMinute;
}

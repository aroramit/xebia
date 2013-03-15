package com.xebia.calories.entities;

import java.util.Date;

import com.xebia.calories.rules.CaloriesRule;

/**
 * Defines a unit exercise routine, which has a definite time, duration and exercise type
 * @author Amit
 */
public class UnitExerciseRoutine extends AbstractExerciseRoutine {

	private final Date time;
	private Exercise exercise;
	private Duration duration;
	
	public UnitExerciseRoutine(User user, Date time, Exercise exercise, Duration duration) {
		super(user);
		this.time = new Date(time.getTime());
		this.exercise = exercise;
		this.duration = duration;
	}
	
	public UnitExerciseRoutine(User user, Date time, Exercise exercise, Duration duration, boolean isRoot) {
		super(user, isRoot);
		this.time = new Date(time.getTime());
		this.exercise = exercise;
		this.duration = duration;
	}

	@Override
	public Date getTime() {
		return new Date(time.getTime());
	}

	@Override
	public Exercise getType() {
		return this.exercise;
	}

	@Override
	public Duration getDuration() {
		return this.duration;
	}

	@Override
	public boolean isLeaf() {
		return true;
	}

	@Override
	public boolean isCompound() {
		return false;
	}

	@Override
	public boolean equals(Object arg0) {
		if (arg0 instanceof UnitExerciseRoutine) {
			UnitExerciseRoutine routine = (UnitExerciseRoutine) arg0;
			return this.time.equals(routine.time) 
				&& this.exercise.equals(routine.exercise) 
				&& this.duration.equals(routine.duration) 
				&& this.getUser().equals(routine.getUser());
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		int hashCode = 5;
		hashCode = hashCode * 31 + this.time.hashCode(); 
		hashCode = hashCode * 31 + this.exercise.hashCode(); 
		hashCode = hashCode * 31 + this.duration.hashCode(); 
		hashCode = hashCode * 31 + this.getUser().hashCode();
		return hashCode;
	}

	@Override
	public void addRoutine(ExerciseRoutine routine) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void removeRoutine(ExerciseRoutine routine) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Calories getCalories() {
		double kCalTotal = (this.getAdjustmentFactor() * this.exercise.getKCalPerUnit() * this.duration.getValueInMinutes())
			+ (this.getAdjustmentFactor() > 0. ? this.getAdjustmentComponent() : 0.);
		return new Calories(kCalTotal);
	}

	@Override
	public void acceptRule(CaloriesRule rule) {
		rule.apply(this);
	}
}

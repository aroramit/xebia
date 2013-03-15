package com.xebia.calories.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.xebia.calories.rules.CaloriesRule;

/**
 * Defines a composite exercise routine, which may contain one or more simple or compsoite exercise routines
 * @author Amit
 */
public class CompositeExerciseRoutine extends AbstractExerciseRoutine {

	private List<ExerciseRoutine> routines = new ArrayList<ExerciseRoutine>();
	
	public CompositeExerciseRoutine(User user, boolean isRoot) {
		super(user, isRoot);
	}

	public CompositeExerciseRoutine(User user) {
		this(user, false);
	}
	
	@Override
	public Date getTime() {
		Date time = null;
		for (ExerciseRoutine routine : this.routines) {
			if (time == null || routine.getTime().before(time)) {
				time = routine.getTime();
			}
		}
		return time;
	}

	@Override
	public Exercise getType() {
		Exercise type = null;
		for (ExerciseRoutine routine : this.routines) {
			if (type == null) {
				type = routine.getType();
			}
			else if (!type.equals(routine.getType())) {
				type = Exercise.COMPOUND;
				break;
			}
		}
		
		return type;
	}

	@Override
	public Duration getDuration() {
		long duration = 0L;
		for (ExerciseRoutine routine : this.routines) {
			duration += routine.getDuration().getValue();
		}
		return new Duration(duration);
	}

	@Override
	public boolean isLeaf() {
		return false;
	}

	@Override
	public boolean isCompound() {
		return true;
	}

	@Override
	public void addRoutine(ExerciseRoutine routine) {
		if (routine.isDaysRoutine())
			throw new IllegalStateException("Cannot assign a parent to root");
		// TODO: handle routines cutting through the 12pm
		this.routines.add(routine);
	}

	@Override
	public void removeRoutine(ExerciseRoutine routine) {
		this.removeRoutine(routine);
	}

	@Override
	public Calories getCalories() {
		double totalKCals = 0.;
		for (ExerciseRoutine routine : this.routines) {
			totalKCals += routine.getCalories().getValueInKCalories();
		}
		totalKCals = (totalKCals * this.getAdjustmentFactor())
			+ (this.getAdjustmentFactor() > 0. ? this.getAdjustmentComponent() : 0.);
		return new Calories(totalKCals);
	}

	@Override
	public void acceptRule(CaloriesRule rule) {
		for (ExerciseRoutine routine : this.routines) {
			routine.acceptRule(rule);
		}
		rule.apply(this);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof CompositeExerciseRoutine) {
			CompositeExerciseRoutine exerciseRoutine = (CompositeExerciseRoutine) obj;
			return this.getUser().equals(exerciseRoutine.getUser())
				&& this.routines.containsAll(exerciseRoutine.routines)
				&& exerciseRoutine.routines.containsAll(this.routines);
		}
		return false;
	}
}

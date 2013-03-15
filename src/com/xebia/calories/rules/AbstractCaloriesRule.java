package com.xebia.calories.rules;

import com.xebia.calories.entities.ExerciseRoutine;

public abstract class AbstractCaloriesRule implements CaloriesRule {

	@Override
	public void apply(ExerciseRoutine routine) {
		if (this.isApplicable(routine)) {
			this.applyRule(routine);
		}
	}
	
	protected abstract void applyRule(ExerciseRoutine routine);
}

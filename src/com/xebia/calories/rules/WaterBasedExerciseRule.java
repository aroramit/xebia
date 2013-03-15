package com.xebia.calories.rules;

import com.xebia.calories.entities.ExerciseRoutine;

/**
 * All water based exercises would mean spending an additional 5 calories per unit exercise routine
 * @author Amit
 */
public class WaterBasedExerciseRule extends AbstractCaloriesRule {

	private static final double WATER_BASED_EXERCISE_ADJUSTMENT_COMPONENT = 5.;

	@Override
	public boolean isApplicable(ExerciseRoutine routine) {
		return routine.isLeaf() && routine.getType().isWaterBased();
	}

	@Override
	public void applyRule(ExerciseRoutine routine) {
		routine.addAdjustmentComponent(WATER_BASED_EXERCISE_ADJUSTMENT_COMPONENT);
	}
}

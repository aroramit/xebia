package com.xebia.calories.rules;

import com.xebia.calories.entities.ExerciseRoutine;

/**
 * If the total time spent doing the exercise during the entire day was more than 2 hours,
 * an additional 5% calories would be expended
 * @author Amit
 */
public class TotalDurationRule extends AbstractCaloriesRule {

	private static final double TOTAL_DURATION_RULE_ADJUSTMENT = 1.05;
	private static final int TOTAL_DURATION_RULE_BARRIER_IN_MINS = 120;

	@Override
	public boolean isApplicable(ExerciseRoutine routine) {
		return routine.isDaysRoutine()
			&& routine.getDuration().getValueInMinutes() > TOTAL_DURATION_RULE_BARRIER_IN_MINS;
	}

	@Override
	public void applyRule(ExerciseRoutine routine) {
		routine.addAdjustmentFactor(TOTAL_DURATION_RULE_ADJUSTMENT);
	}
}

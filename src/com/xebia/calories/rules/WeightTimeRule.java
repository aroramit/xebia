package com.xebia.calories.rules;

import com.xebia.calories.entities.ExerciseRoutine;
import com.xebia.calories.entities.User;

/**
 * If the weight of the user is less than 40 kilos, all exercise routines which have a duration of less
 * than 15 mins will be disregarded
 * @author Amit
 */
public class WeightTimeRule extends AbstractCaloriesRule {

	private static final double WEIGHT_RULE_MINIMUM_WEIGHT_IN_KILOS = 40.;
	private static final int WEIGHT_RULE_MINIMUM_TIME_IN_MINUTES = 15;
	
	private static final double WEIGHT_RULE_ADJUSTMENT_FACTOR = 0.;
	
	
	@Override
	public boolean isApplicable(ExerciseRoutine routine) {
		if (routine.isLeaf()) {
			User user = routine.getUser(); 
			long minutes = routine.getDuration().getValueInMinutes();
			return user.getWeightInKilos() < WEIGHT_RULE_MINIMUM_WEIGHT_IN_KILOS
				&& minutes < WEIGHT_RULE_MINIMUM_TIME_IN_MINUTES; 
		}
		return false;
	}

	@Override
	public void applyRule(ExerciseRoutine routine) {
		routine.addAdjustmentFactor(WEIGHT_RULE_ADJUSTMENT_FACTOR);
	}
}

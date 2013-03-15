package com.xebia.calories.rules;

import java.util.Calendar;
import java.util.Date;

import com.xebia.calories.entities.ExerciseRoutine;

/**
 * If an exercise has been performed during morning, an additional 10% calories would be spent
 * @author Amit
 */
public class MorningRoutineRule extends AbstractCaloriesRule {

	private static final double MORNING_RULE_ADJUSTMENT_FACTOR = 1.1;

	@Override
	public boolean isApplicable(ExerciseRoutine routine) {
		return routine.isLeaf() && this.isMorningRoutine(routine);
	}

	private boolean isMorningRoutine(ExerciseRoutine routine) {
		Date time = routine.getTime();

		Calendar beforeCal = Calendar.getInstance();
		beforeCal.setTime(time);
		beforeCal.set(Calendar.HOUR_OF_DAY, 0);
		beforeCal.set(Calendar.MINUTE, 0);
		beforeCal.set(Calendar.SECOND, 0);
		
		Calendar afterCal = Calendar.getInstance();
		afterCal.setTime(time);
		afterCal.set(Calendar.HOUR_OF_DAY, 12);
		afterCal.set(Calendar.MINUTE, 0);
		afterCal.set(Calendar.SECOND, 0);
		
		return beforeCal.getTime().before(time) && afterCal.getTime().after(time);
	}

	@Override
	public void applyRule(ExerciseRoutine routine) {
		routine.addAdjustmentFactor(MORNING_RULE_ADJUSTMENT_FACTOR);
	}
}

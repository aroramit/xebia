package com.xebia.calories.calculator;

import com.xebia.calories.entities.Calories;
import com.xebia.calories.entities.ExerciseRoutine;

/**
 * Defines a contract for Calories Calculator
 * @author Amit
 */
public interface CaloriesCalculator {

	/**
	 * Calculate and return the calories expended performing the given exercise routine
	 * @param routine
	 * @return
	 */
	Calories calculate(ExerciseRoutine routine);
}

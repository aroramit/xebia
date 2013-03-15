package com.xebia.calories.rules;

import com.xebia.calories.entities.ExerciseRoutine;

/**
 * A Calories Rule is an algorithm, which based on certain conditions can alter the adjustment data
 * of an exercise routine
 * @author Amit
 */
public interface CaloriesRule {

	/**
	 * Returns a boolean flag indicating whether this rule is applicable for the given
	 * exercise routine
	 * @param routine
	 * @return boolean
	 */
	boolean isApplicable(ExerciseRoutine routine);
	
	/**
	 * Execute the rule on the given exercise routine. This method should first verify whether the rule
	 * is applicable or not before execution
	 * @param routine
	 */
	void apply(ExerciseRoutine routine);
}

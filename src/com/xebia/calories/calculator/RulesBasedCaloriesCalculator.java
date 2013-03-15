package com.xebia.calories.calculator;

import java.util.ArrayList;
import java.util.List;

import com.xebia.calories.entities.Calories;
import com.xebia.calories.entities.ExerciseRoutine;
import com.xebia.calories.rules.CaloriesRule;
import com.xebia.calories.rules.MorningRoutineRule;
import com.xebia.calories.rules.TotalDurationRule;
import com.xebia.calories.rules.WaterBasedExerciseRule;
import com.xebia.calories.rules.WeightTimeRule;

/**
 * Provides a rules based implementation for CaloriesCalculator
 * @author Amit
 */
public class RulesBasedCaloriesCalculator implements CaloriesCalculator {

	List<CaloriesRule> rules = new ArrayList<CaloriesRule>();
	
	public RulesBasedCaloriesCalculator() {
		this.rules.add(new MorningRoutineRule());
		this.rules.add(new WeightTimeRule());
		this.rules.add(new WaterBasedExerciseRule());
		this.rules.add(new TotalDurationRule());
	}
	
	@Override
	public Calories calculate(ExerciseRoutine routine) {
		for (CaloriesRule rule : this.rules) {
			routine.acceptRule(rule);
		}
		return routine.getCalories();
	}
}

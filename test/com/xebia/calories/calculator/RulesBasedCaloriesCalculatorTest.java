package com.xebia.calories.calculator;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.xebia.calories.entities.Calories;
import com.xebia.calories.entities.CompositeExerciseRoutine;
import com.xebia.calories.entities.Duration;
import com.xebia.calories.entities.Exercise;
import com.xebia.calories.entities.ExerciseRoutine;
import com.xebia.calories.entities.UnitExerciseRoutine;
import com.xebia.calories.entities.User;

public class RulesBasedCaloriesCalculatorTest {

	private static CaloriesCalculator rulesBasedCalculator;
	private User user;
	private Calendar calendar;
	private Duration duration;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		rulesBasedCalculator = new RulesBasedCaloriesCalculator();
	}

	@Before
	public void setUp() throws Exception {
		user = new User("Test User", 100.);
		calendar = Calendar.getInstance();
	}

	@Test
	public void testCalculateForANonWaterMorningRoutine() {
		duration = new Duration(1800L);
		calendar.set(Calendar.HOUR_OF_DAY, 9);
		calendar.set(Calendar.MINUTE, 30);
		calendar.set(Calendar.SECOND, 0);
		
		ExerciseRoutine routine = new UnitExerciseRoutine(user, calendar.getTime(), Exercise.RUNNING, duration, true);
		Calories calories = rulesBasedCalculator.calculate(routine);
		assertEquals(660., calories.getValueInKCalories(), 0.01);
	}
	
	@Test
	public void testCalculateForAWaterBasedMorningRoutine() {
		duration = new Duration(1800L);
		calendar.set(Calendar.HOUR_OF_DAY, 9);
		calendar.set(Calendar.MINUTE, 30);
		calendar.set(Calendar.SECOND, 0);
		
		ExerciseRoutine routine = new UnitExerciseRoutine(user, calendar.getTime(), Exercise.SWIMMING, duration, true);
		Calories calories = rulesBasedCalculator.calculate(routine);
		assertEquals(335., calories.getValueInKCalories(), 0.01);
	}
	
	@Test
	public void testCalculateForANonWaterLongRoutine() {
		duration = new Duration(8400L);
		calendar.set(Calendar.HOUR_OF_DAY, 13);
		calendar.set(Calendar.MINUTE, 30);
		calendar.set(Calendar.SECOND, 0);
		
		ExerciseRoutine routine = new UnitExerciseRoutine(user, calendar.getTime(), Exercise.CYCLING, duration, true);
		Calories calories = rulesBasedCalculator.calculate(routine);
		assertEquals(1470., calories.getValueInKCalories(), 0.01);
	}
	
	@Test
	public void testCalculateForAWaterBasedLongRoutine() {
		duration = new Duration(8400L);
		calendar.set(Calendar.HOUR_OF_DAY, 13);
		calendar.set(Calendar.MINUTE, 30);
		calendar.set(Calendar.SECOND, 0);
		
		ExerciseRoutine routine = new UnitExerciseRoutine(user, calendar.getTime(), Exercise.SWIMMING, duration, true);
		Calories calories = rulesBasedCalculator.calculate(routine);
		assertEquals(1475., calories.getValueInKCalories(), 0.01);
	}
	
	@Test
	public void testCalculateForACompositeRoutine() {
		ExerciseRoutine routine = new CompositeExerciseRoutine(user, true);
		
		duration = new Duration(1800L);
		calendar.set(Calendar.HOUR_OF_DAY, 9);
		calendar.set(Calendar.MINUTE, 30);
		calendar.set(Calendar.SECOND, 0);
		ExerciseRoutine routine1 = new UnitExerciseRoutine(user, calendar.getTime(), Exercise.SWIMMING, duration); // 335
		
		duration = new Duration(3600L);
		calendar.set(Calendar.HOUR_OF_DAY, 10);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		ExerciseRoutine routine2 = new UnitExerciseRoutine(user, calendar.getTime(), Exercise.CYCLING, duration); // 660
		
		duration = new Duration(1800L);
		calendar.set(Calendar.HOUR_OF_DAY, 17);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		ExerciseRoutine routine31 = new UnitExerciseRoutine(user, calendar.getTime(), Exercise.SWIMMING, duration); // 305
		
		duration = new Duration(1800L);
		calendar.set(Calendar.HOUR_OF_DAY, 17);
		calendar.set(Calendar.MINUTE, 30);
		calendar.set(Calendar.SECOND, 0);
		ExerciseRoutine routine32 = new UnitExerciseRoutine(user, calendar.getTime(), Exercise.RUNNING, duration); // 600
		
		ExerciseRoutine routine3 = new CompositeExerciseRoutine(user, false);
		routine3.addRoutine(routine31);
		routine3.addRoutine(routine32);
		
		routine.addRoutine(routine1);
		routine.addRoutine(routine2);
		routine.addRoutine(routine3);
		
		Calories calories = rulesBasedCalculator.calculate(routine);
		assertEquals(1995., calories.getValueInKCalories(), 0.01);
	}
	
	@Test
	public void testCalculateForACompositeRoutineWeakUser() {
		user = new User("Test User", 38.);
		ExerciseRoutine routine = new CompositeExerciseRoutine(user, true);
		
		duration = new Duration(600L);
		calendar.set(Calendar.HOUR_OF_DAY, 9);
		calendar.set(Calendar.MINUTE, 30);
		calendar.set(Calendar.SECOND, 0);
		ExerciseRoutine routine1 = new UnitExerciseRoutine(user, calendar.getTime(), Exercise.SWIMMING, duration); // 0
		
		duration = new Duration(3600L);
		calendar.set(Calendar.HOUR_OF_DAY, 10);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		ExerciseRoutine routine2 = new UnitExerciseRoutine(user, calendar.getTime(), Exercise.CYCLING, duration); // 660
		
		duration = new Duration(1800L);
		calendar.set(Calendar.HOUR_OF_DAY, 17);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		ExerciseRoutine routine31 = new UnitExerciseRoutine(user, calendar.getTime(), Exercise.SWIMMING, duration); // 305
		
		duration = new Duration(1800L);
		calendar.set(Calendar.HOUR_OF_DAY, 17);
		calendar.set(Calendar.MINUTE, 30);
		calendar.set(Calendar.SECOND, 0);
		ExerciseRoutine routine32 = new UnitExerciseRoutine(user, calendar.getTime(), Exercise.RUNNING, duration); // 600
		
		ExerciseRoutine routine3 = new CompositeExerciseRoutine(user, false);
		routine3.addRoutine(routine31);
		routine3.addRoutine(routine32);
		
		routine.addRoutine(routine1);
		routine.addRoutine(routine2);
		routine.addRoutine(routine3);
		
		Calories calories = rulesBasedCalculator.calculate(routine);
		assertEquals(1643.25, calories.getValueInKCalories(), 0.01);
	}

}

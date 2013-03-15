package com.xebia.calories.entities;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

public class CompositeExerciseRoutineTest {

	private CompositeExerciseRoutine routine;
	private Duration duration;
	private Calendar calendar = Calendar.getInstance();
	private User user;

	@Before
	public void setUp() throws Exception {
		user = new User("Test User", 100.);
		routine = new CompositeExerciseRoutine(user, true);
	}

	@Test
	public void testGetCaloriesWithNonDefaultAdjValues() {
		routine.addAdjustmentComponent(100.);
		routine.addAdjustmentFactor(0.5);

		calendar.set(Calendar.HOUR_OF_DAY, 9);
		calendar.set(Calendar.MINUTE, 30);
		calendar.set(Calendar.SECOND, 0);
		duration = new Duration(1800L);
		ExerciseRoutine routine1 = new UnitExerciseRoutine(user, calendar.getTime(), Exercise.CYCLING, duration);
		routine1.addAdjustmentComponent(10.);
		routine1.addAdjustmentFactor(1.5);
		routine.addRoutine(routine1);
		
		calendar.set(Calendar.HOUR_OF_DAY, 17);
		calendar.set(Calendar.MINUTE, 30);
		calendar.set(Calendar.SECOND, 0);
		duration = new Duration(900L);
		
		ExerciseRoutine routine2 = new UnitExerciseRoutine(user, calendar.getTime(), Exercise.SWIMMING, duration);
		routine2.addAdjustmentComponent(50.);
		routine2.addAdjustmentFactor(1.1);
		routine.addRoutine(routine2);
		
		assertEquals(460., routine1.getCalories().getValueInKCalories(), 0.01);
		assertEquals(215., routine2.getCalories().getValueInKCalories(), 0.01);
		assertEquals(437.5, routine.getCalories().getValueInKCalories(), 0.01);
	}
	
	@Test
	public void testGetCaloriesWithDefaultAdjValues() {
		
		calendar.set(Calendar.HOUR_OF_DAY, 9);
		calendar.set(Calendar.MINUTE, 30);
		calendar.set(Calendar.SECOND, 0);
		duration = new Duration(1800L);
		ExerciseRoutine routine1 = new UnitExerciseRoutine(user, calendar.getTime(), Exercise.CYCLING, duration);
		routine.addRoutine(routine1);
		
		calendar.set(Calendar.HOUR_OF_DAY, 17);
		calendar.set(Calendar.MINUTE, 30);
		calendar.set(Calendar.SECOND, 0);
		duration = new Duration(900L);
		
		ExerciseRoutine routine2 = new UnitExerciseRoutine(user, calendar.getTime(), Exercise.SWIMMING, duration);
		routine.addRoutine(routine2);
		
		assertEquals(300., routine1.getCalories().getValueInKCalories(), 0.01);
		assertEquals(150., routine2.getCalories().getValueInKCalories(), 0.01);
		assertEquals(450., routine.getCalories().getValueInKCalories(), 0.01);
	}

}

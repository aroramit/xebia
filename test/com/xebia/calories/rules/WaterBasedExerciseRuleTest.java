package com.xebia.calories.rules;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.xebia.calories.entities.CompositeExerciseRoutine;
import com.xebia.calories.entities.Duration;
import com.xebia.calories.entities.Exercise;
import com.xebia.calories.entities.ExerciseRoutine;
import com.xebia.calories.entities.UnitExerciseRoutine;
import com.xebia.calories.entities.User;

public class WaterBasedExerciseRuleTest {

	private static WaterBasedExerciseRule rule;
	private User user;
	private Calendar calendar;
	private Duration duration;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		rule = new WaterBasedExerciseRule();
	}

	@Before
	public void setUp() throws Exception {
		user = new User("Test User", 100.);
		calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 9);
		calendar.set(Calendar.MINUTE, 30);
		calendar.set(Calendar.SECOND, 0);
		duration = new Duration(1800L);
	}

	@Test
	public void testIsApplicableForANonWaterUnitRoutine() {
		duration = new Duration(1800L);
		ExerciseRoutine routine = new UnitExerciseRoutine(user, calendar.getTime(), Exercise.RUNNING, duration, true);
		assertTrue(!rule.isApplicable(routine));
		routine.acceptRule(rule);
		assertEquals(600., routine.getCalories().getValueInKCalories(), 0.01);
	}
	
	@Test
	public void testIsApplicableForAWaterBasedUnitRoutine() {
		duration = new Duration(1800L);
		ExerciseRoutine routine = new UnitExerciseRoutine(user, calendar.getTime(), Exercise.SWIMMING, duration, true);
		assertTrue(rule.isApplicable(routine));
		routine.acceptRule(rule);
		assertEquals(305., routine.getCalories().getValueInKCalories(), 0.01);
	}
	
	@Test
	public void testIsApplicableForACompoundRoutineWithWaterBasedExercise() {
		duration = new Duration(1800L);
		ExerciseRoutine routine1 = new UnitExerciseRoutine(user, calendar.getTime(), Exercise.RUNNING, duration);

		calendar.set(Calendar.HOUR_OF_DAY, 10);
		calendar.set(Calendar.MINUTE, 30);
		calendar.set(Calendar.SECOND, 10);
		duration = new Duration(1800L);
		ExerciseRoutine routine2 = new UnitExerciseRoutine(user, calendar.getTime(), Exercise.SWIMMING, duration);
		
		calendar.set(Calendar.HOUR_OF_DAY, 17);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		duration = new Duration(3600L);
		ExerciseRoutine routine31 = new UnitExerciseRoutine(user, calendar.getTime(), Exercise.CYCLING, duration);
		
		calendar.set(Calendar.HOUR_OF_DAY, 18);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		duration = new Duration(900L);
		ExerciseRoutine routine32 = new UnitExerciseRoutine(user, calendar.getTime(), Exercise.SKIPPING, duration);
		
		ExerciseRoutine routine3 = new CompositeExerciseRoutine(user, false);
		routine3.addRoutine(routine31);
		routine3.addRoutine(routine32);
		
		ExerciseRoutine routine = new CompositeExerciseRoutine(user, true);
		routine.addRoutine(routine1);
		routine.addRoutine(routine2);
		routine.addRoutine(routine3);
		assertTrue(!rule.isApplicable(routine));
		routine.acceptRule(rule);
		assertEquals(1730.0, routine.getCalories().getValueInKCalories(), 0.01);
	}
}

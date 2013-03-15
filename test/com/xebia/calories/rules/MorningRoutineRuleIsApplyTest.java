package com.xebia.calories.rules;

import static org.junit.Assert.assertEquals;

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

public class MorningRoutineRuleIsApplyTest {

	private static MorningRoutineRule rule;
	private User user;
	private Calendar calendar;
	private Duration duration;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		rule = new MorningRoutineRule();
	}

	@Before
	public void setUp() throws Exception {
		user = new User("Test User", 100.);
		calendar = Calendar.getInstance();
		duration = new Duration(1800L);
	}
	
	@Test
	public void testApplyForAUnitMorningRoutine() {
		calendar.set(Calendar.HOUR_OF_DAY, 9);
		calendar.set(Calendar.MINUTE, 30);
		calendar.set(Calendar.SECOND, 10);
		
		ExerciseRoutine routine = new UnitExerciseRoutine(user, calendar.getTime(), Exercise.RUNNING, duration);
		routine.acceptRule(rule);
		
		assertEquals(660., routine.getCalories().getValueInKCalories().doubleValue(), 0.);
	}
	
	@Test
	public void testApplyForAUnitEveningRoutine() {
		calendar.set(Calendar.HOUR_OF_DAY, 18);
		calendar.set(Calendar.MINUTE, 30);
		calendar.set(Calendar.SECOND, 10);
		
		ExerciseRoutine routine = new UnitExerciseRoutine(user, calendar.getTime(), Exercise.RUNNING, duration);
		routine.acceptRule(rule);
		
		assertEquals(600., routine.getCalories().getValueInKCalories().doubleValue(), 0.);
	}
	
	
	@Test
	public void testApplyForACompoundMorningRoutine() {
		calendar.set(Calendar.HOUR_OF_DAY, 9);
		calendar.set(Calendar.MINUTE, 30);
		calendar.set(Calendar.SECOND, 10);
		Duration duration1 = new Duration(1200L);
		
		ExerciseRoutine routine1 = new UnitExerciseRoutine(user, calendar.getTime(), Exercise.CYCLING, duration1);

		calendar.set(Calendar.HOUR_OF_DAY, 11);
		calendar.set(Calendar.MINUTE, 00);
		calendar.set(Calendar.SECOND, 29);
		Duration duration2 = new Duration(1800L);
		
		ExerciseRoutine routine2 = new UnitExerciseRoutine(user, calendar.getTime(), Exercise.SWIMMING, duration2);
		
		calendar.set(Calendar.HOUR_OF_DAY, 15);
		calendar.set(Calendar.MINUTE, 10);
		calendar.set(Calendar.SECOND, 0);
		Duration duration3 = new Duration(600L);
		
		ExerciseRoutine routine3 = new UnitExerciseRoutine(user, calendar.getTime(), Exercise.SKIPPING, duration3);
		
		ExerciseRoutine routine = new CompositeExerciseRoutine(user, true);
		routine.addRoutine(routine1);
		routine.addRoutine(routine2);
		routine.addRoutine(routine3);
		routine.acceptRule(rule);
		
		assertEquals(220., routine1.getCalories().getValueInKCalories().doubleValue(), 0.01);
		assertEquals(330., routine2.getCalories().getValueInKCalories().doubleValue(), 0.01);
		assertEquals(150., routine3.getCalories().getValueInKCalories().doubleValue(), 0.01);
		assertEquals(700., routine.getCalories().getValueInKCalories().doubleValue(), 0.01);
	}
	
	@Test
	public void testApplyForACompoundEveningRoutine() {
		calendar.set(Calendar.HOUR_OF_DAY, 13);
		calendar.set(Calendar.MINUTE, 30);
		calendar.set(Calendar.SECOND, 10);
		Duration duration1 = new Duration(1200L);
		
		ExerciseRoutine routine1 = new UnitExerciseRoutine(user, calendar.getTime(), Exercise.CYCLING, duration1);
		
		calendar.set(Calendar.HOUR_OF_DAY, 15);
		calendar.set(Calendar.MINUTE, 00);
		calendar.set(Calendar.SECOND, 29);
		Duration duration2 = new Duration(1800L);
		
		ExerciseRoutine routine2 = new UnitExerciseRoutine(user, calendar.getTime(), Exercise.SWIMMING, duration2);
		
		calendar.set(Calendar.HOUR_OF_DAY, 20);
		calendar.set(Calendar.MINUTE, 10);
		calendar.set(Calendar.SECOND, 0);
		Duration duration3 = new Duration(600L);
		
		ExerciseRoutine routine3 = new UnitExerciseRoutine(user, calendar.getTime(), Exercise.SKIPPING, duration3);
		
		ExerciseRoutine routine = new CompositeExerciseRoutine(user, true);
		routine.addRoutine(routine1);
		routine.addRoutine(routine2);
		routine.addRoutine(routine3);
		routine.acceptRule(rule);
		
		assertEquals(200., routine1.getCalories().getValueInKCalories().doubleValue(), 0.01);
		assertEquals(300., routine2.getCalories().getValueInKCalories().doubleValue(), 0.01);
		assertEquals(150., routine3.getCalories().getValueInKCalories().doubleValue(), 0.01);
		assertEquals(650., routine.getCalories().getValueInKCalories().doubleValue(), 0.01);
	}
}

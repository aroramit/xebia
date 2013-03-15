package com.xebia.calories.entities;

import java.util.Date;

import com.xebia.calories.rules.CaloriesRule;

/**
 * Defines a common contract for representing unit as well as composite exercise routines
 * Additionally, it has methods for getting the adjustment data and the accept() method for the rule visitor
 * @author Amit
 */
public interface ExerciseRoutine {

	/**
	 * Return the user object representing the person who performs the exercise routine
	 * @return
	 */
	User getUser();
	
	/**
	 * Return the time when the routine was performed
	 * @return
	 */
	Date getTime();
	
	/**
	 * Return the type of exercise performed in the routine
	 * @return
	 */
	Exercise getType();
	
	/**
	 * Return the duration of the routine
	 * @return
	 */
	Duration getDuration();
	
	/**
	 * Add a routine to a composite routine
	 * @param routine
	 */
	void addRoutine(ExerciseRoutine routine);
	
	/**
	 * Remove a routine from the composite routine
	 * @param routine
	 */
	void removeRoutine(ExerciseRoutine routine);
	
	/**
	 * Return true if this routine is a unit routine
	 * @return
	 */
	boolean isLeaf();
	
	/**
	 * Return true if this routine is a compound routine. Essentially, a NOT of isLeaf()
	 * @return
	 */
	boolean isCompound();
	
	/**
	 * Indicates whether this exercise routine is a compound/composite routine representing an
	 * entire day's exercise routines
	 * @return
	 */
	boolean isDaysRoutine();
	
	/**
	 * Apply the given adjustment factor to this exercise routine
	 * @param adjustment
	 */
	void addAdjustmentFactor(double adjustment);
	
	/**
	 * Apply the given adjustment component to this exercise routine
	 * @param adjustment
	 */
	void addAdjustmentComponent(double adjustment);
	
	/**
	 * Return the adjustment factor for this exercise routine
	 * @return
	 */
	double getAdjustmentFactor();
	
	/**
	 * Return the adjustment component for this exercise routine
	 * @return
	 */
	double getAdjustmentComponent();
	
	/**
	 * Return a Calories object representing the calories burned by performing this exercise
	 * routine. If it's a composite exercise routine, it should aggregate the calories burned
	 * by all its child exercise routines
	 * @return
	 */
	Calories getCalories();
	
	/**
	 * Acceps the given rule visitor
	 * @param rule
	 */
	void acceptRule(CaloriesRule rule);
}

package com.xebia.calories.entities;


public abstract class AbstractExerciseRoutine implements ExerciseRoutine {

	private User user;
	private double adjustmentFactor = 1.;
	private double adjustmentComponent = 0.;
	private boolean isRoot;

	public AbstractExerciseRoutine(User user) {
		this.user = user;
	}
	
	public AbstractExerciseRoutine(User user, boolean isRoot) {
		this(user);
		this.isRoot = isRoot;
	}
	
	@Override
	public User getUser() {
		return this.user;
	}

	@Override
	public void addAdjustmentFactor(double adjustment) {
		this.adjustmentFactor = this.adjustmentFactor * adjustment;
	}

	@Override
	public void addAdjustmentComponent(double adjustment) {
		this.adjustmentComponent = this.adjustmentComponent + adjustment;
	}
	
	@Override
	public double getAdjustmentFactor() {
		return this.adjustmentFactor;
	}
	
	@Override
	public double getAdjustmentComponent() {
		return this.adjustmentComponent;
	}

	@Override
	public boolean isDaysRoutine() {
		return this.isRoot;
	}
}

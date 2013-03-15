package com.xebia.calories.entities;

/**
 * Contains the details of the person performing the exercise routine
 * @author Amit
 */
public class User {

	private String name;
	private Double weight;
	
	public User(String name, Double weightInKilos) {
		this.name = name;
		this.weight = weightInKilos;
		if (this.weight == null || this.weight < 0.)
			throw new IllegalArgumentException("Invalid weight value " + this.weight);
	}
	
	public Double getWeightInKilos() {
		return weight;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof User) {
			User user = (User) obj;
			return this.name.equals(user.name) && Double.compare(this.weight, user.weight) == 0;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		int hashCode = 5;
		hashCode = hashCode * 31 + this.name.hashCode();
		hashCode = hashCode * 31 + this.weight.hashCode();
		return hashCode;
	}
}

package sic.nmsu.javafx.model;

import sic.nmsu.javafx.enumeration.Measure;

public class Ingredient {
	public final String name;
	public final double amount;
	public final Measure unit;
	
	public Ingredient() {
		this("", 0, Measure.NONE);
	}
	
	public Ingredient(String name) {
		this(name, 1, Measure.NONE);
	}
	
	public Ingredient(String name, double amount) {
		this(name, amount, Measure.NONE);
	}
	
	public Ingredient(String name, double amount, Measure unit) {
		assert name != null;
		assert amount > 0;
		assert unit != null;
		this.name = name;
		this.amount = amount;
		this.unit = unit;
	}
}

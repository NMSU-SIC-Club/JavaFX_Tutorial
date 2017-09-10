package sic.nmsu.javafx.tests.gui.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import sic.nmsu.javafx.enumeration.Measure;
import sic.nmsu.javafx.model.Ingredient;
import sic.nmsu.javafx.model.Recipe;

public class TestRecipes {
	
	private static List<Recipe> recipes = new ArrayList<>();
	
	static {
		Recipe recipe1 = new Recipe();
		recipe1.name = "Hot Tea";
		recipe1.ingredients.addAll(Arrays.asList(new Ingredient[] {
				new Ingredient("water", 1, Measure.CUP),
				new Ingredient("teabag", 1)
				}));
		recipe1.directions.addAll(Arrays.asList(new String[] {
				"Heat up water",
				"Add tea bag",
				"Steep for 5 min."
				}));
		recipes.add(recipe1);
		
		Recipe recipe2 = new Recipe();
		recipe2.name = "Sandwich";
		recipe2.ingredients.addAll(Arrays.asList(new Ingredient[] {
				new Ingredient("Mayonaise", 1, Measure.TABLESPOON),
				new Ingredient("Cheese Slice", 1),
				new Ingredient("Lunchmeat", 1)
				}));
		recipe2.directions.addAll(Arrays.asList(new String[] {
				"Heat up water",
				"Add tea bag",
				"Steep for 5 min."
				}));
		recipes.add(recipe2);
	}
	
	public static List<Recipe> get () {
		return recipes;
	}
}

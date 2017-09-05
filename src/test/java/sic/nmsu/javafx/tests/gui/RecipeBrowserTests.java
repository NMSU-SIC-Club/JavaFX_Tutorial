package sic.nmsu.javafx.tests.gui;

import org.junit.Test;
import org.mockito.Mock;
import org.springframework.context.annotation.Bean;

import javafx.scene.control.TextInputControl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.testfx.api.FxAssert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import sic.nmsu.javafx.enumeration.Measure;
import sic.nmsu.javafx.model.Ingredient;
import sic.nmsu.javafx.model.Recipe;
import sic.nmsu.javafx.service.RecipeLoaderService;

public class RecipeBrowserTests extends AppGuiTest {
	public static @Mock RecipeLoaderService recipeLoaderService;
	public static @Bean RecipeLoaderService recipeLoaderService () {
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
		
		ArrayList<Recipe> testRecipes = new ArrayList<>();
		testRecipes.add(recipe1);
		testRecipes.add(recipe2);
		
		when(recipeLoaderService.LoadRecipes()).thenReturn(testRecipes);
		return recipeLoaderService;
	}
	
	public @Test void shouldDisplayRecipeNames() {
		// when the app starts up, then the test recipe should be displayed
		assertNotNull(lookup("Hot Tea").query());
	}
	
	public @Test void shouldNarrowResults() {
		// given a search query
		TextInputControl searchInput = lookup("#searchInput").query();
		searchInput.setText("HOT");
		
		// when the search button is clicked
		clickOn("SEARCH");
		
		// then irrelevant recipes should be removed
		assertNotNull(lookup("Hot Tea").query());
		assertNull(lookup("Sandwich").query());
		
	}
}

package sic.nmsu.javafx.controller.recipe;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import sic.nmsu.javafx.controller.FXController;
import sic.nmsu.javafx.model.Cookbook;
import sic.nmsu.javafx.model.Recipe;
import sic.nmsu.javafx.service.RecipeLoaderService;
import sic.nmsu.javafx.service.StringDistanceService;

@Controller
public class RecipeBrowserController extends FXController {

	private @Autowired Cookbook cookbook;
	private @Autowired RecipeLoaderService recipeService;
	private @Autowired StringDistanceService stringDistanceService;

	private @FXML TextField searchInput;
	private @FXML ListView<Recipe> recipeList;

	private @FXML Label titleLabel;
	private @FXML Label descriptionLabel;
	private @FXML ListView<String> ingredientsList;
	private @FXML ListView<String> directionsList;

	@Override
	public void initialize() {
		cookbook.recipes.addAll(recipeService.LoadRecipes());
		recipeList.setItems(cookbook.recipes);
	}

	public @FXML void onSearch() {
		String query = searchInput.getText();
		if (query == null || query.length() == 0)
			recipeList.setItems(cookbook.recipes);

		ObservableList<Recipe> filtered = FXCollections.observableArrayList(cookbook.recipes.stream()
				.filter(r -> stringDistanceService.distance(query, r.name) <= 5).collect(Collectors.toList()));

		recipeList.setItems(filtered);
	}
}

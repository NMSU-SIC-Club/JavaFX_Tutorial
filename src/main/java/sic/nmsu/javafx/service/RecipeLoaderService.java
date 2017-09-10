package sic.nmsu.javafx.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.testfx.api.annotation.Unstable;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import sic.nmsu.javafx.model.Recipe;

/**
 * Simple Service for loading and saving Recipe Lists to individual files
 * 
 * @author Shane
 *
 */
@Unstable(reason = "untested")
@Service
public class RecipeLoaderService {
	private static Logger logger = LogManager.getLogger();

	private @Autowired SimpleNameService nameService;
	private Gson gson = new Gson();

	public List<Recipe> LoadRecipes() {
		Gson gson = new Gson();
		List<Recipe> recipes = new ArrayList<>();

		for (File file : getRecipesFolder().listFiles()) {
			if (!file.getName().endsWith(".json"))
				continue;

			try {
				JsonReader reader = new JsonReader(new FileReader(file));
				Recipe recipe = gson.fromJson(reader, Recipe.class);
				recipes.add(recipe);
			} catch (FileNotFoundException | JsonSyntaxException | JsonIOException e) {
				logger.error(e.getMessage());
				e.printStackTrace();
			}
		}
		return recipes;
	}

	public boolean SaveRecipe(Recipe recipe) {
		String filename = nameService.simplify(recipe.name);
		Path filepath = Paths.get(getRecipesFolder().getPath(), (filename + ".json"));
		try {
			JsonWriter writer = new JsonWriter(new FileWriter(filepath.toFile()));
			gson.toJson(recipe, Recipe.class, writer);
		} catch (JsonIOException | IOException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return false;
		}
		return true;
	}

	private File getRecipesFolder() {

		Path recipesDir = Paths.get(System.getProperty("user.home"), ".recipes");

		try {
			if (!Files.isDirectory(recipesDir))
				Files.createDirectory(recipesDir);
		} catch (IOException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return null;
		}

		return recipesDir.toFile();
	}
}

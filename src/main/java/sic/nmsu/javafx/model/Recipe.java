package sic.nmsu.javafx.model;

import java.util.ArrayList;
import java.util.List;

public class Recipe {
	public String name = new String();
	public String description = new String();
	public List<Ingredient> ingredients = new ArrayList<>();
	public List<String> directions = new ArrayList<>();
	
	public @Override String toString() {
		return name;
	}
}

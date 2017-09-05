package sic.nmsu.javafx.model;

import org.springframework.stereotype.Component;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

@Component
public class Cookbook {
	public final ObservableList<Recipe> recipes = FXCollections.observableArrayList();
}

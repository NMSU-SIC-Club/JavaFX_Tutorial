package sic.nmsu.javafx.controller;

import org.javatuples.Pair;
import org.springframework.stereotype.Controller;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sic.nmsu.javafx.controller.recipe.RecipeBrowserController;

@Controller
public class MainController extends FXController {

	private @FXML MenuBar mainMenu;
	private @FXML BorderPane borderPane;

	private Stage stage;

	public void setStage(Stage mainStage) {
		stage = mainStage;
	}

	@Override
	public void initialize() {
		Pair<Parent, RecipeBrowserController> result = FXController.get(RecipeBrowserController.class,
				"view/RecipeBrowserView.fxml");
		borderPane.setCenter(result.getValue0());
	}

	public @FXML void onClose() {
		stage.close();
	}
}

package sic.nmsu.javafx.controller;

import org.javatuples.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sic.nmsu.javafx.controller.dialog.FXDialogController;
import sic.nmsu.javafx.controller.recipe.RecipeBrowserController;

@Controller
public class MainController extends FXController {

	private @FXML MenuBar mainMenu;
	private @FXML BorderPane borderPane;

	@Qualifier("mainStage")
	private @Autowired Stage stage;

	public @Override void initialize() {
		Pair<Parent, RecipeBrowserController> result = FXController.get("view/RecipeBrowserView.fxml");
		borderPane.setCenter(result.getValue0());
	}

	/**
	 * Allows external closing of this view and thus the application
	 */
	public @FXML void onClose() {
		stage.close();
	}

	public @FXML void showAbout() {
		FXDialogController.show("view/dialog/AboutView.fxml");
	}
}

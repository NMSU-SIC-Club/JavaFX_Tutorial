package sic.nmsu.javafx;

import org.javatuples.Pair;

import javafx.application.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import sic.nmsu.javafx.bootstrap.Resolver;
import sic.nmsu.javafx.controller.FXController;
import sic.nmsu.javafx.controller.MainController;

public class App extends Application {

	private static Stage mainStage = null;
	
	public static void main(String[] args) {
		Resolver.init();
		launch(args);
	}
	
	public static Stage getMainStage() {
		return App.mainStage;
	}
	
	public static void setMainStage(Stage mainStage) {
		App.mainStage = mainStage;
	}

	@Override
	public void start(Stage mainStage) throws Exception {
		setMainStage(mainStage);
		Pair<Parent, MainController> result = FXController.get(MainController.class, "view/MainView.fxml",
				c -> c.setStage(mainStage));
		mainStage.setScene(new Scene(result.getValue0(), 400, 300));
		mainStage.setTitle("Recipe Book");
		mainStage.getIcons().add(new Image("images/recipe_icon.png"));
		mainStage.show();
	}

}

package sic.nmsu.javafx;

import org.javatuples.Pair;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javafx.application.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import sic.nmsu.javafx.bootstrap.Resolver;
import sic.nmsu.javafx.controller.FXController;
import sic.nmsu.javafx.controller.MainController;

@Configuration
@Import(Resolver.class)
public class App extends Application {

	private static Stage mainStage = null;
	private static HostServices hostServices = null;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Bean
	public static Stage mainStage() {
		return App.mainStage;
	}
	
	public static HostServices hostServices() {
		return App.hostServices;
	}

	@Override
	public void start(Stage mainStage) throws Exception {		
		App.mainStage = mainStage;
		App.hostServices = getHostServices();
		
		Resolver.init(App.class);
		Pair<Parent, MainController> result = FXController.get("view/MainView.fxml");
		mainStage.setScene(new Scene(result.getValue0()));
		mainStage.setTitle("Recipe Book");
		mainStage.getIcons().add(new Image("images/recipe_icon.png"));
		mainStage.show();
	}

}

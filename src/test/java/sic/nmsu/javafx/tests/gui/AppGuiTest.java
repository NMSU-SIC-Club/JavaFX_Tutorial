package sic.nmsu.javafx.tests.gui;

import org.javatuples.Pair;
import org.junit.Rule;
import org.junit.rules.DisableOnDebug;
import org.junit.rules.TestRule;
import org.junit.rules.Timeout;
import org.mockito.MockitoAnnotations;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.testfx.framework.junit.ApplicationTest;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sic.nmsu.javafx.App;
import sic.nmsu.javafx.bootstrap.Resolver;
import sic.nmsu.javafx.controller.FXController;
import sic.nmsu.javafx.controller.MainController;

@Configuration
@Import(Resolver.class)
public class AppGuiTest extends ApplicationTest {
	
	static {
	    if (Boolean.getBoolean("headless")) {
	        System.setProperty("testfx.robot", "glass");
	        System.setProperty("testfx.headless", "true");
	        System.setProperty("prism.order", "sw");
	        System.setProperty("prism.text", "t2k");
	    }
	}
	
	public @Rule TestRule globalTimeout = new DisableOnDebug(Timeout.seconds(10));
	
	public @Override void start(Stage stage) throws Exception {
		MockitoAnnotations.initMocks(this);
		Resolver.init(getClass());
		App.setMainStage(stage);
		Pair<Parent, MainController> result = FXController.get(MainController.class, "view/MainView.fxml",
				c -> c.setStage(stage));
		stage.setScene(new Scene(result.getValue0(), 600, 400));
		stage.show();
	}
}

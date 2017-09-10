package sic.nmsu.javafx.tests.gui;

import static org.mockito.Mockito.when;

import org.javatuples.Pair;
import org.junit.Rule;
import org.junit.rules.DisableOnDebug;
import org.junit.rules.TestRule;
import org.junit.rules.Timeout;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.testfx.framework.junit.ApplicationTest;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sic.nmsu.javafx.bootstrap.Resolver;
import sic.nmsu.javafx.controller.FXController;
import sic.nmsu.javafx.controller.MainController;
import sic.nmsu.javafx.service.DocumentService;
import sic.nmsu.javafx.service.RecipeLoaderService;
import sic.nmsu.javafx.tests.gui.util.TestRecipes;

/**
 * Base class for all full system GUI tests.
 * 
 * Although this class is decorated with the {@code @Configuration} annotation,
 * all subclasses still need the same annotation. Subclasses can then freely add
 * {@code @Bean} methods or {@code @Mock}/{@code @Spy} fields, which will all be
 * initialized with this class.
 * 
 * @author Shane
 *
 */
@Configuration
@Import(Resolver.class)
public class AppGuiTest extends ApplicationTest {

	/**
	 * If the headless property is set, then adjust these properties to run tests in
	 * headless mode
	 */
	static {
		if (Boolean.getBoolean("headless")) {
			System.setProperty("testfx.robot", "glass");
			System.setProperty("testfx.headless", "true");
			System.setProperty("prism.order", "sw");
			System.setProperty("prism.text", "t2k");
		}
	}

	/**
	 * Global timeout in case of long running tests (those which are self-blocking)
	 * 
	 * Can be overridden in subclass by declaring the same rule with a longer
	 * timeout. This rule is disabled during debugging
	 */
	public @Rule TestRule globalTimeout = new DisableOnDebug(Timeout.seconds(10));

	private static Stage mainStage;
	private static @Mock DocumentService documentService;
	private static @Mock RecipeLoaderService recipeLoaderService;

	public static @Bean Stage mainStage() {
		return AppGuiTest.mainStage;
	}

	public static @Bean RecipeLoaderService recipeLoaderService() {
		when(recipeLoaderService.LoadRecipes()).thenReturn(TestRecipes.get());
		return recipeLoaderService;
	}

	public static @Bean DocumentService documentService() {
		return AppGuiTest.documentService;
	}

	/**
	 * Setup the test on the provided stage, setting the Resolver to be configured
	 * with this class
	 */
	public @Override void start(Stage stage) throws Exception {
		AppGuiTest.mainStage = stage;
		MockitoAnnotations.initMocks(this);
		Resolver.init(getClass());
		Pair<Parent, MainController> result = FXController.get("view/MainView.fxml");
		stage.setScene(new Scene(result.getValue0()));
		stage.show();
	}
}

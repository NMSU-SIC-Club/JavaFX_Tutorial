package sic.nmsu.javafx.controller;

import java.io.IOException;
import java.util.function.Consumer;

import org.apache.logging.log4j.*;
import org.javatuples.Pair;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import sic.nmsu.javafx.App;
import sic.nmsu.javafx.bootstrap.Resolver;

/**
 * Base class for all controllers. Controllers must still be decorated with the
 * {@code @Controller} annotation to integrate properly with the system.
 * 
 * Instantiate the respective controllers using {@code get()} method. This will
 * trigger the following initialization steps:<br>
 * 1. The constructor is called<br>
 * 2. The fields/method injections are performed (via Spring Framework)<br>
 * 3. The {@code stateInit} consumer accepts the controller, if provided<br>
 * 4. The UI field injections are performed (via JavaFX)<br>
 * 5. The {@code initialize()} method is called<br>
 * 
 * @author Shane
 */
public abstract class FXController {
	private static final Logger logger = LogManager.getLogger();

	/**
	 * Initializes the controller view after FXML field injection
	 */
	public @FXML abstract void initialize();

	/**
	 * Create the view and controller associated with the provided FXML URL
	 * 
	 * @param url
	 *            the location of the FXML file, relative to the
	 *            {@code sic.nmsu.javafx} package
	 * @param stateInit
	 *            a consumer used to configure the controller before FXML injection
	 * @return the initialized view and controller
	 */
	public static <C extends FXController> Pair<Parent, C> get(String url, Consumer<C> stateInit) {
		FXMLLoader loader = new FXMLLoader(App.class.getResource(url));
		loader.setControllerFactory(ctrlClass -> {
			@SuppressWarnings("unchecked")
			C ctrl = (C) Resolver.resolve(ctrlClass);
			if (stateInit != null)
				stateInit.accept(ctrl);
			return ctrl;
		});

		try {
			loader.load();
		} catch (IOException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return null;
		}

		return new Pair<Parent, C>(loader.getRoot(), loader.getController());
	}

	/**
	 * Create the view and controller associated with the provided FXML URL
	 * 
	 * @param url
	 *            the location of the FXML file, relative to the
	 *            {@code sic.nmsu.javafx} package
	 * @return the initialized view and controller
	 */
	public static <C extends FXController> Pair<Parent, C> get(String url) {
		return get(url, null);
	}
}

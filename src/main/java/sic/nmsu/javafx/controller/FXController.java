package sic.nmsu.javafx.controller;

import java.io.IOException;
import java.util.function.Consumer;

import org.apache.logging.log4j.*;
import org.javatuples.Pair;
import org.springframework.stereotype.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import sic.nmsu.javafx.App;
import sic.nmsu.javafx.bootstrap.Resolver;

@Controller
public abstract class FXController {
	private static final Logger logger = LogManager.getLogger();
	public @FXML abstract void initialize();

	public static <C extends FXController> Pair<Parent, C> get(Class<C> ctrlType, String url, Consumer<C> stateInit) {
		FXMLLoader loader = new FXMLLoader(App.class.getResource(url));
		loader.setControllerFactory((c) -> {
			C ctrl = Resolver.resolve(ctrlType);
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

	public static <C extends FXController> Pair<Parent, C> get(Class<C> ctrlType, String url) {
		return get(ctrlType, url, null);
	}
}

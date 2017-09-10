package sic.nmsu.javafx.controller.dialog;

import java.util.function.Consumer;

import org.javatuples.Pair;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import sic.nmsu.javafx.controller.FXController;

/**
 * Base class for all controllers used for dialogs. Controllers must still be
 * decorated with the {@code @Controller} annotation to integrate properly with
 * the system.
 * 
 * Instantiate the respective controllers using {@code show()} method. This will
 * trigger the following initialization steps:<br>
 * 1. The constructor is called<br>
 * 2. The fields/method injections are performed (via Spring Framework)<br>
 * 3. The {@code stateInit} consumer accepts the controller, if provided<br>
 * 4. The UI field injections are performed (via JavaFX)<br>
 * 5. The {@code initialize()} method is called<br>
 * 
 * @author Shane
 *
 * @param <R>
 *            the return type of the dialog. May be {@code NullType}
 *            (javax.lang.model.type.NullType)
 */
public abstract class FXDialogController<R> extends FXController {
	/**
	 * The dialog managed by the controller
	 */
	Dialog<R> dialog;

	/**
	 * The result returned the the caller of the show method
	 */
	R result = null;

	/**
	 * The title used for the dialog window
	 */
	protected final StringProperty title = new SimpleStringProperty();

	/**
	 * Constructs and displays dialog based on the FXML at the specified URL
	 * 
	 * @param url
	 *            the location of the FXML file, relative to the
	 *            {@code sic.nmsu.javafx} package
	 * @param stateInit
	 *            a consumer used to configure the controller before FXML injection
	 * @return
	 */
	public static <R, C extends FXDialogController<R>> R show(String url, Consumer<C> stateInit) {
		final Pair<Parent, C> result = FXController.get(url, stateInit);
		final Parent view = result.getValue0();
		final C ctrl = result.getValue1();

		final Dialog<R> dialog = new Dialog<>();
		dialog.titleProperty().bind(ctrl.title);

		final DialogPane dialogPane = dialog.getDialogPane();
		dialogPane.setContent(view);
		dialogPane.getButtonTypes().add(ButtonType.CLOSE);

		final Stage window = (Stage) dialogPane.getScene().getWindow();
		window.getIcons().add(new Image("images/recipe_icon.png"));

		final Node closeButton = dialogPane.lookupButton(ButtonType.CLOSE);
		closeButton.managedProperty().bind(closeButton.visibleProperty());
		closeButton.setVisible(false);

		ctrl.dialog = dialog;
		ctrl.dialog.showAndWait();

		return ctrl.result;
	}

	/**
	 * Constructs and displays dialog based on the FXML at the specified URL
	 * 
	 * @param url
	 *            the location of the FXML file, relative to the
	 *            {@code sic.nmsu.javafx} package
	 * @return
	 */
	public static <R, C extends FXDialogController<R>> R show(String url) {
		return show(url, null);
	}

	/**
	 * Closes the dialog and provides null to the caller of {@code show()}
	 * 
	 * This method is decorated to be referenced in FXML
	 */
	public @FXML void close() {
		closeWith(null);
	}

	/**
	 * Close the dialog and provides the result to the caller of {@code show()}
	 * 
	 * @param result the result of the dialog
	 */
	public synchronized void closeWith(R result) {
		this.result = result;
		if (dialog != null)
			dialog.close();
	}
}

package sic.nmsu.javafx.controller;

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
import javafx.stage.Stage;
import sic.nmsu.javafx.App;

public abstract class FXDialogController<R> extends FXController {
	Dialog<R> dialog;
	R result = null;
	protected final StringProperty title = new SimpleStringProperty();

	public static <R, C extends FXDialogController<R>> R show(Class<C> ctrlType, String url, Consumer<C> stateInit,
			Stage owner) {
		final Pair<Parent, C> result = FXController.get(ctrlType, url, stateInit);
		final Parent view = result.getValue0();
		final C ctrl = result.getValue1();

		final Dialog<R> dialog = new Dialog<>();
		dialog.titleProperty().bind(ctrl.title);
		
		final DialogPane dialogPane = dialog.getDialogPane();
		dialogPane.setContent(view);
		dialogPane.getButtonTypes().add(ButtonType.CLOSE);

		final Node closeButton = dialogPane.lookupButton(ButtonType.CLOSE);
		closeButton.managedProperty().bind(closeButton.visibleProperty());
		closeButton.setVisible(false);
		
		ctrl.dialog = dialog;
		ctrl.dialog.showAndWait();

		return ctrl.result;
	}

	public static <R, C extends FXDialogController<R>> R show(Class<C> ctrlType, String url) {
		return show(ctrlType, url, null, App.getMainStage());
	}

	public static <R, C extends FXDialogController<R>> R show(Class<C> ctrlType, String url, Consumer<C> stateInit) {
		return show(ctrlType, url, stateInit, App.getMainStage());
	}

	public static <R, C extends FXDialogController<R>> R show(Class<C> ctrlType, String url, Stage owner) {
		return show(ctrlType, url, null, owner);
	}

	public @FXML void close() {
		closeWith(null);
	}

	public synchronized void closeWith(R result) {
		this.result = result;
		if (dialog != null)
			dialog.close();
	}
}

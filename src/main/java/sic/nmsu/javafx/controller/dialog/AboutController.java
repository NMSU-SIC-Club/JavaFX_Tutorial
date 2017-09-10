package sic.nmsu.javafx.controller.dialog;

import javax.lang.model.type.NullType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javafx.fxml.FXML;
import sic.nmsu.javafx.service.DocumentService;

@Controller
public class AboutController extends FXDialogController<NullType> {
	@Autowired
	private DocumentService documentService;

	@Override
	public void initialize() {
		title.set("About");
	}

	public @FXML void openGitHub() {
		documentService.showDocument("https://github.com/NMSU-SIC-Club");
	}
}

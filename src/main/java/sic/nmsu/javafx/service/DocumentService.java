package sic.nmsu.javafx.service;

import org.springframework.stereotype.Service;

import javafx.application.HostServices;
import sic.nmsu.javafx.App;

/**
 * Simple service to allow access to host services.
 * 
 * This class was created because HostServices is cannot be sub-classed, and
 * thus is not eligible for mocking (in general). Wrapping HostServices in a
 * separate service allows the DocumentService to be stubbed out for testing.
 * 
 * @author Shane
 *
 */
@Service
public class DocumentService {
	private HostServices hostServices;

	public DocumentService() {
		hostServices = App.hostServices();
	}

	public void showDocument(String uri) {
		hostServices.showDocument(uri);
	}
}

package sic.nmsu.javafx.tests.gui;

import org.junit.After;
import org.junit.Test;
import org.springframework.context.annotation.Configuration;

import sic.nmsu.javafx.bootstrap.Resolver;
import sic.nmsu.javafx.controller.dialog.AboutController;
import sic.nmsu.javafx.service.DocumentService;

import static org.junit.Assert.*;
import static org.junit.Assume.*;
import static org.mockito.Mockito.*;


@Configuration
public class AboutTests extends AppGuiTest {
	public @Test void shouldOpenAbout() {
		// given the help menu is open
		clickOn("Help");
		
		// when the about page is clicked
		clickOn("About");
		
		// then the about page should be displayed
		assertNotNull(lookup("#githubLink").query());
	}
	
	public @Test void shouldOpenGitHub() {
		// given the about page is open
		clickOn("Help").clickOn("About");
		assumeNotNull(lookup("#githubLink").query());
		
		// when the hyperlink is clicked
		clickOn("#githubLink");
		
		// then the host services should open the github link
		DocumentService mockService = Resolver.resolve(DocumentService.class);
		verify(mockService).showDocument("https://github.com/NMSU-SIC-Club");
	}
	
	public @After void cleanUp() {
		interact(Resolver.resolve(AboutController.class)::close);
	}
}

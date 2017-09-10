package sic.nmsu.javafx.tests.gui;

import org.junit.Test;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javafx.scene.control.TextInputControl;
import sic.nmsu.javafx.bootstrap.Resolver;
import sic.nmsu.javafx.service.StringDistanceService;
import sic.nmsu.javafx.tests.gui.util.SpyPostProcessor;

import static org.junit.Assert.*;
import static org.junit.Assume.*;
import static org.mockito.Mockito.*;

@Configuration
public class RecipeBrowserTests extends AppGuiTest {
	
	public @Bean SpyPostProcessor spyPostProcessor() {
		return new SpyPostProcessor(StringDistanceService.class);
	}

	public @Test void shouldDisplayRecipeNames() {
		// when the app starts up, then the test recipe should be displayed
		assertNotNull(lookup("Hot Tea").query());
	}

	public @Test void shouldNarrowResults() {
		// given a search query
		((TextInputControl)lookup("#searchInput").query()).setText("HOT");

		// when the search button is clicked
		clickOn("SEARCH");

		// then irrelevant recipes should be removed
		assertNotNull(lookup("Hot Tea").query());
		assertNull(lookup("Sandwich").query());
		
		// ... and the string distance should be calculated twice to do this
		StringDistanceService spiedService = Resolver.resolve(StringDistanceService.class);
		verify(spiedService, times(2)).distance(any(), any());
	}
	
	public @Test void shouldCallStringDistanceService() {
		StringDistanceService spiedService = Resolver.resolve(StringDistanceService.class);
		TextInputControl searchInput = lookup("#searchInput").query();

		// given two recipes, one hidden after a search
		searchInput.setText("HOT");
		clickOn("SEARCH");
		
		assumeNotNull(lookup("Hot Tea").query());
		assumeTrue(lookup("Sandwich").query() == null);

		// when a new search is performed
		searchInput.setText("wich");
		clickOn("SEARCH");
		
		// then the StringDistanceService should be called once for each entry, present or not
		verify(spiedService, times(2)).distance(matches("wich"), any());
	}
}

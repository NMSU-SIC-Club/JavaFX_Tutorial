package sic.nmsu.javafx.tests.unit;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContextManager;

import sic.nmsu.javafx.service.SimpleNameService;

@RunWith(Parameterized.class)
@ContextConfiguration(classes = { SimpleNameService.class })
public class SimpleNameServiceTests {

	/**
	 * Constructor Parameters for instantiating tests
	 * @return
	 */
	public @Parameters static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] { { "noChange", "noChange" }, { "with space", "with_space" },
				{ "with-hypen", "with_hypen" }, { " $ymb@1$ .", "__ymb_1___" } });
	}

	/**
	 * Initializes Autowired fields
	 * @throws Exception
	 */
	public @Before void setUp() throws Exception {
		new TestContextManager(getClass()).prepareTestInstance(this);
	}

	public @Autowired SimpleNameService nameService;

	private String input;
	private String expected;

	public SimpleNameServiceTests(String input, String expected) {
		this.input = input;
		this.expected = expected;
	}

	@Test
	public void testSimplifyName() {
		String actual = nameService.simplify(input);
		assertEquals(expected, actual);
	}

}

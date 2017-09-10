package sic.nmsu.javafx.bootstrap;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "sic.nmsu.javafx.controller", "sic.nmsu.javafx.model", "sic.nmsu.javafx.service" })
public class Resolver {
	private static AnnotationConfigApplicationContext ctx;

	/**
	 * Initializes the Spring context for future calls using this class
	 */
	public static void init() {
		init(Resolver.class);
	}

	/**
	 * Initializes the Spring context for future calls
	 * 
	 * @param config
	 *            a {@code @Configuration} decorated class
	 */
	public static void init(Class<?> config) {
		ctx = new AnnotationConfigApplicationContext(config);
	}

	/**
	 * Initializes and returns an instance of the specified type
	 * 
	 * Initialization time is subject to the scope of the type
	 * 
	 * @param type
	 *            the type of the desired object
	 * @param args
	 *            constructor arguments (optional)
	 * @return a object of the specified type
	 */
	public static <T> T resolve(Class<T> type, Object... args) {
		return ctx.getBean(type, args);
	}

	/**
	 * Initializes and returns an instance of the object by name
	 * 
	 * Initialization time is subject to the scope of the type
	 * 
	 * @param name
	 *            the name of the desired object
	 * @param args
	 *            constructor arguments (optional)
	 * @return a object of the specified type
	 */
	public static Object resolve(String name, Object... args) {
		return ctx.getBean(name, args);
	}
}

package sic.nmsu.javafx.bootstrap;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "sic.nmsu.javafx.controller", "sic.nmsu.javafx.model", "sic.nmsu.javafx.service" })
public class Resolver {
	private static AnnotationConfigApplicationContext ctx;

	public static void init() {
		init(Resolver.class);
	}

	public static void init(Class<?> config) {
		ctx = new AnnotationConfigApplicationContext(config);
	}

	public static <T> T resolve(Class<T> type, Object... args) {
		return ctx.getBean(type, args);
	}

	public static Object resolve(String name, Object... args) {
		return ctx.getBean(name, args);
	}

	public static void addPostProcessor(BeanFactoryPostProcessor postProcessor) {
		ctx.addBeanFactoryPostProcessor(postProcessor);
	}
}

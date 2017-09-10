package sic.nmsu.javafx.tests.gui.util;

import java.util.ArrayList;
import java.util.List;

import org.mockito.Mockito;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * Specifies which Spring Beans will be wrapped with Mockito Spies
 * 
 * @author Shane
 *
 */
public class SpyPostProcessor implements BeanPostProcessor {
	private final List<Class<?>> spyTargets = new ArrayList<>();

	public SpyPostProcessor(Class<?>... targets) {
		for (Class<?> target : targets)
			spyTargets.add(target);
	}

	public @Override Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

	public @Override Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if (spyTargets.contains(bean.getClass()))
			return Mockito.spy(bean);

		return bean;
	}

}

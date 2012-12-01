package com.st.nicobot.context;

import java.util.Set;

import org.picocontainer.ComponentAdapter;
import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.MutablePicoContainer;
import org.picocontainer.PicoContainer;
import org.picocontainer.behaviors.Caching;
import org.picocontainer.gems.monitors.Slf4jComponentMonitor;
import org.picocontainer.injectors.AnnotatedFieldInjection;
import org.picocontainer.lifecycle.ReflectionLifecycleStrategy;
import org.picocontainer.monitors.NullComponentMonitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.st.nicobot.context.annotations.Component;
import com.st.nicobot.utils.ClassLoader;

/**
 * @author Julien
 *
 */
public class ApplicationContext {

	private static Logger logger = LoggerFactory.getLogger(ApplicationContext.class);
	
	private MutablePicoContainer container;
	
	@SuppressWarnings("serial")
	public void init() {
		container = new DefaultPicoContainer(
				new Caching().wrap(new AnnotatedFieldInjection()), 
				new ReflectionLifecycleStrategy(new NullComponentMonitor()) {
					@Override
					public boolean isLazy(ComponentAdapter<?> adapter) {
						return true;
					}
				}, 
				(PicoContainer)null, 
				new Slf4jComponentMonitor()
		);
		
		loadComponents(ClassLoader.getAllInstantiableClasses());
		loadComponents(ClassLoader.getClassAnnotedWith(Component.class));

		container.start();
		
		publishApplicationContext();
	}
	
	private void loadComponents(Set<Class<?>> classes) {
		for(Class<?> c : classes) {
			logger.debug("Adding component {}", c.getSimpleName());
			container.addComponent(c);
		}
	}
	
	public MutablePicoContainer getPicoContainer() {
		return container;
	}
	
	private void publishApplicationContext() {
		Set<Class<? extends ApplicationContextAware>> impls = ClassLoader.getSubTypesOf(ApplicationContextAware.class);
		
		for(Class<? extends ApplicationContextAware> c : impls) {
			ApplicationContextAware impl = container.getComponent(c);
			impl.setApplicationContext(this);
		}
	}
}

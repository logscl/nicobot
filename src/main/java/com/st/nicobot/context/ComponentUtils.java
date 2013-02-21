package com.st.nicobot.context;

import java.util.Set;

import org.picocontainer.MutablePicoContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author Julien
 *
 */
public abstract class ComponentUtils {

	private static Logger logger = LoggerFactory.getLogger(ComponentUtils.class);
	
	/**
	 * Ajoute chaque classe de {@code classes} dans le container.
	 * @param container
	 * @param classes
	 */
	public static void loadComponents(MutablePicoContainer container, Set<Class<?>> classes) {
		for(Class<?> c : classes) {
			logger.debug("Adding component {}", c.getSimpleName());
			container.addComponent(c);
		}
	}
	
	/**
	 * Invoque la methode {@link ApplicationContextAware#setApplicationContext(MutablePicoContainer)} pour chaque 
	 * classe implémentant l'interface {@link ApplicationContextAware}.
	 * 
	 * @param container
	 * 		Le container à injecter
	 */
	public static void publishApplicationContext(MutablePicoContainer container) {
		Set<Class<? extends ApplicationContextAware>> impls = ClassLoader.getSubTypesOf(ApplicationContextAware.class);
		
		for(Class<? extends ApplicationContextAware> c : impls) {
			ApplicationContextAware impl = container.getComponent(c);
			impl.setApplicationContext(container);
		}
	}
}

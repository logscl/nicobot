package com.st.nicobot.context;

import java.lang.annotation.Annotation;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Permet de charger toutes les classes d'un certain type
 * de tout le projet.
 * 
 * @author Logan
 *
 */
public class ClassLoader {

	private static Logger logger = LoggerFactory.getLogger(ClassLoader.class);
	
	private static String DEFAULT_PACKAGE = "com.st.nicobot";
	
	private static Reflections reflex;
	
	static {
		reflex = new Reflections(
			new ConfigurationBuilder()
				.filterInputsBy(
						new FilterBuilder().include(FilterBuilder.prefix(DEFAULT_PACKAGE))
				)
				.setUrls(ClasspathHelper.forPackage(DEFAULT_PACKAGE))
				.setScanners(
						new SubTypesScanner(), 
						new TypeAnnotationsScanner()
				)
			);
	}
	
	private ClassLoader() {}
	
	public static Set<Class<?>> getAllInstantiableClasses() {
		Reflections r = new Reflections(DEFAULT_PACKAGE, new SubTypesScanner(false));
		Set<Class<?>> classes = r.getSubTypesOf(Object.class);
		
		Set<Class<?>> instantiableClasses = new HashSet<Class<?>>();
		
		for(Class<?> c : classes) {
			if ( !Modifier.isAbstract(c.getModifiers()) ) {
				instantiableClasses.add(c);
			}
		}
		
		return instantiableClasses;
	}
	
	/**
	 * Retourne la liste de toutes les classes ayant un sous type spécifique
	 * (extends ou implements)
	 * @param type
	 * 		Le type à chercher
	 * @return
	 * 		La liste des classes trouvées
	 */
	public static <T> Set<Class< ? extends T>> getSubTypesOf(Class<T> type) {
		return reflex.getSubTypesOf(type);
	}
	
	/**
	 * Retourne une liste d'instances de classes du projet.
	 * Les classes trouvées sont juste instanciées et placées dans une liste. 
	 * Elles ne sont pas liées.
	 * @param type
	 * 		Le type de classe à instancier
	 * @return
	 * 		La liste des instances
	 */
	public static <T> List<T> getInstancesOfClass(Class<T> type) {
		Set<Class<? extends T>> classes = getSubTypesOf(type);
		List<T> instances = new ArrayList<T>();
		
		for(Class<? extends T> clazz : classes) {
			try {
				if(!Modifier.isAbstract(clazz.getModifiers())) {
					T instance = (T)clazz.newInstance();
					instances.add(instance);
					
				}
			} catch (Exception e) {
				logger.error("Impossible d'instancier la classe {}, exception : {}", clazz, e.getMessage());
			}
		}
		
		return instances;
	}
	
	public static Set<Class<?>> getClassAnnotedWith(Class<? extends Annotation> annot){
		return reflex.getTypesAnnotatedWith(annot);
	}
	
}

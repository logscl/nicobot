package com.st.nicobot.utils;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

/**
 * Permet de charger toutes les classes d'un certain type
 * de tout le projet.
 * 
 * @author Logan
 *
 */
public class ClassLoader {

	private static String DEFAULT_PACKAGE = "com.st.nicobot";
	
	private static ClassLoader instance;
	
	private static Reflections reflex;
	
	private ClassLoader() {}
	
	public static ClassLoader getInstance() {
		if(instance == null) {
			reflex = new Reflections(new ConfigurationBuilder()
		        .filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix(DEFAULT_PACKAGE)))
		        .setUrls(ClasspathHelper.forPackage(DEFAULT_PACKAGE))
		        .setScanners(new SubTypesScanner(), new ResourcesScanner()));
			instance = new ClassLoader();
		}
		return instance;
	}
	
	/**
	 * Retourne la liste de toutes les classes ayant un sous type spécifique
	 * (extends ou implements)
	 * @param type
	 * 		Le type à chercher
	 * @return
	 * 		La liste des classes trouvées
	 */
	public <T> Set<Class< ? extends T>> getSubTypesOf(Class<T> type) {
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
	public <T> List<T> getInstancesOfClass(Class<T> type) {
		Set<Class<? extends T>> classes = getSubTypesOf(type);
		List<T> instances = new ArrayList<T>();
		
		for(Class<? extends T> clazz : classes) {
			try {
				if(!Modifier.isAbstract(clazz.getModifiers())) {
					T instance = (T)clazz.newInstance();
					instances.add(instance);
				}
			} catch (Exception e) {
				System.out.println("Impossibler d'instancier la classe "+clazz+", exception : "+e.getMessage());
			}
		}
		
		return instances;
	}
}

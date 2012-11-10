/**
 * 
 */
package com.st.nicobot.internal.services;

import java.util.Set;

import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import com.st.nicobot.cmd.NiCommand;
import com.st.nicobot.services.Commands;

/**
 * @author Julien
 *
 */
public class CommandsImpl implements Commands {

	private NiCommand firstLink;

	private static CommandsImpl instance;
	
	private static String commandsPackage = "com.st.nicobot.cmd";
	
	private CommandsImpl() {	}
	
	public static CommandsImpl getInstance() {
		if (instance == null) {
			instance = new CommandsImpl();
			instance.init();
		}
		
		return instance;
	}
	
	public void init() {
		NiCommand previous = null;
		
		Reflections reflex = new Reflections(new ConfigurationBuilder()
	        .filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix(commandsPackage)))
	        .setUrls(ClasspathHelper.forPackage(commandsPackage))
	        .setScanners(new SubTypesScanner(), new ResourcesScanner()));
		
		Set<Class<? extends NiCommand>> classes = reflex.getSubTypesOf(NiCommand.class);
		
		for(Class<? extends NiCommand> clazz : classes) {
			try {
				NiCommand c = (NiCommand)clazz.newInstance();
				if(firstLink == null) {
					firstLink = c;
				}
				if(previous == null) {
					previous = c;
				} else {
					previous.setNext(c);
					previous = c;
				}
			} catch (Exception e) {
				System.out.println("Impossibler d'instancier la classe "+clazz+", exception : "+e.getMessage());
			}
		}
	}
	
	@Override
	public NiCommand getFirstLink() {
		return firstLink;
	}
	
}

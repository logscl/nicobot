/**
 * 
 */
package com.st.nicobot.internal.services;

import com.st.nicobot.cmd.NiCommand;
import com.st.nicobot.services.Commands;

/**
 * @author Julien
 *
 */
public class CommandsImpl implements Commands {

	private NiCommand firstLink;

	private static CommandsImpl instance;
	
	private static String commandsPackage = "com.st.nicobot.cmd.";
	
	/**
	 * v1 : pas d'auto-discovery des classes (sans Spring, c'est chiant).
	 * TODO v2 : prendre toutes les classes du package dans commandsPackage
	 * et instancier celles qui extends NiCommand
	 */
	private static String[] commandsClasses = {"Help", "ReloadMessage", "StopNicoBot", "Say"};
	
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
		for(String clazz : commandsClasses) {
			try {
				NiCommand c = (NiCommand)Class.forName(commandsPackage+clazz).newInstance();
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

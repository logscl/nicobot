/**
 * 
 */
package com.st.nicobot.internal.services;

import java.util.Set;

import com.st.nicobot.cmd.NiCommand;
import com.st.nicobot.services.Commands;
import com.st.nicobot.utils.ClassLoader;

/**
 * @author Julien
 *
 */
public class CommandsImpl implements Commands {

	private NiCommand firstLink;

	private static CommandsImpl instance;
	
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
		
		Set<Class<? extends NiCommand>> classes = ClassLoader.getInstance().getSubTypesOf(NiCommand.class);
		
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

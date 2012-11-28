/**
 * 
 */
package com.st.nicobot.internal.services;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.st.nicobot.cmd.NiCommand;
import com.st.nicobot.services.Commands;
import com.st.nicobot.utils.ClassLoader;

/**
 * @author Julien
 *
 */
public class CommandsImpl implements Commands {

	private static Logger logger = LoggerFactory.getLogger(CommandsImpl.class);
	
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
				logger.error("Impossibler d'instancier la classe {}, exception : {}", clazz, e.getMessage());
			}
		}
	}
	
	@Override
	public NiCommand getFirstLink() {
		return firstLink;
	}
	
}

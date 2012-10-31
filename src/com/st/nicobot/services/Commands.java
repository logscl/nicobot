/**
 * 
 */
package com.st.nicobot.services;

import java.util.Set;

import com.st.nicobot.cmd.NiCommand;

/**
 * @author Julien
 *
 */
public interface Commands {

	public Set<String> getCommands();
	public NiCommand getCommand(String commandName);
	
}

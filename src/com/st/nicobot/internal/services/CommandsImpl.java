/**
 * 
 */
package com.st.nicobot.internal.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.st.nicobot.cmd.NiCommand;
import com.st.nicobot.cmd.ReloadMessage;
import com.st.nicobot.services.Commands;

/**
 * @author Julien
 *
 */
public class CommandsImpl implements Commands {

	private Map<String, NiCommand> niCommands;

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
		niCommands = new HashMap<String, NiCommand>();
		
		NiCommand reloadCmd = new ReloadMessage();
		
		niCommands.put(reloadCmd.getCommandName(), reloadCmd);
	}
	
	@Override
	public Set<String> getCommands() {
		return niCommands.keySet();
	}
	
	@Override
	public NiCommand getCommand(String commandName) {
		return niCommands.get(commandName);
	}
}

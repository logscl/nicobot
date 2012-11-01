/**
 * 
 */
package com.st.nicobot.internal.services;

import com.st.nicobot.cmd.Help;
import com.st.nicobot.cmd.NiCommand;
import com.st.nicobot.cmd.ReloadMessage;
import com.st.nicobot.cmd.StopNicoBot;
import com.st.nicobot.services.Commands;

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
		NiCommand cmd1, cmd2, cmd3;
		cmd1 = new ReloadMessage();
		cmd2 = new StopNicoBot();
		cmd3 = new Help();
		
		cmd1.setNext(cmd2);
		cmd2.setNext(cmd3);
		
		firstLink = cmd1;
	}
	
	@Override
	public NiCommand getFirstLink() {
		return firstLink;
	}
	
}

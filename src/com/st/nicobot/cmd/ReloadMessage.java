/**
 * 
 */
package com.st.nicobot.cmd;

import com.st.nicobot.NicoBot;
import com.st.nicobot.internal.services.MessagesImpl;

/**
 * @author Julien
 *
 */
public class ReloadMessage implements NiCommand {

	private static final String COMMAND = "!nico_refresh";
	private static final String DESC = "Redémarre les différents services de nicobot.";
	
	
	public ReloadMessage() {	}
	
	@Override
	public String getCommandName() {
		return COMMAND;
	}
	
	@Override
	public String getDescription() {
		return DESC;
	}
	
	@Override
	public void handle(NicoBot nicobot) {
		System.out.println("Start reloading messages");
		MessagesImpl.getInstance().init();
	}
	
}

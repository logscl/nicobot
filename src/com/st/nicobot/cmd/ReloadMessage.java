/**
 * 
 */
package com.st.nicobot.cmd;

import com.st.nicobot.NicoBot;
import com.st.nicobot.internal.services.MessagesImpl;
import com.st.nicobot.utils.Option;

/**
 * @author Julien
 *
 */
public class ReloadMessage extends NiCommand {

	private static final String COMMAND = "refresh";
	private static final String DESC = "Redémarre les différents messages de nicobot.";
	
	
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
	public void doCommand(NicoBot nicobot, String message, String[] args, Option opts) {
		System.out.println("Start reloading messages");
		MessagesImpl.getInstance().init();
	}
	
}

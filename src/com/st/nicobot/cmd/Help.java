/**
 * 
 */
package com.st.nicobot.cmd;

import com.st.nicobot.NicoBot;
import com.st.nicobot.internal.services.CommandsImpl;
import com.st.nicobot.internal.services.MessagesImpl;
import com.st.nicobot.services.Commands;
import com.st.nicobot.utils.Option;

/**
 * @author Julien
 *
 */
public class Help extends NiCommand {

	private static final String CMD = "help";
	private static final String DESC = "Retourne la liste des commandes disponibles.";
			
	@Override
	public String getCommandName() {
		return CMD;
	}

	@Override
	public String getDescription() {
		return DESC;
	}

	@Override
	protected void doCommand(NicoBot nicobot, String command, String[] args, Option opts) {
		Commands commandsChain = CommandsImpl.getInstance();
		NiCommand cmd = commandsChain.getFirstLink();
		
		nicobot.sendMessage(opts.sender, MessagesImpl.getInstance().getOtherMessage("helpHeader"));
		
		while (cmd != null) {
			nicobot.sendMessage(opts.sender, "    - " + cmd.getCommandName() + " : " + cmd.getDescription());
			cmd = cmd.nextCommand;
		}
	}		

}

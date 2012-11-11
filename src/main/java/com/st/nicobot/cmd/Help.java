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

	private static final String COMMAND = "help";
	private static final String FORMAT = "help [commandName]";
	private static final String DESC = "Retourne la liste des commandes disponibles OU " +
			"une aide detaillé pour la commande passsée en paramètre.";
			
	@Override
	public String getCommandName() {
		return COMMAND;
	}

	@Override
	public String getDescription() {
		return DESC;
	}
	
	@Override
	public String getFormat() {
		return FORMAT;
	}

	@Override
	protected void doCommand(NicoBot nicobot, String command, String[] args, Option opts) {
		HelpArguments arguments = new HelpArguments(args);
		
		if (arguments.commandName == null) {
			sendCommandList(nicobot, opts);
		}
		else {
			sendCommandHelp(nicobot, opts, arguments.commandName);
		}
		
	}	
	
	/**
	 * Envoie la liste de toutes les commandes + description 
	 * @param nicobot
	 * @param opts
	 */
	private void sendCommandList(NicoBot nicobot, Option opts) {
		Commands commandsChain = CommandsImpl.getInstance();
		NiCommand cmd = commandsChain.getFirstLink();
		
		nicobot.sendNotice(opts.sender, MessagesImpl.getInstance().getOtherMessage("helpHeader"));
		
		while (cmd != null) {
			nicobot.sendNotice(opts.sender, "    - " + cmd.getCommandName() + " : " + cmd.getDescription());
			cmd = cmd.nextCommand;
		}
	}
	
	/**
	 * Envoie la description de la commande + format
	 * @param nicobot
	 * @param opts
	 * @param commandName
	 */
	private void sendCommandHelp(NicoBot nicobot, Option opts, String commandName) {
		Commands commandsChain = CommandsImpl.getInstance();
		NiCommand cmd = commandsChain.getFirstLink();
		
		while(cmd != null && !cmd.getCommandName().equals(commandName)) {
			cmd = cmd.nextCommand;
		}
		
		if (cmd != null) {
			nicobot.sendNotice(opts.sender, cmd.getDescription());
			nicobot.sendNotice(opts.sender, cmd.getFormat());
		}
	}
	
	private class HelpArguments {
		private final String commandName;
		
		public HelpArguments(String[] args) {
			
			if (args != null && args.length != 0) {
				commandName = args[0];
			}
			else {
				commandName = null;
			}
		}
	}

}

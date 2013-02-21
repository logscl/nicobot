package com.st.nicobot.bot.cmd;

import org.picocontainer.annotations.Inject;

import com.st.nicobot.bot.NicoBot;
import com.st.nicobot.bot.utils.Option;
import com.st.nicobot.services.Commands;
import com.st.nicobot.services.Messages;

/**
 * @author Julien
 *
 */
public class Help extends NiCommand {

	private static final String COMMAND = "help";
	private static final String FORMAT = "help [commandName]";
	private static final String DESC = "Retourne la liste des commandes disponibles OU " +
			"une aide detaillée pour la commande passée en paramètre.";

	@Inject
	private Messages messages;
	
	@Inject
	private Commands commandsChain;
	
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
	public boolean isAdminRequired() {
		return false;
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
		NiCommand cmd = commandsChain.getFirstLink();
		
		nicobot.sendNotice(opts.sender, messages.getOtherMessage("helpHeader"));
		
		while (cmd != null) {
			if(!cmd.isAdminRequired() || (cmd.isAdminRequired() && opts.senderIsAdmin)) {
				nicobot.sendNotice(opts.sender, "    - " + cmd.getCommandName() + " : " + cmd.getDescription());
			}
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
		NiCommand cmd = commandsChain.getFirstLink();
		
		while(cmd != null && !cmd.getCommandName().equals(commandName)) {
			cmd = cmd.nextCommand;
		}
		
		if (cmd != null) {
			if(!cmd.isAdminRequired() || (cmd.isAdminRequired() && opts.senderIsAdmin)) {
				nicobot.sendNotice(opts.sender, cmd.getDescription());
				nicobot.sendNotice(opts.sender, cmd.getFormat());
			}
		} else {
			nicobot.sendNotice(opts.sender, messages.getOtherMessage("helpNotFound"));
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

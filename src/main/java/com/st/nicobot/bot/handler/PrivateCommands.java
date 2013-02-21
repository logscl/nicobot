package com.st.nicobot.bot.handler;

import org.jibble.pircbot.Colors;
import org.picocontainer.annotations.Inject;

import com.st.nicobot.bot.NicoBot;
import com.st.nicobot.bot.cmd.NiCommand;
import com.st.nicobot.bot.event.PrivateMessageEvent;
import com.st.nicobot.bot.utils.Option;
import com.st.nicobot.services.Commands;
import com.st.nicobot.services.UsersService;

public class PrivateCommands implements PrivateMessageEvent {

	@Inject
	private Commands commands;
	
	@Inject
	private UsersService users;
	
	@Override
	public void onPrivateMessage(String sender, String login, String hostname, String message, NicoBot nicobot) {
		//on extrait <cmd> <reste>
		String[] arguments = message.split(" ");		
		
		if(arguments.length >= 1) {
			String[] commandArgs = null;
			
			if(arguments.length > 1) {
				// on extrait de la chaine uniquement la partie contenant les arguments
				String commandsString = message.substring(message.indexOf(arguments[1]));	
				commandArgs = NiCommand.getArgs(commandsString);
			}
			
			commands.getFirstLink().handle(
					nicobot, Colors.removeFormattingAndColors(arguments[0]), 
					commandArgs, new Option(null, sender, message, users.isAdmin(sender)));
		} else {
			nicobot.sendNotice(sender, "T'es con ou quoi ? Une commande, c'est \"<commande> [params]\"");
		}

	}

}

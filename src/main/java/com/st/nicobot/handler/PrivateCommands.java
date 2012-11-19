package com.st.nicobot.handler;

import org.jibble.pircbot.Colors;

import com.st.nicobot.NicoBot;
import com.st.nicobot.cmd.NiCommand;
import com.st.nicobot.event.PrivateMessageEvent;
import com.st.nicobot.internal.services.CommandsImpl;
import com.st.nicobot.services.Commands;
import com.st.nicobot.utils.Option;

public class PrivateCommands implements PrivateMessageEvent {

	private Commands commands = CommandsImpl.getInstance();
	
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
					commandArgs, new Option(null, sender, message));
		} else {
			nicobot.sendNotice(sender, "T'es con ou quoi ? Une commande, c'est \"<commande> [params]\"");
		}

	}

}

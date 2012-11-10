package com.st.nicobot;

import org.jibble.pircbot.Colors;

import com.st.nicobot.bot.AbstractPircBot;
import com.st.nicobot.cmd.NiCommand;
import com.st.nicobot.internal.services.BehaviorsServiceImpl;
import com.st.nicobot.internal.services.CommandsImpl;
import com.st.nicobot.internal.services.MessagesImpl;
import com.st.nicobot.internal.services.SchedulerServiceImpl;
import com.st.nicobot.services.BehaviorsService;
import com.st.nicobot.services.Commands;
import com.st.nicobot.services.Messages;
import com.st.nicobot.services.SchedulerService;
import com.st.nicobot.utils.Option;

/**
 * Je me suis basé la dessus !
 * http://www.jibble.org/pircbot.php
 * @author Logan
 *
 */
public class NicoBot extends AbstractPircBot {
	
	private Messages messages = MessagesImpl.getInstance(); //bon je triche un peu, il faut dans l'ideal
															//un service/whatever qui gere tt les autres services
	private Commands commands = CommandsImpl.getInstance();
	
	private BehaviorsService behaviors = BehaviorsServiceImpl.getInstance();
	
	private SchedulerService schedulerService = SchedulerServiceImpl.getInstance();
	
	public NicoBot() {
		this("nicobot");
	}
	
	public NicoBot(String nick) {
		this.setName(nick);
		this.setAutoNickChange(true);
	}
	
	@Override
	protected void onMessage(String channel, String sender, String login, String hostname, String message) {
		super.onMessage(channel, sender, login, hostname, message);
		
		message = Colors.removeFormattingAndColors(message);
		String response = null;

		for(String pattern: messages.getSentences()) {
			if(message.matches(pattern)) {
				response = messages.getSentence(pattern);
				break;
			}
		}
			
		if (response != null) {
			response = formatMessage(response, sender, channel);
			sendMessage(channel, response);
		}
		else {
			behaviors.randomBehave(this, new Option(channel, sender, message));
		}
	}
	
	@Override
	protected void onKick(String channel, String kickerNick, String kickerLogin, String kickerHostname, 
			String recipientNick, String reason) {
		
		if(recipientNick.equals(this.getNick())) {
			joinChannel(channel);
			String msg = messages.getOtherMessage("onKick");
			sendMessage(channel, formatMessage(msg, kickerNick, null));
		}
	}
	
	@Override
	protected void onInvite(String targetNick, String sourceNick, String sourceLogin, String sourceHostname, String channel) {
		String[] strings = channel.split(" ");
		channel = strings[3];
		
		if (channel.startsWith("#zqsd")){
			joinChannel(channel);
			String msg = messages.getOtherMessage("onInvite");
			sendAction(channel, formatMessage(msg, sourceNick, null));
		}
		else {
			sendNotice(sourceNick, messages.getOtherMessage("inviteNo"));
		}
	}
	
	@Override
	protected void onJoin(String channel, String sender, String login, String hostname) {
		String msg = messages.getOtherMessage("onJoin");
		
		if (sender.equals(this.getNick())) {
			msg = messages.getOtherMessage("onSelfJoin");
		}
		
		sendMessage(channel, formatMessage(msg, sender, null));
	}
	
	/**
	 * Format une chaine de caractere en remplacant les "%p" par {@code sender} et les "%c" par {@code channel}.
	 * @param message
	 * @param sender
	 * @param channel
	 * @return
	 */
	private String formatMessage(String message, String sender, String channel) {
		message = message.replaceAll("%p", sender);
		message = message.replaceAll("%c", channel);
		
		return message;
	}
	
	@Override
	protected void onPrivateMessage(String sender, String login, String hostname, String message) {
		super.onPrivateMessage(sender, login, hostname, message);
		
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
					this, Colors.removeFormattingAndColors(arguments[0]), 
					commandArgs, new Option(null, sender, message));
		} else {
			sendNotice(sender, "T'es con ou quoi ? Une commande, c'est \"<commande> [params]\"");
		}
	}
	
	@Override
	protected void onPart(String channel, String sender, String login, String hostname) {
		super.onPart(channel, sender, login, hostname);
		
		if (! sender.equals(this.getNick())) {
			sendMessage(channel, messages.getOtherMessage("onPart"));
		}
	}
	
	@Override
	protected void onConnect() {
		super.onConnect();
		
		schedulerService.startScheduler();
	}
	
}

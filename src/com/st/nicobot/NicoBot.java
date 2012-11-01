package com.st.nicobot;

import org.jibble.pircbot.Colors;

import com.st.nicobot.bot.AbstractPircBot;
import com.st.nicobot.internal.services.BehaviorsServiceImpl;
import com.st.nicobot.internal.services.CommandsImpl;
import com.st.nicobot.internal.services.MessagesImpl;
import com.st.nicobot.services.BehaviorsService;
import com.st.nicobot.services.Commands;
import com.st.nicobot.services.Messages;
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
	
	public NicoBot() {
		this.setName("nicobot");
	}
	
	@Override
	protected void onMessage(String channel, String sender, String login, String hostname, String message) {
		super.onMessage(channel, sender, login, hostname, message);
		
		message = Colors.removeFormattingAndColors(message);
		String response = null;
		
		if (message.startsWith("!nico")){
			String cmd = message.substring("!nico ".length());
			commands.getFirstLink().handle(this, cmd, new Option(channel, sender, message));
		}
		else if (messages.getSentences().contains(message)){
			response = messages.getSentence(message);
		}
		else {
			for(String key: messages.getSentenceFragments()) {
				if(message.contains(key)) {
					response = messages.getSentenceFragment(key);
				}
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
//		System.out.println(channel);
		
		if (channel.startsWith("#zqsd")){
			joinChannel(channel);
			String msg = messages.getOtherMessage("onInvite");
			sendAction(channel, formatMessage(msg, sourceNick, null));
		}
		else {
			sendMessage(sourceNick, messages.getOtherMessage("inviteNo"));
		}
	}
	
	@Override
	protected void onJoin(String channel, String sender, String login, String hostname) {
		String msg = messages.getOtherMessage("onJoin");
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
}

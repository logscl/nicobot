package com.st.nicobot;

import java.util.Arrays;

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
	
	public NicoBot(String nick) {
		this.setName(nick);
	}
	
	@Override
	protected void onMessage(String channel, String sender, String login, String hostname, String message) {
		super.onMessage(channel, sender, login, hostname, message);
		
		message = Colors.removeFormattingAndColors(message);
		String response = null;
		
		if (messages.getSentences().contains(message)){
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
			sendNotice(sourceNick, messages.getOtherMessage("inviteNo"));
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
	
	@Override
	protected void onPrivateMessage(String sender, String login, String hostname, String message) {
		super.onPrivateMessage(sender, login, hostname, message);
		
		message = Colors.removeFormattingAndColors(message);
		
		/* Gestion des commandes à nicobot
		 * Nicobot ne réagira aux commandes qu'en pv. Une commande bien formée :
		 * 
		 * #chan command args 
		 * DONC arguments :
		 * 0 -> !nico, pour bien balancer une nicommande
		 * 1 -> non du chan, avec son cardinal (de richelieu lolilol)
		 * 2 -> la nicommande (halp,...)
		 * 3 -> les arguments de la nicommande
		 */
		String[] arguments = message.split(" ");
		String[] commandArgs = Arrays.asList(arguments).subList(3, arguments.length).toArray(new String[0]);
		
		if (arguments[0].equals("!nico")){
			if(arguments.length >= 2 && arguments[1].startsWith("#")) {
				commands.getFirstLink().handle(this, arguments[2], commandArgs, new Option(arguments[1], sender, message));
			} else {
				sendNotice(sender, "T'es con ou quoi ? Une commande, c'est \"!nico #lechan lacommande [les params]\"");
			}
		}
		// fin gestion des commandes
	}
}

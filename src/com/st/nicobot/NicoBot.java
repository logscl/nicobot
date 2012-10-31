package com.st.nicobot;

import java.util.HashMap;
import java.util.Map;

import org.jibble.pircbot.PircBot;

/**
 * Je suis basé la dessus !
 * http://www.jibble.org/pircbot.php
 * @author Logan
 *
 */
public class NicoBot extends PircBot {
	
	// les messages que nicobot écoute
	private Map<String, String> reacts = new HashMap<String, String>(); 

	public NicoBot() {
		this.setName("nicobot");
		initMessages();
	}
	
	@Override
	protected void onMessage(String channel, String sender, String login,
			String hostname, String message) {
		
		//Color strip v0.1 - on retire les codes 0 -> 15
		String rawMessage = message.replaceAll("[0|1]?[0-9]?", "");
		
		if(reacts.containsKey(rawMessage)) {
			String toSend = reacts.get(rawMessage);
			toSend = toSend.replaceAll("%p", sender);
			toSend = toSend.replaceAll("%c", channel);
			sendMessage(channel, toSend);
		}
		
		// pour refresh les messages sans reboot le bot
		initMessages();
	}
	
	private void initMessages() {
		reacts.put("nicobot ?", "Quoi ?");
		reacts.put("sisi", "la famille");
		reacts.put("13", "la famille");
		reacts.put("tf2", "Bande de casus...");
		reacts.put("salut nicobot", "Salut %p !");
		reacts.put("chut", "Tu m'dis pas chut %p, déjà");
		reacts.put("propre sur toi", "xD");
		reacts.put("nicontroleur", "ONE THIRTY TWO ONE THIRTY TWO.... REPONDEZ ONE THIRTY TWO !!! Papaaaaaaaaa~");
		reacts.put("rien", "Baaaam ! T'es deg ?");
		reacts.put("(=^;^=) pika pi", "Toi aussi tu joues à Pokemon ?");
		
		/* dangerous !
		reacts.put("boom", "omg");
		reacts.put("wtf", "bbq");
		reacts.put("omg", "wtf");
		reacts.put("bbq", "omg");
		*/
	}
	
	@Override
	protected void onKick(String channel, String kickerNick, String kickerLogin, String kickerHostname, String recipientNick, String reason) {
		if(recipientNick.equals(this.getNick())) {
			joinChannel(channel);
			sendMessage(channel, "Merci pour le kick, "+kickerNick+"...");
			return;
		}
	}
	
	@Override
	protected void onInvite(String targetNick, String sourceNick, String sourceLogin, String sourceHostname, String channel) {
		joinChannel(channel);
		sendMessage(channel, "/me remercie "+targetNick);
	}
	
	@Override
	protected void onJoin(String channel, String sender, String login, String hostname) {
		sendMessage(channel, "Yo les gars! Saluez "+sender+ " !");
	}
}

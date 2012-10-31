/**
 * 
 */
package com.st.nicobot.bot;

import org.jibble.pircbot.PircBot;

/**
 * @author Julien
 *
 */
public abstract class AbstractPircBot extends PircBot {

	@Override
	protected void onMessage(String channel, String sender, String login, String hostname, String message) {
		message = getRawMessage(message);
	}
	
	/**
	 * Retourne les messages brutes sans aucun element de style
	 * @param message
	 * 		Un message
	 * @return
	 * 		Le message nettoyé de tout element de style
	 */
	protected String getRawMessage(String message) {
		String rawMessage = colorStrip(message);
		rawMessage = boldStrip(rawMessage);
		rawMessage = italicStrip(rawMessage);
		
		return rawMessage;
	}

	/**
	 * Color strip v0.1 - on retire les codes 0 -> 15
	 * @param message
	 * 		Un message
	 * @return
	 * 		Le message nettoyé des codes couleurs
	 */
	protected String colorStrip(String message) {
		return message.replaceAll("[0|1]?[0-9]?", "");
	}
	
	/**
	 * Retourne le message sans balise de gras
	 * @param message
	 * 		Un message
	 * @return
	 * 		Le message nettoyé des balises de gras
	 */
	protected String boldStrip(String message) {
		return message; //TODO
	}
	
	/**
	 * Retourne le message sans balise d'italique
	 * @param message
	 * 		Un message
	 * @return
	 * 		Le message nettoyé des balises d'italique
	 */
	protected String italicStrip(String message) {
		return message; //TODO
	}
}

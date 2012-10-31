/**
 * 
 */
package com.st.nicobot.internal.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.st.nicobot.services.Messages;

/**
 * Une classe qui va contenir les differents messages / commandes auxquels notre cher 
 * nicobot reagir.
 * 
 * @author Julien
 *
 */
public class MessagesImpl implements Messages {

	/** Les messages que nicobot écoute */
	private Map<String, String> reacts;
		
	/** Les messages que nicobot va tenter de chercher dans des phrases */
	private Map<String, String> reactsRgx;
	
	/** Les messages de service de nicobot */
	private Map<String, String> otherMessages;

	private static MessagesImpl instance;
	
	private MessagesImpl() { }
	
	public static MessagesImpl getInstance() {
		if (instance == null) {
			instance = new MessagesImpl();
			instance.init();
		}
		
		return instance;
	}
	
	public void init() {
		reacts = new HashMap<String, String>();
		reacts.put("nicobot ?", 		"Quoi ?");
		reacts.put("sisi", 				"la famille");
		reacts.put("13", 				"la famille");
		reacts.put("tf2", 				"Bande de casus...");
		reacts.put("salut nicobot", 	"Salut %p !");
		reacts.put("chut", 				"Tu m'dis pas chut %p, déjà");
		reacts.put("propre sur toi", 	"xD");
		reacts.put("nicontroleur",		"ONE THIRTY TWO ONE THIRTY TWO.... REPONDEZ ONE THIRTY TWO !!! Papaaaaaaaaa~");
		reacts.put("rien", 				"Baaaam ! T'es deg ?");
		reacts.put("(=^;^=) pika pi",	"Toi aussi tu joues à Pokemon ?");
		reacts.put("psp", 				"Enkuler de rire !");
		reacts.put("pic", 				"or it didn't happen");
		
		/* dangerous !
		reacts.put("boom", "omg");
		reacts.put("wtf", "bbq");
		reacts.put("omg", "wtf");
		reacts.put("bbq", "omg");
		*/
		
		reactsRgx = new HashMap<String, String>();
		reactsRgx.put("grand", 	"CMB !");
		reactsRgx.put("petit", 	"CMB !");
		reactsRgx.put("cham", 	"Y'a de ces CHA-MELLES ici ! :D");
		reactsRgx.put("gamin", 	"Hein fieu");
		reactsRgx.put("hey", 	"Hey Hey !");
		reactsRgx.put("secret",	"J'ai un terrible secret aussi...");
		
		otherMessages = new HashMap<String, String>();
		otherMessages.put("onKick", 	"Merci pour le kick, %p...");
		otherMessages.put("onJoin", 	"Yo les gars! Saluez %p !");
		otherMessages.put("onInvite", 	"/me remercie %p");
	}
	
	@Override
	public Set<String> getSentences() {
		return reacts.keySet();
	}
	
	@Override
	public String getSentence(String key){
		return get(reacts, key);
	}
	
	/**
	 * Retourne l'ensemble des parties de messages auxquelles nicobot va reagir
	 * @return
	 */
	@Override
	public Set<String> getSentenceFragments() {
		return reactsRgx.keySet();
	}
	
	@Override
	public String getSentenceFragment(String key) {
		return get(reactsRgx, key);
	}
	
	@Override
	public String getOtherMessage(String key) {
		return get(otherMessages, key);
	}
	
	private String get(Map<String, String> map, String key){
		return map.get(key);
	}
}
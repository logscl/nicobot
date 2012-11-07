/**
 * 
 */
package com.st.nicobot.internal.services;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import com.st.nicobot.services.Messages;

/**
 * Une classe qui va contenir les differents messages / commandes auxquels notre cher 
 * nicobot va reagir.
 * 
 * @author Julien
 *
 */
public class MessagesImpl implements Messages {

	/** Les réactions de nicobot sous forme de regex */
    private Map<String, String> reactions;
	
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

		/**
		 * Init des réactions.
		 * 
		 * Idée v0 :
		 * nicobot peut réagir a des phrases complètes, ou des fragments.
		 * On place les phrases complètes en premier pour s'assurer que le
		 * bot réagisse à une commande complète plutôt qu'un fragment.
		 * Celui qui veut améliorer cela, il peut.
		 *
		 * Learn how to regex : http://docs.oracle.com/javase/1.4.2/docs/api/java/util/regex/Pattern.html
		 * résumé
		 * (?i) au début : case insensitive, marche aussi avec u pour unicode, et en combi (?ui)
		 * ^regex$ : match du début à la fin (pour les full words)
		 *
		 * TODO : valider les regex à l'init pour les rejeter si elles sont pourrites
		 */
		reactions = new LinkedHashMap<String, String>();

		// messages complets
		reactions.put("(?i)^nicobot( \\?)??$", 			"Quoi ?");
		reactions.put("(?i)^(sisi|13)$", 				"la famille");
		reactions.put("(?i)^tf2$", 						"Bande de casus...");
		reactions.put("(?i)^(pour )??rien( \\!)??$",	"Bam ! Bien joué %p !");
		reactions.put("(?i)^chut( !)??$",				"Tu m'dis pas chut %p, déjà");
		reactions.put("(?i)^propre sur toi$",			"xD");
		reactions.put("(?i)^\\(=\\^;\\^=\\) pika pi$",	"Toi aussi tu joues à Pokemon ?");
		reactions.put("(?i)^psp$",						"Enkuler de rire !");
		reactions.put("(?i)^pic$",						"...or it didn't happen");
		reactions.put("(?i)^secret$",					"J'ai un terrible secret aussi...");

		// fragments
		reactions.put("(?i)^salut nicobot.*", 			"Salut %p !");
		reactions.put("(?i).*gamin.*",					"Hein fieu");
		reactions.put("(?i).*hey.*",					"Hey Hey !");
		reactions.put("(?i).*grand.*",					"CMB !");
		reactions.put("(?i).*long.*",					"CMB !");
		reactions.put("(?i).*petit.*",					"CMB ! ... euh ... merde.");
		reactions.put("(?i).*court.*",					"CTB ! Hahahaha... J'me marre.");
		reactions.put("(?i).*cham.*",					"Y'a de ces CHA-MELLES ici ! :D");
		reactsRgx.put(".* nico.*",							"Nico ... avec un N majuscule ...Tu es né idiot, tu vas mourir idiot !");
		
		otherMessages = new HashMap<String, String>();
		otherMessages.put("onKick", 		"Merci pour le kick, %p...");
		otherMessages.put("onJoin", 		"Yo les gars! Saluez %p !");
		otherMessages.put("onSelfJoin",	"Yo les gars! Ovation pour %p ! Woup Woup !!");
		otherMessages.put("onInvite", 		"remercie %p");
		otherMessages.put("onLeave", 		"A plus les nb's !");
		otherMessages.put("onPart", 	"Casse toi, aller ... j'veux plus jamais t'voir !");
		
		otherMessages.put("leaveReason",	"rien.");
		otherMessages.put("helpHeader",		"Liste des commandes que nicobot connait :");
		otherMessages.put("inviteNo",		"LOL ? T'as cru ? Va t'faire refaire, ALIEN !");
		
		otherMessages.put("riverside",		"Riverside mothefoker");
		otherMessages.put("velo",			"On m'a volé mon vélooooo !!! Qui m'a volé mon vélooooo ???");
		otherMessages.put("topside",		"TOPSIDE COMIC TROIS CENT QUATRE VING QUATORZE");
		otherMessages.put("biatch",			"Ouais BIATCH !");
		otherMessages.put("ensomme",		"En somme.");
		otherMessages.put("nicontroleur",	"ONE THIRTY TWO ONE THIRTY TWO.... REPONDEZ ONE THIRTY TWO !!! Papaaaaaaaaa~");
	}
	
	@Override
	public Set<String> getSentences() {
		return reactions.keySet();
	}
	
	@Override
	public String getSentence(String key){
		return get(reactions, key);
	}
	
	@Override
	public String getOtherMessage(String key) {
		return get(otherMessages, key);
	}
	
	private String get(Map<String, String> map, String key){
		return map.get(key);
	}
}

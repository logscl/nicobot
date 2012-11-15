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
		reactions.put("(?i)^(pour )??rien( \\!)??$",	"Baaam ! Bien joué %p !");
		reactions.put("(?i)^chut( !)??$",				"Oh, tu m'dis pas chut %p, déjà");
		reactions.put("(?i)^propre sur toi$",			"De dingue !");
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
		reactions.put("(?i).*ha(i|ï)ku.*",				"Mais lol, y a pas plus débile que la formulation d'un haïku: 5-7-5.  \"Trente trois jours de pluie, Toi tu n'as que des soucis, Bite sur le gateau.\"");
		reactions.put("(?i).*amis de (m|t|s)es amis.*",	"Si tu as un ami, en fait tu en as deux. Puisque les amis de tes amis sont tes amis, et que tu es l'ami de ton ami, tu es donc ton propre ami !");
		reactions.put("(?i).*garagiste.*", 				"PUTAIN QU'ELLE EST BONNE LA GARAGIIIIISTE ! :D");
		reactions.put("(?i).*choper.*", 				"Tout le monde sait très bien que je choppe plus rien depuis P2, merci de remuer le couteau. :(");
		reactions.put(".*nico.*",						"\"Nico\" avec un N majuscule putain !  Tu es né idiot, tu vas mourir idiot !");

		// girls
		reactions.put("(?i).*sarah?.*",					"Mhmmm...  \"Avec tes deux obus, j'crois que tu te sens plus. Du quatre-vingt dix D, il en faut plus pour me faire trembler !\"");
		reactions.put("(?i).*(julie|hercot).*",			"On en reparle quand elle aura arrêté avec son équipe de meeeerde celle là.  Iiiimmmmbécile.");
		reactions.put("(?i).*(pauline).*",				"Ah ben si tu veux, moi j'en connais un rayon sur les Paulines !  P1, P2, P3 et même P4: j'ai fait toute la famille !");
		reactions.put("(?i).*(alice).*",				"T'as qu'à me dire dans quel auditoire elle a cours; j'ai un plan pour ça.");
		reactions.put("(?i).*(ga(e|ë)lle).*",			"Moi, quand une meuf un peu bourrée me propose de dormir chez elle après une bonne grosse guindaille, je préfère encore dire non tu vois.  Genre gentleman.  Où est le challenge sinon ?!");
		reactions.put("(?i).*(fairy|aur(e|é)lie|hanut).*",	"Heuuu, ouais, salut...  T'aurais pas de_cbble stp ?  En fait j'l'ai pas et on a war dans 4 minutes :(");

		otherMessages = new HashMap<String, String>();
		otherMessages.put("onKick", 		"Merci pour le kick, %p...");
		otherMessages.put("onJoin", 		"Yo les gars! Saluez %p !");
		otherMessages.put("onSelfJoin",		"Yo les gars! Ovation pour %p ! Woup Woup !!");
		otherMessages.put("onInvite", 		"remercie %p");
		otherMessages.put("onLeave", 		"A plus les nb's !");
		otherMessages.put("onPart", 		"Casse toi, aller ... j'veux plus jamais t'voir !");

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

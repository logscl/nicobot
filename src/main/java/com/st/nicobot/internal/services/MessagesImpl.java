/**
 *
 */
package com.st.nicobot.internal.services;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import com.st.nicobot.property.NicobotProperty;
import com.st.nicobot.services.Messages;
import com.st.nicobot.services.PropertiesService;

/**
 * Une classe qui va contenir les differents messages / commandes auxquels notre cher
 * nicobot va reagir.
 *
 * @author Julien
 *
 */
public class MessagesImpl implements Messages {

	/** Les réactions de nicobot sous forme de regex */
    private Map<Pattern, String> reactions;

	/** Les messages de service de nicobot */
	private Map<String, String> otherMessages;

	private static MessagesImpl instance;

	private PropertiesService props = PropertiesServiceImpl.getInstance();

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
		 * Les réactions sont traitées dans l'ordre, donc il faut placer la plus importante
		 * en premier.
		 *
		 * Learn how to regex : http://docs.oracle.com/javase/1.4.2/docs/api/java/util/regex/Pattern.html
		 *
		 * TODO : valider les regex à l'init pour les rejeter si elles sont pourrites
		 */
		reactions = new LinkedHashMap<Pattern, String>();
		String botName = props.get(NicobotProperty.BOT_NAME);

		// messages complets
		reactions.put(Pattern.compile("^"+botName+"( \\?)??$", Pattern.CASE_INSENSITIVE),		"Quoi ?");
		reactions.put(Pattern.compile("^(sisi|13)$", Pattern.CASE_INSENSITIVE), 				"la famille");
		reactions.put(Pattern.compile("^tf2$", Pattern.CASE_INSENSITIVE), 						"Bande de casus...");
		reactions.put(Pattern.compile("^(pour )??rien( \\!)??$", Pattern.CASE_INSENSITIVE),		"Baaam ! Bien joué %p !");
		reactions.put(Pattern.compile("^chut( !)??$", Pattern.CASE_INSENSITIVE),				"Oh, tu m'dis pas chut %p, déjà");
		reactions.put(Pattern.compile("^propre sur toi$", Pattern.CASE_INSENSITIVE),			"De dingue !");
		reactions.put(Pattern.compile("^\\(=\\^;\\^=\\) pika pi$", Pattern.CASE_INSENSITIVE),	"Toi aussi tu joues à Pokemon ?");
		reactions.put(Pattern.compile("^psp$", Pattern.CASE_INSENSITIVE),						"Enkuler de rire !");
		reactions.put(Pattern.compile("^pic$", Pattern.CASE_INSENSITIVE),						"...or it didn't happen");
		reactions.put(Pattern.compile("^secret$", Pattern.CASE_INSENSITIVE),					"J'ai un terrible secret aussi...");

		// fragments
		reactions.put(Pattern.compile("^salut "+botName+".*", Pattern.CASE_INSENSITIVE), 		"Salut %p !");
		reactions.put(Pattern.compile(".*gamin.*", Pattern.CASE_INSENSITIVE),					"Hein fieu");
		reactions.put(Pattern.compile(".*hey.*", Pattern.CASE_INSENSITIVE),						"Hey Hey !");
		reactions.put(Pattern.compile(".*grand.*", Pattern.CASE_INSENSITIVE),					"CMB !");
		reactions.put(Pattern.compile(".*long.*", Pattern.CASE_INSENSITIVE),					"CMB !");
		reactions.put(Pattern.compile(".*petit.*", Pattern.CASE_INSENSITIVE),					"CMB ! ... euh ... merde.");
		reactions.put(Pattern.compile(".*court.*", Pattern.CASE_INSENSITIVE),					"CTB ! Hahahaha... J'me marre.");
		reactions.put(Pattern.compile(".*cham.*", Pattern.CASE_INSENSITIVE),					"Y'a de ces CHA-MELLES ici ! :D");
		reactions.put(Pattern.compile(".*ha(i|ï)ku.*", Pattern.CASE_INSENSITIVE),				"Mais lol, y a pas plus débile que la formulation d'un haïku: 5-7-5.  \"Trente trois jours de pluie, Toi tu n'as que des soucis, Bite sur le gateau.\"");
		reactions.put(Pattern.compile(".*amis de (m|t|s)es amis.*", Pattern.CASE_INSENSITIVE),	"Si tu as un ami, en fait tu en as deux. Puisque les amis de tes amis sont tes amis, et que tu es l'ami de ton ami, tu es donc ton propre ami !");
		reactions.put(Pattern.compile(".*garagiste.*", Pattern.CASE_INSENSITIVE), 				"PUTAIN QU'ELLE EST BONNE LA GARAGIIIIISTE ! :D");
		reactions.put(Pattern.compile(".*choper.*", Pattern.CASE_INSENSITIVE), 					"Tout le monde sait très bien que je choppe plus rien depuis P2, merci de remuer le couteau. :(");
		reactions.put(Pattern.compile(".*nico( .*|$)"),												"\"Nico\" avec un N majuscule putain !  Tu es né idiot, tu vas mourir idiot !");
		reactions.put(Pattern.compile(".*ocin.*"),												"Tain mais pas à l'envers !  Ca m'énèèèèrve çaaaa !!", Pattern.CASE_INSENSITIVE);
		reactions.put(Pattern.compile(".*tracteur.*"),											"On va au Quick ?  Il est où mon saucisson ?", Pattern.CASE_INSENSITIVE);

		// girls
		reactions.put(Pattern.compile(".*sarah?.*", Pattern.CASE_INSENSITIVE),						"Mhmmm...  \"Avec tes deux obus, j'crois que tu te sens plus. Du quatre-vingt dix D, il en faut plus pour me faire trembler !\"");
		reactions.put(Pattern.compile(".*(julie|hercot).*", Pattern.CASE_INSENSITIVE),				"On en reparle quand elle aura arrêté avec son équipe de meeeerde celle là.  Iiiimmmmbécile.");
		reactions.put(Pattern.compile(".*pauline.*", Pattern.CASE_INSENSITIVE),					"Ah ben si tu veux, moi j'en connais un rayon sur les Paulines !  P1, P2, P3 et même P4: j'ai fait toute la famille !");
		reactions.put(Pattern.compile(".*alice.*", Pattern.CASE_INSENSITIVE),						"T'as qu'à me dire dans quel auditoire elle a cours; j'ai un plan pour ça.");
		reactions.put(Pattern.compile(".*(ga(e|ë)lle).*", Pattern.CASE_INSENSITIVE),				"Moi, quand une meuf un peu bourrée me propose de dormir chez elle après une bonne grosse guindaille, je préfère encore dire non tu vois.  Genre gentleman.  Où est le challenge sinon ?!");
		reactions.put(Pattern.compile(".*(fairy|aur(e|é)lie|hanut).*", Pattern.CASE_INSENSITIVE),	"Heuuu, ouais, salut...  T'aurais pas de_cbble stp ?  En fait j'l'ai pas et on a war dans 4 minutes :(");

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
	public Set<Pattern> getSentences() {
		return reactions.keySet();
	}

	@Override
	public String getSentence(Pattern key){
		return reactions.get(key);
	}

	@Override
	public String getOtherMessage(String key) {
		return otherMessages.get(key);
	}
}

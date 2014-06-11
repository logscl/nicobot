package com.st.nicobot.internal.services;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.picocontainer.annotations.Inject;

import com.st.nicobot.bot.utils.NicobotProperty;
import com.st.nicobot.bot.utils.Random;
import com.st.nicobot.bot.utils.Reaction;
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

	@Inject
	private PropertiesService props;
	
	/** Les réactions de nicobot sous forme de regex */
    private Set<Reaction> reactions;

	/** Les messages de service de nicobot */
	private Map<String, String> otherMessages;
	
	/** Les messages de bienvenue de nicobot */
	private Map<String, String> welcomeMessages;

	public MessagesImpl() {	}
	
	public void start() {
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
		String botName = props.get(NicobotProperty.BOT_NAME);
		
		reactions = new LinkedHashSet<Reaction>();
		
		// messages complets
		reactions.add(new Reaction("^"+botName+"( \\?)??$", 		"Quoi ?"));
		reactions.add(new Reaction("^(sisi|13)$", 					"la famille"));
		reactions.add(new Reaction("^tf2$", 						"Bande de casus..."));
		reactions.add(new Reaction("^(pour )??rien( \\!)??$", 		"Baaam ! Bien joué %p !"));
		reactions.add(new Reaction("^chut( !)??$", 					"Oh, tu m'dis pas chut %p, déjà"));
		reactions.add(new Reaction("^propre sur toi$", 				"De dingue !"));
		reactions.add(new Reaction("^\\(=\\^;\\^=\\) pika pi$", 	"Toi aussi tu joues à Pokemon ?"));
		reactions.add(new Reaction("^psp$", 						"Enkuler de rire !"));
		reactions.add(new Reaction("^pic$", 						"...or it didn't happen"));
		reactions.add(new Reaction("^secret$", 						"J'ai un terrible secret aussi..."));

		// fragments
		reactions.add(new Reaction("^salut "+botName+".*", 			"Salut %p !"));
		reactions.add(new Reaction(".*gamin.*",						"Hein fieu"));
		reactions.add(new Reaction(".*hey.*",						"Hey Hey !"));
		reactions.add(new Reaction(".*grand.*",						"CMB !"));
		reactions.add(new Reaction(".*long.*",						"CMB !"));
		reactions.add(new Reaction(".*petit.*",						"CMB ! ... euh ... merde."));
		reactions.add(new Reaction(".*court.*",						"CTB ! Hahahaha... J'me marre."));
		reactions.add(new Reaction(".*cham.*",						"Y'a de ces CHA-MELLES ici ! :D"));
		reactions.add(new Reaction(".*ha(i|ï)ku.*",					"Mais lol, y a pas plus débile que la formulation d'un haïku: 5-7-5.  \"Trente trois jours de pluie, Toi tu n'as que des soucis, Bite sur le gateau.\""));
		reactions.add(new Reaction(".*amis de (m|t|s)es amis.*",	"Si tu as un ami, en fait tu en as deux. Puisque les amis de tes amis sont tes amis, et que tu es l'ami de ton ami, tu es donc ton propre ami !"));
		reactions.add(new Reaction(".*garagiste.*", 				"PUTAIN QU'ELLE EST BONNE LA GARAGIIIIISTE ! :D"));
		reactions.add(new Reaction(".*choper.*", 					"Tout le monde sait très bien que je choppe plus rien depuis P2, merci de remuer le couteau. :("));
		reactions.add(new Reaction(".*nico( .*|$)",					"\"Nico\" avec un N majuscule putain !  Tu es né idiot, tu vas mourir idiot !", false, 20));
		reactions.add(new Reaction(".*ocin.*",						"Tain mais pas à l'envers !  Ca m'énèèèèrve çaaaa !!"));
		reactions.add(new Reaction(".*tracteur.*",					"On va au Quick ?  Il est où mon saucisson ?"));

		// girls
		reactions.add(new Reaction(".*sarah?.*",					"Mhmmm...  \"Avec tes deux obus, j'crois que tu te sens plus. Du quatre-vingt dix D, il en faut plus pour me faire trembler !\"", true, 30));
		reactions.add(new Reaction(".*(julie|hercot) .*",			"On en reparle quand elle aura arrêté avec son équipe de meeeerde celle là.  Iiiimmmmbécile.", true, 30));
		reactions.add(new Reaction(".*pauline.*",					"Ah ben si tu veux, moi j'en connais un rayon sur les Paulines !  P1, P2, P3 et même P4: j'ai fait toute la famille !", true, 30));
		reactions.add(new Reaction(".*alice.*",						"T'as qu'à me dire dans quel auditoire elle a cours; j'ai un plan pour ça.", true, 30));
		reactions.add(new Reaction(".*(ga(e|ë)lle).*",				"Moi, quand une meuf un peu bourrée me propose de dormir chez elle après une bonne grosse guindaille, je préfère encore dire non tu vois.  Genre gentleman.  Où est le challenge sinon ?!", true, 30));
		reactions.add(new Reaction(".*(fairy|aur(e|é)lie|hanut).*",	"Heuuu, ouais, salut...  T'aurais pas de_cbble stp ?  En fait j'l'ai pas et on a war dans 4 minutes :(", true, 30));

		otherMessages = new HashMap<String, String>();
		otherMessages.put("onKick", 		"Merci pour le kick, %p...");
		otherMessages.put("onSelfJoin",		"Yo les gars! Ovation pour %p ! Woup Woup !!");
		otherMessages.put("onInvite", 		"remercie %p");
		otherMessages.put("onLeave", 		"A plus les nb's !");
		otherMessages.put("onPart", 		"Casse toi, aller ... j'veux plus jamais t'voir !");

		otherMessages.put("leaveReason",	"rien.");
		otherMessages.put("helpHeader",		"Liste des commandes que nicobot connait :");
		otherMessages.put("inviteNo",		"LOL ? T'as cru ? Va t'faire refaire, ALIEN !");
		otherMessages.put("helpNotFound", 	"J'veux bien t'aider, mais je vois pas bien ce que tu me veux la -_-");

		otherMessages.put("riverside",		"Riverside motherfoker");
		otherMessages.put("velo",			"On m'a volé mon vélooooo !!! Qui m'a volé mon vélooooo ???");
		otherMessages.put("topside",		"TOPSIDE COMIC TROIS CENT QUATRE VING QUATORZE");
		otherMessages.put("biatch",			"Ouais BIATCH !");
		otherMessages.put("ensomme",		"En somme.");
		otherMessages.put("nicontroleur",	"ONE THIRTY TWO ONE THIRTY TWO.... REPONDEZ ONE THIRTY TWO !!! Papaaaaaaaaa~");
		otherMessages.put("durmaisjuste",	"C'est dur, mais c'est juste.");
		otherMessages.put("chepatse",		"Ché pa tsé...");
		otherMessages.put("standard",		"Staaaannnndard de merde olé oléééééé");
		otherMessages.put("wouhou",			"WOUUUH WOUUUUUUHHH WOUUUUUUUUUUHHH WOUUUUUUUUUUUUUHHHHH");
		otherMessages.put("tesqui",			"T'es qui ?");
		otherMessages.put("anecdote",		"C'est une anecdote de MALADE ça !");
		
		otherMessages.put("hgt",			"!!§!!§§!!§ Happy Geek Time !!§!!§§!!§");
		
		otherMessages.put("kickError",		"randomkick <#channel>. BIATCH !");
		otherMessages.put("kickInit",		"Y'a %s qui m'a demandé de kicker quelqu'un, alors...");
		otherMessages.put("kickReason",		"Le prends pas mal hein... on reste amis ?");
		otherMessages.put("kickLose",		"BIEN FAIT HAHA !");
		otherMessages.put("kickWin",		"Gamin !! Allez viens ! C'était pour rire !");
		otherMessages.put("riamaskin",		"Ca suffit maintenant ! C’est excessivement énervant !");
		otherMessages.put("noLastMsg",		"Aucun message n'a été échangé lors des 5 dernieres minutes");
		otherMessages.put("lastMsgHeader",	"Derniers messages échangés :");
		otherMessages.put("noHGT", 			"Vous me souhaitez pas un Happy Geek Time à moi ? Ingrats ! :(");
		otherMessages.put("congratHGT", 	"Félicitations à %s ! Propre sur vous !");
		otherMessages.put("congratSoloHGT",	"Bravo %s ! Au moins toi tu y as pensé <3 !");
		
		welcomeMessages = new HashMap<String, String>();
		welcomeMessages.put("newJoin0",		"Yo les gars! Saluez %p !");
		welcomeMessages.put("newJoin1",		"Coucou %p ! Ca va bien ?");
		welcomeMessages.put("newJoin2",		"BOOM ! %p est dans la place !");
		
		welcomeMessages.put("join1", 		"Hé ! Encore toi %p !");
		welcomeMessages.put("join2", 		"OMG T'es revenu %p !");
		welcomeMessages.put("join3",		"Euh Ca fait 3 fois aujourd'hui %p, T'en as pas marre ?");
		welcomeMessages.put("join4",		"T'es branché sur une guirlande de Noël %p ?");
		welcomeMessages.put("join5",		"CA SUFFIT %p ! Maintenant, tu t'achètes une connexion !!");
	}

	@Override
	public Set<Reaction> getSentences() {
		return reactions;
	}

	@Override
	public String getOtherMessage(String key) {
		return otherMessages.get(key);
	}
	
	@Override
	public String getWelcomeMessage(Integer nbr) {
		if(nbr.equals(0)) {
			return welcomeMessages.get("newJoin" + Random.nextInt(3));
		} else {
			return welcomeMessages.get("join"+nbr.toString());
		}
	}
}

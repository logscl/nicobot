package com.st.nicobot.bot.handler;

import java.util.HashMap;
import java.util.Map;

import org.picocontainer.annotations.Inject;

import com.st.nicobot.bot.NicoBot;
import com.st.nicobot.bot.event.JoinEvent;
import com.st.nicobot.services.Messages;

public class WelcomeUsers implements JoinEvent {

	@Inject
	private Messages messages;
	
	private Map<String, Map<String, Integer>> userJoins = new HashMap<String, Map<String, Integer>>();
	
	@Override
	public void onJoin(String channel, String sender, String login,	String hostname, NicoBot nicobot) {
		
		String msg;
		
		// J'aurais pu splitter l'action en 2 classes : 
		// une qui fait l'égo greeting, 
		// et l'autre qui s'occupe des nouveaux arrivants.
		// J'aurais pu.
		if (sender.equals(nicobot.getNick())) {
			msg = messages.getOtherMessage("onSelfJoin");
		} else {
			// remplir userJoin + retourner message
			msg = messages.getWelcomeMessage(addJoin(channel, sender));
		}
		
		if(msg != null) {
			nicobot.sendMessage(channel, nicobot.formatMessage(msg, sender, null));
		}

	}
	
	/**
	 * Ajoute un join, et retourne le nombre de joins pour l'utilisateur
	 */
	private int addJoin(String channel, String username) {
		if(!userJoins.containsKey(channel)) {
			userJoins.put(channel, new HashMap<String, Integer>());
		}
		Map<String, Integer> tmpMap = userJoins.get(channel);
		if(!tmpMap.containsKey(username)) {
			tmpMap.put(username, 0);
			return 0;
		} else {
			int curJoins = tmpMap.get(username).intValue();
			tmpMap.put(username, ++curJoins);
			return curJoins;
		}
	}

}

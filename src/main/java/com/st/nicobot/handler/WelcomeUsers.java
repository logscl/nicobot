package com.st.nicobot.handler;

import com.st.nicobot.NicoBot;
import com.st.nicobot.event.JoinEvent;
import com.st.nicobot.internal.services.MessagesImpl;
import com.st.nicobot.services.Messages;

public class WelcomeUsers implements JoinEvent {

	private Messages messages = MessagesImpl.getInstance();
	
	@Override
	public void onJoin(String channel, String sender, String login,	String hostname, NicoBot nicobot) {
		String msg = messages.getOtherMessage("onJoin");
		
		// J'aurais pu splitter l'action en 2 classes : 
		// une qui fait l'égo greeting, 
		// et l'autre qui s'occupe des nouveaux arrivants.
		// J'aurais pu.
		if (sender.equals(nicobot.getNick())) {
			msg = messages.getOtherMessage("onSelfJoin");
		}
		
		nicobot.sendMessage(channel, nicobot.formatMessage(msg, sender, null));

	}

}

package com.st.nicobot.handler;

import com.st.nicobot.NicoBot;
import com.st.nicobot.event.PartEvent;
import com.st.nicobot.internal.services.MessagesImpl;
import com.st.nicobot.services.Messages;

public class GTFO implements PartEvent {

	private Messages messages = MessagesImpl.getInstance();
	
	@Override
	public void onPart(String channel, String sender, String login,	String hostname, NicoBot nicobot) {
		if (!sender.equals(nicobot.getNick())) {
			nicobot.sendMessage(channel, messages.getOtherMessage("onPart"));
		}
	}

}

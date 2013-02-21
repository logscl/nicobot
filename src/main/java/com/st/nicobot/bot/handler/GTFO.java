package com.st.nicobot.bot.handler;

import org.picocontainer.annotations.Inject;

import com.st.nicobot.bot.NicoBot;
import com.st.nicobot.bot.event.PartEvent;
import com.st.nicobot.services.Messages;

public class GTFO implements PartEvent {

	@Inject
	private Messages messages;
	
	@Override
	public void onPart(String channel, String sender, String login,	String hostname, NicoBot nicobot) {
		if (!sender.equals(nicobot.getNick())) {
			nicobot.sendMessage(channel, messages.getOtherMessage("onPart"));
		}
	}

}

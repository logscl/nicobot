package com.st.nicobot.bot.handler;

import org.picocontainer.annotations.Inject;

import com.st.nicobot.bot.NicoBot;
import com.st.nicobot.bot.event.PartEvent;
import com.st.nicobot.services.Messages;

public class GTFO implements PartEvent {

	@Inject
	private Messages messages;
	
	@Inject
	private NicoBot nicobot;
	
	@Override
	public void onPart(String channel, String sender, String login,	String hostname) {
		if (!sender.equals(nicobot.getNick())) {
			nicobot.sendChannelMessage(channel, messages.getOtherMessage("onPart"));
		}
	}

}

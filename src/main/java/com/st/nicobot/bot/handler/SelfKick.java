package com.st.nicobot.bot.handler;

import org.picocontainer.annotations.Inject;

import com.st.nicobot.bot.NicoBot;
import com.st.nicobot.bot.event.KickEvent;
import com.st.nicobot.services.Messages;

public class SelfKick implements KickEvent {

	@Inject
	private Messages messages;
	
	@Override
	public void onKick(String channel, String kickerNick, String kickerLogin, String kickerHostname, String recipientNick, String reason, NicoBot nicobot) {
		
		if(recipientNick.equals(nicobot.getNick())) {
			nicobot.joinChannel(channel);
			String msg = messages.getOtherMessage("onKick");
			nicobot.sendMessage(channel, nicobot.formatMessage(msg, kickerNick, null));
		}
		
	}
	
	

}

package com.st.nicobot.handler;

import com.st.nicobot.NicoBot;
import com.st.nicobot.event.KickEvent;
import com.st.nicobot.internal.services.MessagesImpl;
import com.st.nicobot.services.Messages;

public class SelfKick implements KickEvent {

	private Messages messages = MessagesImpl.getInstance();
	
	@Override
	public void onKick(String channel, String kickerNick, String kickerLogin, String kickerHostname, String recipientNick, String reason, NicoBot nicobot) {
		
		if(recipientNick.equals(nicobot.getNick())) {
			nicobot.joinChannel(channel);
			String msg = messages.getOtherMessage("onKick");
			nicobot.sendMessage(channel, nicobot.formatMessage(msg, kickerNick, null));
		}
		
	}
	
	

}

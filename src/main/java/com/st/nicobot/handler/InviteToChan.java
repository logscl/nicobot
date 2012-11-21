package com.st.nicobot.handler;

import com.st.nicobot.NicoBot;
import com.st.nicobot.event.InviteEvent;
import com.st.nicobot.internal.services.MessagesImpl;
import com.st.nicobot.internal.services.PropertiesServiceImpl;
import com.st.nicobot.property.NicobotProperty;
import com.st.nicobot.services.Messages;
import com.st.nicobot.services.PropertiesService;

public class InviteToChan implements InviteEvent {

	private PropertiesService props = PropertiesServiceImpl.getInstance();
	
	private Messages messages = MessagesImpl.getInstance();
	
	@Override
	public void onInvite(String targetNick, String sourceNick, String sourceLogin, String sourceHostname, String channel, NicoBot nicobot) {
		String[] strings = channel.split(" ");
		channel = strings[3];
		
		if (channel.startsWith(props.get(NicobotProperty.BOT_CHAN))){
			nicobot.joinChannel(channel);
			String msg = messages.getOtherMessage("onInvite");
			nicobot.sendAction(channel, nicobot.formatMessage(msg, sourceNick, null));
		}
		else {
			nicobot.sendNotice(sourceNick, messages.getOtherMessage("inviteNo"));
		}
	}

}

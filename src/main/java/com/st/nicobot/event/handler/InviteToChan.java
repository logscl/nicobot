package com.st.nicobot.event.handler;

import org.picocontainer.annotations.Inject;

import com.st.nicobot.NicoBot;
import com.st.nicobot.event.InviteEvent;
import com.st.nicobot.property.NicobotProperty;
import com.st.nicobot.services.Messages;
import com.st.nicobot.services.PropertiesService;

public class InviteToChan implements InviteEvent {

	@Inject
	private PropertiesService props;
	
	@Inject
	private Messages messages;
	
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

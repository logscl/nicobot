package com.st.nicobot.bot.handler;

import java.util.Collections;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.picocontainer.annotations.Inject;

import com.st.nicobot.api.domain.model.Message;
import com.st.nicobot.api.services.APIMessageService;
import com.st.nicobot.bot.NicoBot;
import com.st.nicobot.bot.event.JoinEvent;
import com.st.nicobot.services.Messages;

/**
 * @author Julien
 *
 */
public class SendLastMessages implements JoinEvent {

	private static final String MESSAGE_FORMAT = "  [%s] <%s> : %s";
	
	@Inject
	private APIMessageService apiMessageService;
	
	@Inject
	private Messages messages;
	
	@Inject
	private NicoBot nicobot;
	
	@Override
	public void onJoin(final String channel, final String sender, final String login, final String hostname) {
		if (!sender.equals(nicobot.getNick())) {
			Thread t = new Thread() {
				public void run() {
					List<Message> lastMessages = apiMessageService.getLastMessages(null, null);
					
					if (lastMessages.size() == 0) {
						nicobot.sendNotice(sender, messages.getOtherMessage("noLastMsg"));
					} 
					else {
						Collections.reverse(lastMessages);
						
						nicobot.sendNotice(sender, messages.getOtherMessage("lastMsgHeader"));
						
						for(Message msg : lastMessages) {
							nicobot.sendNotice(sender, formatMessage(msg));
						}
					}
				}
			};
			
			t.start();
		}
	}

	static String formatMessage(Message msg) {
		DateTime postedDateServerTimezone = msg.getPostedDate().withZone(DateTimeZone.forID("Europe/Paris"));
		return String.format(MESSAGE_FORMAT, postedDateServerTimezone.toString("HH:mm"), msg.getUsername(), msg.getMessage());
	}
	
	

}
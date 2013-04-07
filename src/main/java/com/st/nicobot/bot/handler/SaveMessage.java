package com.st.nicobot.bot.handler;

import java.util.Arrays;

import org.joda.time.DateTime;
import org.picocontainer.annotations.Inject;

import com.st.nicobot.api.domain.model.Message;
import com.st.nicobot.api.services.APIMessageService;
import com.st.nicobot.bot.NicoBot;
import com.st.nicobot.bot.event.MessageEvent;

/**
 * @author Julien
 *
 */
public class SaveMessage implements MessageEvent{

	@Inject
	private APIMessageService apiMessageService;
	
	@Inject
	private NicoBot nicoBot;
	
	@Override
	public void onMessage(final String channel, final String sender, final String login, final String hostname, final String message) {
		Thread t = new Thread() {
			public void run() {
				Message msg = new Message(new DateTime(), sender, message);
				apiMessageService.saveMessages(Arrays.asList(msg));
			}
		};
		
		t.start();
	}

}

package com.st.nicobot.bot.handler;

import java.util.Date;

import org.jibble.pircbot.Colors;
import org.picocontainer.annotations.Inject;

import com.st.nicobot.bot.NicoBot;
import com.st.nicobot.bot.event.MessageEvent;
import com.st.nicobot.bot.utils.Reaction;
import com.st.nicobot.services.LeetGreetingService;
import com.st.nicobot.services.Messages;

public class ParseReactions implements MessageEvent {

	@Inject
	private Messages messages;
	
	@Inject
	private NicoBot nicobot;
	
	@Inject
    private LeetGreetingService greetingService;
	
	@Override
	public void onMessage(String channel, String sender, String login, String hostname, String message) {

		message = Colors.removeFormattingAndColors(message);
		String response = null;
		
		for(Reaction reac : messages.getSentences()) {
			// reaction ok
			if(reac.match(message)) {
				// déjà dit ?
				if(reac.canSaySentence(channel)) {
					reac.addSpokenTime(channel, new Date());
					response = reac.getResponse();
				}
				break;
			}
		}
		
		if (response != null) {
			response = nicobot.formatMessage(response, sender, channel);
			nicobot.sendChannelMessage(channel, response);
		}
		
		// Happy Hour handling
		if(greetingService.isLeetHourActive()) {
            greetingService.addGreeter(channel,sender,message);
        }

	}

}

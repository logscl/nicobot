package com.st.nicobot.handler;

import java.util.regex.Pattern;

import org.jibble.pircbot.Colors;

import com.st.nicobot.NicoBot;
import com.st.nicobot.event.MessageEvent;
import com.st.nicobot.internal.services.MessagesImpl;
import com.st.nicobot.services.Messages;

public class ParseReactions implements MessageEvent {

	private Messages messages = MessagesImpl.getInstance();
	
	@Override
	public void onMessage(String channel, String sender, String login, String hostname, String message, NicoBot nicobot) {

		message = Colors.removeFormattingAndColors(message);
		String response = null;

		for(Pattern pattern: messages.getSentences()) {
			if(pattern.matcher(message).matches()) {
				response = messages.getSentence(pattern);
				break;
			}
		}
			
		if (response != null) {
			response = nicobot.formatMessage(response, sender, channel);
			nicobot.sendMessage(channel, response);
		}

	}

}

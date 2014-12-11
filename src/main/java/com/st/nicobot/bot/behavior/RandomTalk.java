package com.st.nicobot.bot.behavior;

import org.picocontainer.annotations.Inject;

import com.st.nicobot.bot.NicoBot;
import com.st.nicobot.bot.utils.Option;
import com.st.nicobot.services.Messages;

/**
 * @author Julien
 *
 */
public class RandomTalk implements NiConduct {

	@Inject
	private Messages messages;
	
	public RandomTalk() {	}
	
	@Override
	public int getChance() {
		return 50;
	}

	@Override
	public void behave(NicoBot nicobot, Option opts) {
		nicobot.sendChannelMessage(opts.channel, getRandomTalk());
	}
	
	protected String getRandomTalk() {
		return messages.getRandomSpeech();
	}
}
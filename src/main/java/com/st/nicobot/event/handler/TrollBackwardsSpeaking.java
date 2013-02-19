package com.st.nicobot.event.handler;

import java.util.Random;

import org.apache.commons.lang3.StringUtils;

import com.st.nicobot.NicoBot;
import com.st.nicobot.event.MessageEvent;

/**
 * @author Julien
 *
 */
public class TrollBackwardsSpeaking implements MessageEvent {

	private Random random = com.st.nicobot.utils.Random.getInstance();
	
	@Override
	public void onMessage(String channel, String sender, String login, String hostname, String message, NicoBot nicobot) {
		int chance = 1000 - random.nextInt(1000);
		
		if (StringUtils.reverse(message).equals(message)) {
			if (chance < getChance()) {
				nicobot.sendMessage(channel, message);
			}
		}
	}

	/*
	 * En utilisant les ‰ on beneficie d'une granularité d'activation plus fine tout en evitant de travailler avec de vils double/float !
	 * 100% 	=> 1		1000‰ 	=> 100%
	 * 10%		=> 0.1		100‰ 	=> 10%
	 * 0.1%		=> 0.001	1‰ 		=> 0.1%
	 */
	/**
	 * <p>Retourne le taux d'activation pour ce comportement (en ‰).</p>
	 * @return
	 */
	public int getChance() {
		return 250;
	}

}

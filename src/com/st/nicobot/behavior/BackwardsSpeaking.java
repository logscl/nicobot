/**
 * 
 */
package com.st.nicobot.behavior;

import com.st.nicobot.NicoBot;
import com.st.nicobot.utils.Option;

/**
 * @author Julien
 *
 */
public class BackwardsSpeaking implements NiConduct {

	@Override
	public int getChance() {
		return 50;
	}

	@Override
	public void behave(NicoBot nicobot, Option opts) {
		nicobot.sendMessage(opts.channel, reverseString(opts.message));
	}
	
	protected String reverseString(String string) {
		return new StringBuilder(string).reverse().toString();
	}

}

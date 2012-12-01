package com.st.nicobot.behavior;

import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;

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
	
	/**
	 * Retourne {@code string} renversé. <br/>
	 * Si une majuscule est présente sur le 1er caractere de {@code string}, 
	 * alors le 1er car de la chaine renversée sera en maj aussi.
	 * @param string
	 * @return
	 */
	protected String reverseString(String string) {
		boolean isFirstLetterUpperCase = CharUtils.isAsciiAlphaUpper(string.charAt(0));

		StringBuilder builder = new StringBuilder(string).reverse();
		
		if (isFirstLetterUpperCase) {
			String firstCharSequence = builder.substring(0, 1);
			String lastCharSequence = builder.substring(builder.length()-1);
			
			builder.replace(0, 1, StringUtils.upperCase(firstCharSequence));
			builder.replace(builder.length()-1, builder.length(), StringUtils.lowerCase(lastCharSequence));
		}
		
		return builder.toString();
	}

}
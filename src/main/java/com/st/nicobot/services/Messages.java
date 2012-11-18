/**
 * 
 */
package com.st.nicobot.services;

import java.util.Set;
import java.util.regex.Pattern;

/**
 * @author Julien
 *
 */
public interface Messages {

	/**
	 * Retourne l'ensemble des messages auxquels nicobot va reagir
	 * @return
	 */
	public Set<Pattern> getSentences();
	public String getSentence(Pattern key);
	
	public String getOtherMessage(String key);
}
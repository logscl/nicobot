/**
 * 
 */
package com.st.nicobot.services;

import java.util.Set;

/**
 * @author Julien
 *
 */
public interface Messages {

	/**
	 * Retourne l'ensemble des messages auxquels nicobot va reagir
	 * @return
	 */
	public Set<String> getSentences();
	public String getSentence(String key);
	
	public String getOtherMessage(String key);
}
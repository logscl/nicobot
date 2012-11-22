/**
 * 
 */
package com.st.nicobot.services;

import java.util.Set;

import com.st.nicobot.reaction.Reaction;

/**
 * @author Julien
 *
 */
public interface Messages {

	/**
	 * Retourne l'ensemble des messages auxquels nicobot va reagir
	 * @return
	 */
	public Set<Reaction> getSentences();
	
	public String getOtherMessage(String key);
}
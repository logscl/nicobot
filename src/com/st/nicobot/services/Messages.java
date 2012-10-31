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
	
	/**
	 * Retourne l'ensemble des parties de messages auxquelles nicobot va reagir
	 * @return
	 */
	public Set<String> getSentenceFragments();
	public String getSentenceFragment(String key);
	
	public String getOtherMessage(String key);
}
/**
 * 
 */
package com.st.nicobot.cmd;

import com.st.nicobot.NicoBot;

/**
 * 
 * @author Julien
 *
 */
public interface NiCommand {

	/**
	 * Retourne la chaine de caractere à partir de laquelle nico doit etre commandé
	 */
	public String getCommandName();

	/**
	 * Retourne une description pour la commande
	 * @return
	 */
	public String getDescription();
	
	/**
	 * Code à executer par la commande
	 */
	public void handle(NicoBot nicobot);
	
}

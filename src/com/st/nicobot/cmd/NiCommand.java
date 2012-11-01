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
public abstract class NiCommand {

	protected NiCommand nextCommand;
	
	/**
	 * Retourne la chaine de caractere à partir de laquelle nico doit etre commandé
	 */
	public abstract String getCommandName();

	/**
	 * Retourne une description pour la commande
	 * @return
	 */
	public abstract String getDescription();
	
	/**
	 * Code à executer par la commande
	 */
	protected abstract void doCommand(NicoBot nicobot, String command, Option opts);
	
	public void setNext(NiCommand niCommand){
		nextCommand = niCommand;
	}
	
	public void handle(NicoBot nicobot, String command, Option opts) {
		if (command.startsWith(getCommandName())){
			this.doCommand(nicobot, command, opts);
		}

		if (nextCommand != null) {
			nextCommand.handle(nicobot, command, opts);
		}
	}
	
}

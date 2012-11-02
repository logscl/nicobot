/**
 * 
 */
package com.st.nicobot.cmd;

import java.util.Arrays;

import sun.net.dns.ResolverConfiguration.Options;

import com.st.nicobot.NicoBot;
import com.st.nicobot.utils.Option;

/**
 * 
 * @author Julien
 *
 */
public abstract class NiCommand {

	/** Maillon suivant de la chaine */
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
	 * @param nicobot
	 * 		TEH Nicobot
	 * @param command
	 * 		La commande
	 * @param args
	 * 		Les arguments de la commande (peut etre null)
	 * @param opts
	 *		Les options {@link Option} 
	 */
	protected abstract void doCommand(NicoBot nicobot, String command, String[] args, Option opts);
	
	/**
	 * Ajoute un nouveau maillon à la chaine
	 * @param niCommand
	 */
	public void setNext(NiCommand niCommand){
		nextCommand = niCommand;
	}
	
	/**
	 * <p>Determine si l'implementation est capable de gérer la commande.</p>
	 * <p>Si oui, alors {@link NiCommand#doCommand(NicoBot, String, String[], Option)} est appelé.</p>
	 * <p>Si non, alors on passe le relais au maillon suivant ({@link NiCommand#nextCommand})</p>
	 * @param nicobot
	 * 		TEH nicobot
	 * @param command
	 * 		La commande "brute" seule
	 * @param
	 * 		Les arguments de la commande (sans le nom du chan, ni la commande)
	 * @param opts
	 * 		Les options {@link Options}
	 */
	public void handle(NicoBot nicobot, String command, String[] arguments, Option opts) {
		
		if (command.startsWith(getCommandName())){
			this.doCommand(nicobot, command, arguments, opts);
		}

		if (nextCommand != null) {
			nextCommand.handle(nicobot, command, arguments, opts);
		}
	}
	
	protected String[] getArgs(String[] explodedCommand) {
		String[] args = null;

		if (explodedCommand.length > 1) {
			args = Arrays.copyOfRange(explodedCommand, 1, explodedCommand.length);
		}
		
		return args;
	}
	
}

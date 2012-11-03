/**
 * 
 */
package com.st.nicobot.cmd;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	
	/*
	 * Retourne le format de la commande
	 */
	public abstract String getFormat();

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
	
	/** Regex pour trouver une chaine de caractere encadrée par " " */
	private static final Pattern REGEX_FIND_STRING = Pattern.compile("\"(.)*\"");
	private static final String REPlACE_VALUE = "inputString";
	
	public static String[] getArgs(String arguments) {
		Matcher matcher = REGEX_FIND_STRING.matcher(arguments);
		
		String inputString = null;
		
		if (matcher.find()) {
			inputString = matcher.group();
			arguments = matcher.replaceFirst(REPlACE_VALUE);
		}
		
		String[] explodedArgs = arguments.split(" ");
		boolean needToContinue = true;
		
		for (int i = 0; i < explodedArgs.length && needToContinue; i++) {
			if (explodedArgs[i].equals(REPlACE_VALUE)) {
				
				// on vire le " en debut et en fin de chaine
				explodedArgs[i] = inputString.substring(1, inputString.length()-1);
				needToContinue = false;
			}
		}
		
		return explodedArgs;
		
	}
	
}

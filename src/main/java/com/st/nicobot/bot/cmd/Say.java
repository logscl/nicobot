package com.st.nicobot.bot.cmd;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.jibble.pircbot.Colors;

import com.st.nicobot.bot.NicoBot;
import com.st.nicobot.bot.utils.Option;

public class Say extends NiCommand {
	
	private static final String COMMAND = "say";
	private static final String FORMAT = "say <#channel> \"<message>\" [keepFormatting (0|1)]";
	private static final String DESC = "Fait parler le bot.";

	@Override
	public String getCommandName() {
		return COMMAND;
	}

	@Override
	public String getDescription() {
		return DESC;
	}
	
	@Override
	public String getFormat() {
		return FORMAT;
	}
	
	@Override
	public boolean isAdminRequired() {
		return false;
	}

	@Override
	protected void doCommand(NicoBot nicobot, String command, String[] args, Option opts) {
		try {
			SayArguments arguments = new SayArguments(args);
			String message = arguments.message;
			
			if (! arguments.keepFormatting ) {
				message = Colors.removeFormattingAndColors(arguments.message);
			}
			
			nicobot.sendChannelMessage(arguments.channel, message);
		}
		catch (IllegalArgumentException ex) {
			nicobot.sendNotice(opts.sender, "Malformed command, format : " + getFormat());
		}
	}
	
	/**
	 *	Ne fait rien. 
	 */
	@Override
	protected String[] removeFormattingAndColors(String[] args) {
		return args;
	}
	
	private class SayArguments {
		public final String channel;
		public final String message;
		public final boolean keepFormatting;
		
		public SayArguments(String[] arguments) throws IllegalArgumentException {
		
			// si l'arg keepFormatting n'est pas la, on autorise quand meme la création de l'objet
			if (arguments == null || arguments.length < 2) {	
				throw new IllegalArgumentException(); 
			}

			// on verifie que le 1er char du 1er arg est un # (pour le channel)
			if (arguments[0].charAt(0) != '#') {
				throw new IllegalArgumentException();
			}
			
			channel = arguments[0];			
			message = arguments[1];
			
			if (arguments.length == 3) {
				// si erreur de conversion, la valeur par defaut est 0 (donc false)
				keepFormatting = BooleanUtils.toBoolean(NumberUtils.toInt(arguments[2]));
			}
			else {
				keepFormatting = false;
			}
		}
	}

}

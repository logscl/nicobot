package com.st.nicobot.cmd;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.jibble.pircbot.Colors;

import com.st.nicobot.NicoBot;
import com.st.nicobot.utils.Option;

public class Say extends NiCommand {
	
	private static final String COMMAND = "say";
	private static final String FORMAT = "say <#channel> <message> <keepFormatting>";
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
	protected void doCommand(NicoBot nicobot, String command, String[] args, Option opts) {
		boolean keepFormatting = false;
		
		if (args.length != 1) {
			keepFormatting = BooleanUtils.toBoolean(NumberUtils.toInt(args[1]));	// si erreur de conversion, la valeur par defaut est 0 (donc false)
		}
		
		String message = args[0];
		
		if (! keepFormatting) {
			message = Colors.removeFormattingAndColors(message);
		}
		
		nicobot.sendMessage(opts.channel, message);
	}
	
	/**
	 *	Ne fait rien. 
	 */
	@Override
	protected String[] removeFormattingAndColors(String[] args) {
		return args;
	}

}

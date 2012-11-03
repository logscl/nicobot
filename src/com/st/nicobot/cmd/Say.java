package com.st.nicobot.cmd;

import com.st.nicobot.NicoBot;
import com.st.nicobot.utils.Option;

public class Say extends NiCommand {
	
	private static final String COMMAND = "say";
	private static final String FORMAT = "say <#channel> <message> <removeFormating>";
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
		nicobot.sendMessage(opts.channel, args[0]);
	}

}

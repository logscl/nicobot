package com.st.nicobot.cmd;

import org.picocontainer.annotations.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.st.nicobot.NicoBot;
import com.st.nicobot.services.Messages;
import com.st.nicobot.utils.Option;

/**
 * @author Julien
 *
 */
public class StopNicoBot extends NiCommand {

	private static Logger logger = LoggerFactory.getLogger(StopNicoBot.class);
	
	private static final String CMD = "stop";
	private static final String FORMAT = "stop";
	private static final String DESC = "Force nicobot a s'éteindre";
	
	@Inject
	private Messages messages;
	
	@Override
	public String getCommandName() {
		return CMD;
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
		return true;
	}

	@Override
	protected void doCommand(NicoBot nicobot, String message, String[] args, Option opts) {
		logger.info("Stoping nicobot ...");
		
		nicobot.sendRawLine("PRIVMSG " + opts.channel + " :" + messages.getOtherMessage("onLeave"));
		nicobot.partChannel(opts.channel, messages.getOtherMessage("leaveReason"));
		
		nicobot.disconnect();
	}

}

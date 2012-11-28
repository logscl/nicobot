/**
 * 
 */
package com.st.nicobot.cmd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.st.nicobot.NicoBot;
import com.st.nicobot.internal.services.MessagesImpl;
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
	protected void doCommand(NicoBot nicobot, String message, String[] args, Option opts) {
		logger.info("Stoping nicobot ...");
		
		nicobot.sendRawLine("PRIVMSG " + opts.channel + " :" + MessagesImpl.getInstance().getOtherMessage("onLeave"));
		nicobot.partChannel(opts.channel, MessagesImpl.getInstance().getOtherMessage("leaveReason"));
		
		nicobot.disconnect();
	}

}

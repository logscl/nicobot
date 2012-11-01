/**
 * 
 */
package com.st.nicobot.cmd;

import com.st.nicobot.NicoBot;
import com.st.nicobot.internal.services.MessagesImpl;
import com.st.nicobot.utils.Option;

/**
 * @author Julien
 *
 */
public class StopNicoBot extends NiCommand {

	private static final String CMD = "stop";
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
	protected void doCommand(NicoBot nicobot, String message, String[] args, Option opts) {
		System.out.println("Stoping nicobot ...");
		nicobot.sendRawLine("PRIVMSG " + opts.channel + " :" + MessagesImpl.getInstance().getOtherMessage("onLeave"));
		nicobot.partChannel(opts.channel, MessagesImpl.getInstance().getOtherMessage("leaveReason"));
		
		nicobot.disconnect();
	}

}

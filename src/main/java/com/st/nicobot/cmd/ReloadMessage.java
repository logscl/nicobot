package com.st.nicobot.cmd;

import org.picocontainer.annotations.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.st.nicobot.NicoBot;
import com.st.nicobot.internal.services.MessagesImpl;
import com.st.nicobot.services.Messages;
import com.st.nicobot.services.UsersService;
import com.st.nicobot.utils.Option;

/**
 * @author Julien
 *
 */
public class ReloadMessage extends NiCommand {

	private static Logger logger = LoggerFactory.getLogger(ReloadMessage.class);
	
	private static final String COMMAND = "refresh";
	private static final String FORMAT = "refresh";
	private static final String DESC = "Redémarre les différents messages de nicobot.";
	
	@Inject
	private Messages messages;
	
	@Inject
	private UsersService users;
	
	public ReloadMessage() {	}
	
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
		return true;
	}
	
	@Override
	public void doCommand(NicoBot nicobot, String message, String[] args, Option opts) {
		logger.info("Start reloading messages");
		
		((MessagesImpl)messages).start();
	}
	
}

package com.st.nicobot.job.task;

import org.picocontainer.annotations.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.st.nicobot.NicoBot;
import com.st.nicobot.context.annotations.Component;
import com.st.nicobot.services.Messages;

/**
 * @author Julien
 *
 */
@Component
public class HappyGeekTimeTask extends Task {

	private static Logger logger = LoggerFactory.getLogger(HappyGeekTimeTask.class);
	
	@Inject
	private Messages messages;
	
	@Inject
	private NicoBot bot;
	
	@Override
	public void run() {
		logger.info("HappyGeekTimeTask running ...");
		
		for(String channel : bot.getChannels()) {
			logger.debug("Sending message to {}" , channel);
			
			bot.sendMessage(channel, messages.getOtherMessage("hgt"));
		}
	}
}

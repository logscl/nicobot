/**
 * 
 */
package com.st.nicobot.job.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.st.nicobot.BotMain;
import com.st.nicobot.NicoBot;
import com.st.nicobot.internal.services.MessagesImpl;

/**
 * @author Julien
 *
 */
public class HappyGeekTimeTask extends Task {

	private static Logger logger = LoggerFactory.getLogger(HappyGeekTimeTask.class);
	
	@Override
	public void run() {
		logger.info("HappyGeekTimeTask running ...");
		
		
		NicoBot bot = BotMain.getBot();
		
		for(String channel : bot.getChannels()) {
			logger.debug("Sending message to {}" , channel);
			
			bot.sendMessage(channel, MessagesImpl.getInstance().getOtherMessage("hgt"));
		}
	}
}
